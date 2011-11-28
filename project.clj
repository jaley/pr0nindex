(defproject pr0nindex "0.0.1-SNAPSHOT"
  :description "GAE demo of the pr0n index."
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [compojure "0.5.3"]
                 [ring "0.3.11"]
                 [com.google.appengine/appengine-tools-sdk "1.6.0"]
                 [clj-json "0.3.1"]]
  :compile-path "war/WEB-INF/classes/"
  :library-path "war/WEB-INF/lib/"
  :aot [pr0nindex.core])
