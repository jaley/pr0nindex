(ns pr0nindex.pr0n
  (:use pr0nindex.key
        clojure.contrib.json)
  (:require [clojure.contrib.duck-streams :as duck])
  (:import [java.net URL URLEncoder]))

(def *url-format*
  "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=%s&key=%s&safe=%s")

(defn make-url
  "Construct a java.net.URL for the search request."
  [phrase safe?]
  (URL.
   (format *url-format*
           (str \" (URLEncoder/encode phrase "UTF-8") \")
           *api-key*
           ({true "active", false "off"} safe?))))

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

(def clamp (comp (partial min 1) (partial max 0)))

(defn pr0n-index
  "Return a value between 0 and 1, where 0 indicates unlikely offensive material,
   and 1 is almost certainly offensive."
  [phrase]
  (let [hits_on  (hits (search! (make-url phrase true)))
        hits_off (hits (search! (make-url phrase false)))]
    (if (> hits_off 0)
      (clamp (/ (- hits_off hits_on) hits_off))
      0.0)))

