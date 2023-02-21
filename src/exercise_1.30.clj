(ns exercise-1.30
  (:require [common]))

(defn sum-iterative [term a next b]
  (letfn [(iter [a result]
            (if (> a b)
              result
              (recur (next a) (+ (term a) result))))]
    (iter a 0)))

(defn integral-iterative [f a b dx]
  (letfn [(add-dx [x] (+ x dx))]
    (* (sum-iterative f (+ a (/ dx 2.0)) add-dx b)
       dx)))

(comment
  (integral-iterative common/cube 0 1 0.01)  ;; => 0.24998750000000042
  (integral-iterative common/cube 0 1 0.001) ;; => 0.249999875000001
  )
