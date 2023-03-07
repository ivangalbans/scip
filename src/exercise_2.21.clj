(ns exercise-2.21
  (:require [common :refer [square]]))

(defn square-list [items]
  (if (empty? items)
    nil
    (cons (square (first items)) (square-list (rest items)))))

(defn square-list2 [items]
  (map square items))

(comment
  (= '(1 4 9 16) (square-list (list 1 2 3 4)))
  (= '(1 4 9 16) (square-list2 (list 1 2 3 4))))
