(ns exercise-2.22
  (:require [common :refer [square]]))

(defn square-list-wrong1 [items]
  (letfn [(iter [things answer]
            (if (empty? things)
              answer
              (iter (rest things)
                    (cons (square (first things)) answer))))]
    (iter items nil)))

(defn square-list-wrong2 [items]
  (letfn [(iter [things answer]
            (if (empty? things)
              answer
              (iter (rest things)
                    (conj answer (square (first things))))))]
    (iter items nil)))

(comment
  (= (reverse '(1 4 9 16)) (square-list-wrong1 (list 1 2 3 4)))
  (= (reverse '(1 4 9 16)) (square-list-wrong2 (list 1 2 3 4))))

;; Why ?
;; Both cons and conj put the element at the start when working with lists

(comment
  (= '(1 2 3 4) (cons 1 '(2 3 4)))
  (= '(1 2 3 4) (conj '(2 3 4) 1)))

(defn square-list [items]
  (letfn [(iter [things answer]
            (if (empty? things)
              answer
              (iter (rest things)
                    (conj answer (square (first things))))))]
    (iter items [])))

(comment
  (= [2 3 4 1] (conj [] 1))
  (= [1 4 9 16] (square-list (list 1 2 3 4))))
