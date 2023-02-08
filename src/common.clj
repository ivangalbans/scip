(ns common)

(def EPS 0.001)

(defn average [x y]
  (/ (+ x y) 2))

(defn close? [x1 x2]
  (< (Math/abs (- x1 x2)) common/EPS))
