(ns pr0nindex.test.pr0n
  (:use [pr0nindex.pr0n])
  (:use [clojure.test]))

(defn pr0n?
  "Return true if a phrase is more than 80% pr0n."
  [phrase]
  (> (pr0n-index phrase) 0.8))

(defn safe?
  "Return true if a phrase is less than 50% pr0n, thus likely safe, maybe."
  [phrase]
  (<= (pr0n-index phrase) 0.5))

(deftest test-pr0n-index-positive
  (is (pr0n? "man juice"))
  (is (pr0n? "hot mom"))
  (is (pr0n? "lex steele"))
  (is (pr0n? "huge jugs"))
  (is (pr0n? "twat pounding")))

(deftest test-pr0n-index-negative
  (is (safe? "man"))
  (is (safe? "juice"))
  (is (safe? "hot"))
  (is (safe? "mom"))
  (is (safe? "lex"))
  (is (safe? "steele"))
  (is (safe? "huge"))
  (is (safe? "jugs")))

(deftest test-pr0n-index-garbage
  (is (safe? nil))
  (is (safe? ""))
  (is (safe? "123 5432 1234 1"))
  (is (safe? "!!223 __ 123431")))

