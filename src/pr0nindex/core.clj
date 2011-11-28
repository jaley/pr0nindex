(ns pr0nindex.core
  "Main servlet entry point."
  (:use [pr0nindex.pr0n :only [pr0n-index]]
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

(defroutes app
  (GET "/pindex" {{p "p"} :params} (response p))
  (route/not-found "<h1>Page not found</h1>"))

(defservice app)
