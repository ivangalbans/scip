(ns exercise-2.18)

(defn reverse2
  [coll]
  (if (empty? coll)
    []
    (conj (reverse2 (rest coll)) (first coll))))

(comment
  (= [25 16 9 4 1] (reverse2 (list 1 4 9 16 25))))
