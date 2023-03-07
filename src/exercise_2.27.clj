(ns exercise-2.27)

(defn deep-reverse
  [coll]
  (let [[x xs] [(first coll) (rest coll)]]
    (cond
      (and (empty? xs) (sequential? x)) [(deep-reverse x)]
      (empty? xs) [x]
      (not (sequential? x)) (conj (deep-reverse xs) x)
      :else (conj (deep-reverse xs) (deep-reverse x)))))

(comment
  (= [2 1] (deep-reverse [1 2]))
  (= [[2 1]] (deep-reverse [[1 2]]))
  (= [[2 1] [4 3]] (deep-reverse [[3 4] [1 2]]))
  (= [[[[[7 6]]] [[[5]]] [3] [2 1]]] (deep-reverse [[[1 2] [3] [[[5]]] [[[6 7]]]]])))
