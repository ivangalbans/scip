(ns exercise-1.6
  (:require [common]))

(defn good-enough? [guess x]
  (< (Math/abs (- (* guess guess) x)) common/EPS))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(def iterations (atom 0))

(defn sqrt-iter [guess x]
  (swap! iterations inc)
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x) x)))

(defn sqrt [x]
  (reset! iterations 0)
  {:result (sqrt-iter 1.0 x)
   :iterations @iterations})

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

(comment
  (sqrt2 9)) ;; => Stack Overflor

;; Why StackOverflow ?
;;
;; Since Lisp languages use applicative-order evaluation, the parameters are evaluated first
;; and then the new-if function will be replaced. To see the difference between the if and new-if
;; symbols let's evaluate the following:

(defmacro new-if-2 [predicate then-clause else-clause]
  (list 'cond
    predicate then-clause
    :else     else-clause))

(macroexpand '(new-if-2 true 1 (/ 1 0)))

(new-if-2 true 1 (/ 1 0))

(comment
  (if true 1 (/ 1 0)) ;; => 1
  (new-if true 1 (/ 1 0))) ;; => Unhandled `java.lang.ArithmeticException` Divide by zero

;; Let's see what happens with the Newton's method implemented in sqrt2 that uses new-if function:

(comment
  (sqrt2 9)

  (sqrt-iter2 1.0 9)

  (new-if (good-enough? 1.0 9)
          1.0
          (sqrt-iter2 (improve 1.0 9) 9))

  (new-if (good-enough? 1.0 9) ;; => Evalute (good-enough? 1.0 9)
          1.0
          (sqrt-iter2 5.0 9))  ;; => (sqrt-iter2 5.0 9)

  (new-if false ;; (good-enough? 1.0 9) => false
          1.0
          (new-if (good-enough? 5.0 9) ;; =>  (sqrt-iter2 5.0 9) substitute the function with the new-if function
                  5.0
                  (sqrt-iter2 (improve 5.0 9) 9)))

  (new-if false
          1.0
          (new-if (good-enough? 5.0 9)
                  5.0
                  (sqrt-iter2 3.4 9))) ;; (improve 5.0 9) => 3.4

  (new-if false
          1.0
          (new-if (good-enough? 5.0 9)  ;;Evalute (good-enough? 5.0 9)
                  5.0
                  (sqrt-iter2 3.4 9)))

  (new-if false
          1.0
          (new-if false
                  5.0
                  (new-if (good-enough? 3.4 9)
                          3.4
                          (sqrt-iter2 (improve 3.4 9) 9))))
  ;; ...

  (new-if false
          1.0
          (new-if false
                  5.0
                  ;; ...
                  (new-if true
                          3.00009155413138
                          (sqrt-iter2 (improve 3.00009155413138 9) 9))))

  ;; Even when the root was binded to `guess` the evaluation never finishes due to the applicative order evaluation.
  ;; The parameters have to be evaluated first and then the new-if function is substituted
  )
