(ns exercise-2.17)

(defn last-pair
  [coll]
  (let [[x xs] [(first coll) (rest coll)]]
    (if (seq xs)
      (recur xs)
      x)))

(comment
  (= 5 (last-pair [1 2 3 4 5]))
  (= 1 (last-pair [1]))
  (= 34 (last-pair (list 23 72 149 34))))
