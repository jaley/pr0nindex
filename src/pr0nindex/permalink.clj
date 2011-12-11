(ns pr0nindex.permalink
  (:use net.cgrand.enlive-html))

(def *max-penis-length* 25.0)

(defn make-penis
  [length]
  (str "8=" (apply str (take (int length) (repeat "="))) "D"))

(defn make-label
  [phrase index]
  (format "The phrase \"%s\" is %1.2f%% pr0n!" phrase (* 100 index)))

(deftemplate permalink-template "templates/perm-template.html"
  [label penis]
  [:#willy] (content penis)
  [:#text_results] (content label))

(defn page
  "Render a permalink page for the given phrase and pr0n index."
  [phrase index]
  (apply str (permalink-template
              (make-label phrase index)
              (make-penis (* index *max-penis-length*)))))
