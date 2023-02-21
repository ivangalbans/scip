(ns exercise-1.29
  (:require [common]))

;; Simpson
;; Compare with CPP solution: https://github.com/ivangalbans/TeamReference/blob/master/Numeric/Simpson.cpp

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn integral [f a b dx]
  (letfn [(add-dx [x] (+ x dx))]
    (* (sum f (+ a (/ dx 2.0)) add-dx b)
       dx)))

(comment
  (integral common/cube 0 1 0.01)  ;; => 0.24998750000000042
  (integral common/cube 0 1 0.001) ;; => 0.249999875000001
  )
(defn simpson [f a b n]
  (let [h (/ (- b a) n)
        y_k (fn [k] (f (+ a (* k h))))
        coef #(cond (= % 0)  1
                    (= % n)  1
                    (odd? %) 4
                    :else    2)
        ff  (fn [k] (* (coef k) (y_k k)))]
    (* (/ h 3.0)
       (sum ff 1 inc n))))

(comment
  (simpson common/cube 0 1 100)  ;; => 0.25
  (simpson common/cube 0 1 1000) ;; => 0.25
  )
