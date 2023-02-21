(ns exercise-1.33
  (:require [common]))

(defn filtered-accumulate
  [predicate combiner null-value term a next b]
  (if (> a b)
    null-value
    (combiner (if (predicate a) (term a) null-value)
              (filtered-accumulate predicate combiner null-value term (next a) next b))))

(defn sum-square-primes [a b]
  (filtered-accumulate common/prime? + 0 common/square a inc b))

(defn coprime? [a b]
  (= 1 (common/gcd a b)))

(defn coprime-product [n]
  (filtered-accumulate (partial coprime? n) * 1 identity 1 inc n))

(comment
  (sum-square-primes 2 10) ;; => 87 = 2^2 + 3^2 + 5^2 + 7^2
  (coprime-product 10)     ;; => 189 = 1 * 3 * 7 * 9
  )
