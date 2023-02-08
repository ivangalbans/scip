(ns exercise-1.8
  (:require [exercise-1.6]
            [common]))

(def iterations (atom 0))

(defn good-enough? [guess next-guest]
  (common/close? guess next-guest))

(defn improve [guess x]
  (/ (+ (/ x (* guess guess)) (* 2 guess)) 3))

(defn sqrt-iter [guess x]
  (swap! iterations inc)
  (let [next-guess (improve guess x)]
    (if (good-enough? guess next-guess)
      guess
      (sqrt-iter next-guess x))))

(defn sqrt3 [x]
  (reset! iterations 0)
  {:result (sqrt-iter 1.0 x)
   :iterations @iterations})

(comment
  (def ans (:result (sqrt3 9)))
  (* ans ans ans) ;; => 9.00025574517236
  )
