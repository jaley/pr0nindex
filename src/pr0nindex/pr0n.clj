(ns pr0nindex.pr0n
  (:use pr0nindex.key
        pr0nindex.profanities
        clojure.contrib.json)
  (:require [clojure.contrib.duck-streams :as duck]
            [clojure.string :as string])
  (:import [java.net URL URLEncoder]))

(def *url-format*
  "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=%s&key=%s&safe=%s")

(defn make-url
  "Construct a java.net.URL for the search request."
  [phrase safe]
  (URL.
   (format *url-format*
           (URLEncoder/encode (str \" phrase \") "UTF-8")
           *api-key*
           safe)))

(defn search!
  "Executes query URL and returns results from JSON parser."
  [req-url]
  (with-open [s (duck/reader req-url)]
    (read-json s)))

(defn hits
  "Return number of search hits from results, or nil if search didn't work."
  [results]
  (if-let [res-count (-> results :responseData :cursor :estimatedResultCount)]
    (Double/parseDouble res-count)
    0.0))

(def clamp (comp (partial min 1.0) (partial max 0.0)))

(defn safe-type
  "If phrase contains profanities, use moderate, else high."
  [phrase]
  (if (some profanity-set (string/split phrase #"\s+"))
    "moderate"
    "high"))

(defn pr0n-index*
  "Return a value between 0 and 1, where 0 indicates unlikely offensive material,
   and 1 is almost certainly offensive."
  [phrase]
  (if (nil? phrase)
    0.0 ; guard for nil
    (let [hits_on  (hits (search! (make-url phrase (safe-type phrase))))
          hits_off (hits (search! (make-url phrase "off")))]
      (if (> hits_off 0)
        (clamp (/ (- hits_off hits_on) hits_off))
        0.0))))

(def pr0n-index (memoize pr0n-index*))
