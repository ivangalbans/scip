(ns exercise-2.33
  (:require [common :refer [square]]))

(defn accumulate
  [f null-value coll]
  (reduce f null-value coll))

(defn map2 [p sequence]
  (accumulate (fn [x y] (concat x (list (p y)))) nil sequence))

(comment
  (= '(2 3 4) (map2 inc [1 2 3]))
  (= '(1 4 9) (map2 square [1 2 3])))

(defn append [seq1 seq2]
  (if (empty? seq1)
    seq2
    (cons (first seq1) (append (rest seq1) seq2))))

(defn append2 [seq1 seq2]
  (accumulate conj (vec seq1) (vec seq2)))

(comment
  (= '(1 2 3 4) (append2 '(1 2) '(3 4))))

(defn length [sequence]
  (accumulate (fn [i _] (inc i)) 0 sequence))

(comment
  (= 4 (length '(1 2 3 4)))
  (= 0 (length '())))
