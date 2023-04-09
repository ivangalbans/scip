(ns complex-numbers
  (:require [common :refer [square]])
  (:import (java.io Writer)))

(defn list->rectangular
  [coll]
  (vec (cons :rectangular coll)))

(defn list->polar
  [coll]
  (vec (cons :polar coll)))

(def representation first)

(comment
  (= :rectangular (representation #complex/rectangular [1 2]))
  (= :polar       (representation #complex/polar [1 2])))

(defmulti real-part representation)
(defmulti imag-part representation)
(defmulti magnitude representation)
(defmulti angle representation)

(defmethod real-part :rectangular
  [[_rep x _y]]
  x)

(defmethod real-part :polar
  [number]
  (* (magnitude number) (Math/cos (angle number))))

(defmethod imag-part :rectangular
  [[_rep _x y]]
  y)

(defmethod imag-part :polar
  [number]
  (* (magnitude number) (Math/sin (angle number))))

(defmethod magnitude :rectangular
  [number]
  (Math/sqrt (+ (square (real-part number))
                (square (imag-part number)))))

(defmethod magnitude :polar
  [[_rep r _a]]
  r)

(defmethod angle :rectangular
  [number]
  (Math/atan (imag-part number) #_x) ;; FIXME: atan only receives one parameter
  )

(defmethod angle :polar
  [[_rep _r a]]
  a)

(defn sum [number1 number2]
  (let [real (+ (real-part number1) (real-part number2))
        imag (+ (imag-part number1) (imag-part number2))]
    #complex/rectangular [real imag]))

(defmethod print-method :rectangular
  [[_rep x y] ^Writer out]
  (.write out (str #complex/rectangular [x y])))

(comment
  (= [:rectangular 4 6] (sum #complex/rectangular [1 2] #complex/rectangular [3 4])) ;; TODO: it's possible to return `#complex/rectangular [4 6]`
  )
