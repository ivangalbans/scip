(ns exercise-2.5
  (:require [common :refer [pow]]))

(defn cons
  [a b]
  (* (pow 2 a)
     (pow 3 b)))

(defn log
  "Return maximun x such as n divides b^x"
  [n b]
  (if (= (rem n b) 0)
    (+ 1 (log (/ n b) b))
    0))

(defn car [n] (log n 2))
(defn cdr [n] (log n 3))

(comment
  (cons 2 3)
  (car (cons 2 3))
  (cdr (cons 2 3)))
