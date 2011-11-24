(ns pr0nindex.test.pr0n
  (:use [pr0nindex.pr0n])
  (:use [clojure.test]))

(defn pr0n?
  [phrase]
  (> (pr0n-index phrase) 0.75))
(def npr0n? (complement pr0n?))

(deftest test-pr0n-index-positive
  (is (pr0n? "man juice"))
  (is (pr0n? "hot mom"))
  (is (pr0n? "lex steele"))
  (is (pr0n? "huge jugs")))

(deftest test-pr0n-index-negative
  (is (npr0n? "man"))
  (is (npr0n? "juice"))
  (is (npr0n? "hot"))
  (is (npr0n? "mom"))
  (is (npr0n? "lex"))
  (is (npr0n? "steele"))
  (is (npr0n? "huge"))
  (is (npr0n? "jugs")))

(deftest test-pr0n-index-garbage
  (is (npr0n? ""))
  (is (npr0n? "123 5432 1234 1"))
  (is (npr0n? "!!223 __ 123431")))

