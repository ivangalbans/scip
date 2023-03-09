(ns exercise-2.30
  (:require [clojure.walk :as walk]))

(defn square-tree
  [tree]
  (walk/prewalk 2))

(square-tree
 (list 1
       (list 2 (list 3 4) 5)
       (list 6 7)))

(= '(1 (4 (9 16) 25) (36 49))
   (square-tree
    (list 1
          (list 2 (list 3 4) 5)
          (list 6 7))))
