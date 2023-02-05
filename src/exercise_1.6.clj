(ns exercise-1.6)

(defn good-enough? [guess x]
  (< (Math/abs (- (* guess guess) x)) 0.001))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x)
               x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

(sqrt 9) ;; => 3.00009155413138

(defn new-if [predicate then-clause else-clause]
  (cond
    predicate then-clause
    :else     else-clause))

(new-if (= 2 3) 0 5) ;; => 5
(new-if (= 1 1) 0 5) ;; => 0

(defn sqrt-iter2 [guess x]
  (new-if (good-enough? guess x)
          guess
          (sqrt-iter2 (improve guess x) x)))

(defn sqrt2 [x]
  (sqrt-iter2 1.0 x))

(sqrt2 9) ;; => Stack Overflor
