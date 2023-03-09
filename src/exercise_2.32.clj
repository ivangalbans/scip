(ns exercise-2.32)

(defn subsets [s]
  (let [[x xs] [(first s) (rest s)]]
    (if (empty? s)
      nil
      (let [re (subsets (rest s))]
        (concat re (map (partial cons (first s)) re))))))

(subsets '(1 2 3))

(map (partial cons 0) '((1) (1 2)))
