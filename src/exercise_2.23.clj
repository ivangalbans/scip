(ns exercise-2.23)

(defn for-each
  [f coll]
  (if (empty? coll)
    nil
    (do
      (f (first coll))
      (recur f (rest coll)))))

(for-each (fn [x]
            (newline)
            (print x))
          (list 57 321 88))
