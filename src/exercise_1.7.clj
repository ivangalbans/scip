(ns exercise-1.7
  (:require [exercise-1.6]
            [common]))

(defn good-enough? [guess next-guest]
  (< (Math/abs (- guess next-guest)) common/EPS))

(defn improve [guess x]
  (common/average guess (/ x guess)))

(def iterations (atom 0))

(defn sqrt-iter [guess x]
  (swap! iterations inc)
  (let [next-guess (improve guess x)]
    (if (good-enough? guess next-guess)
      guess
      (sqrt-iter next-guess x))))

(defn sqrt [x]
  (reset! iterations 0)
  {:result (sqrt-iter 1.0 x)
   :iterations @iterations})

(comment
  (def x 12345678901234)

  (exercise-1.6/sqrt x)  ;; => Stack Overflow

  (sqrt x)               ;; => {:result 3513641.8288200637, :iterations 27}
  )

(comment
  (def x 0.00000000123)

  (exercise-1.6/sqrt x)  ;; => {:result 0.031250013107186406, :iterations 6}
  (* 0.031250013107186406 0.031250013107186406) ;; => 9.76563319199322E-4

  (sqrt x)               ;; => {:result 0.0019533349146870433, :iterations 10}
  (* 0.0019533349146870433 0.0019533349146870433) ;; => 3.815517288935439E-6
  )

(comment
  (def x 12345678901234)
  (def guess 3513641.8288200637)

  ;; Can't improve more
  (improve guess x) ;; => 3513641.8288200637
  (= guess 3513641.8288200637) ;; => true

  ;; However the guess is not good for the good-enough? implementation of Exercise 1.6
  (exercise-1.6/good-enough? guess x) ;; => false

  ;; But it's good enough for the implementation of Exercise 1.7, which is based in the changes of guess
  (good-enough? guess (improve guess x)) ;; => true
  )
