(ns exercise-2.37
  (:require [common :refer [accumulate]]
            [exercise-2.36 :refer [accumulate-n]]))

(defn dot-product
  [v w]
  (accumulate + 0 (map * v w)))

(defn matrix-*-vector
  [m v]
  (map #(dot-product % v) m))

(defn transpose
  [mat]
  (accumulate-n cons nil mat))

(defn matrix-*-matrix [m n]
  (let [cols (transpose n)]
    (map #(matrix-*-vector cols %) m)))

(comment

  (= 11 (dot-product '(1 2) '(3 4)))

  (= '(12 30 48)
     (matrix-*-vector '((1 2 3) (4 5 6) (7 8 9)) '(2 2 2)))

  (= '((1 4 6) (2 5 7) (3 6 8) (4 6 9))
     (transpose '((1 2 3 4) (4 5 6 6) (6 7 8 9)))

     (= '((19 22) (43 50))
        (matrix-*-matrix '((1 2) (3 4)) '((5 6) (7 8))))))
