(ns exercise-2.1
  (:require [common]))

(defn make-rat
  [a b]
  (if (= (common/sign b) -1)
    [(- a) (- b)]
    [a b]))

(comment
  (make-rat 1 2)
  (make-rat -1 -2)
  (make-rat -1 2)
  (make-rat 1 -2))
