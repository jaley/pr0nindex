(ns pr0nindex.core
  "Main servlet entry point."
  (:use [pr0nindex.pr0n :only [pr0n-index]]
        [pr0nindex.permalink :only [page]]
        [compojure.core :only [defroutes GET]]
        [ring.util.servlet :only [defservice]]
        [compojure.route :as route]
        [clj-json.core :as json])
  (:gen-class :extends javax.servlet.http.HttpServlet))

(defn response
  [phrase]
  {:status 200,
   :headers {"Content-Type" "application/json"},
   :body (json/generate-string
          {:phrase phrase,
           :ratio (if-let [ratio (pr0n-index phrase)] ratio 0.0)})})

(defn permalink
  [phrase]
  {:status 200,
   :headers {"Content-Type" "text/html"},
   :body (page phrase (pr0n-index phrase))})

(defroutes app
  (GET "/pindex" {{p "p"} :params} (response p))
  (GET "/perma" {{p "p"} :params} (permalink p))
  (route/not-found "<h1>Page not found</h1>"))

(defservice app)
