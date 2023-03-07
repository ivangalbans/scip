(ns exercise-2.28)

(defn fringe
  [coll]
  (filter (complement sequential?)
          (rest (tree-seq sequential? seq coll))))

(def x (list (list 1 2) (list 3 4)))

(comment
  (= '(1 2 3 4) (fringe x))
  (= '(1 2 3 4 1 2 3 4) (fringe (list x x))))
