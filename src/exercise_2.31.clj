(ns exercise-2.31
  (:require [common :refer [square]]
            [clojure.walk :as walk]))

(defn tree-map
  [f tree]
  (walk/prewalk #(if (number? %) (f %) %) tree))

(defn square-tree
  [tree]
  (tree-map square tree))

(comment
  (= '(1 (4 (9 16) 25) (36 49))
     (square-tree
      (list 1
            (list 2 (list 3 4) 5)
            (list 6 7)))))
