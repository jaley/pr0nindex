(ns pr0nindex.core
  "Main servlet entry point."
  (:use [compojure.core :only [defroutes GET]]
        [ring.util.servlet :only [defservice]])
  (:gen-class :extends javax.servlet.http.HttpServlet))

(defn response
  [& _]
  {:status 200, :headers {}, :body "<h1>Hello!</h1>"})

(defroutes app
  (GET "/" req (response)))

(defservice app)

