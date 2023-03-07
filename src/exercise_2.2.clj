(ns exercise-2.2
  (:require [common]))

(defn make-segment
  [p1 p2]
  [p1 p2])

(defn start-segment
  [[p1 _p2]]
  p1)

(defn end-segment
  [[_p1 p2]]
  p2)

(defn make-point
  [x y]
  [x y])

(defn x-point
  [[x _y]]
  x)

(defn y-point
  [[_x y]]
  y)

(defn midpoint-segment
  [segment]
  (let [start (start-segment segment)
        end   (end-segment segment)]
    (make-point (common/average (x-point start) (x-point end))
                (common/average (y-point start) (y-point end)))))

(comment
  (def segment (make-segment (make-point 1 1) (make-point 3 2)))
  (midpoint-segment segment))
