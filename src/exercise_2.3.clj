(ns exercise-2.3
  (:require [common :refer [square]]
            [exercise-2.2 :refer [x-point y-point]]))

(defn make-rectangle
  "Make a rectangle with anti-clowwise points"
  [p1 p2 p3 p4]
  [p1 p2 p3 p4])

(defn distance
  [p1 p2]
  (Math/sqrt (+ (square (- (x-point p1) (x-point p2)))
                (square (- (y-point p1) (y-point p2))))))

(defn length
  [[p1 p2 _ _]]
  (distance p1 p2))

(defn width
  [[p1 _ _ p4]]
  (distance p1 p4))

(defn perimeter
  [rectangle]
  (* 2 (+ (length rectangle)
          (width rectangle))))

(defn area
  [rectangle]
  (* (length rectangle)
     (width rectangle)))

(comment
  (def rectangle (make-rectangle [1 1] [5 1] [5 7] [1 7]))
  (area rectangle))
