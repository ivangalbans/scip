(ns exercise-2.56
  (:require [common :refer [third]]))

(defn variable? [x]
  (symbol? x))

(defn same-variable? [v1 v2]
  (and (variable? v1)
       (variable? v2)
       (= v1 v2)))

(defn make-sum [a1 a2]
  (list '+ a1 a2))

(defn make-product [m1 m2]
  (list '* m1 m2))

(defn sum? [x]
  (and (sequential? x)
       (= (first x) '+)))

(defn addend [s]
  (second s))

(defn augend [s]
  (third s))

(defn product? [x]
  (and (sequential? x) (= (first x) '*)))

(defn multiplier [p]
  (second p))

(defn multiplicand [p]
  (third p))

(defn deriv [exp var]
  (cond
    (number? exp) 0
    (variable? exp) (if (same-variable? exp var) 1 0)
    (sum? exp) (make-sum (deriv (addend exp) var)
                         (deriv (augend exp) var))
    (product? exp) (make-sum
                    (make-product
                     (multiplier exp)
                     (deriv (multiplicand exp) var))
                    (make-product
                     (deriv (multiplier exp) var)
                     (multiplicand exp)))
    :else (throw (Exception. (format "unknown expression type: DERIV %s" exp)))))

(comment
  (= '(+ 1 0)
     (deriv '(+ x 3) 'x))

  (= '(+ (* x 0) (* 1 y))
     (deriv '(* x y) 'x))

  (= '(+ (* (* x y) (+ 1 0))
         (* (+ (* x 0) (* 1 y))
            (+  x 3)))
     (deriv '(* (* x y) (+ x 3)) 'x)))

(defn =number? [exp num]
  (and (number? exp)
       (= exp num)))

(defn make-sum
  [a1 a2]
  (cond
    (=number? a1 0) a2
    (=number? a2 0) a1
    (and (number? a1) (number? a2)) (+ a1 a2)
    :else (list '+ a1 a2)))

(defn make-product [m1 m2]
  (cond
    (or (=number? m1 0) (=number? m2 0)) 0
    (=number? m1 1) m2
    (=number? m2 1) m1
    (and (number? m1) (number? m2)) (* m1 m2)
    :else (list '* m1 m2)))

(comment
  (= 1 (deriv '(+ x 3) 'x))
  (= 'y (deriv '(* x y) 'x))
  (= '(+ (* x y) (* y (+ x 3)))
     (deriv '(* (* x y) (+ x 3)) 'x)))

(defn exponentiation? [x]
  (and (sequential? x) (= (first x) '**)))

(defn base [exp]
  (second exp))

(defn exponent [exp]
  (third exp))

(defn make-exponentiation
  [base exponent]
  (cond
    (=number? exponent 0) 1
    (=number? exponent 1) base
    :else (list '** base exponent)))

(defn deriv [exp var]
  (cond
    (number? exp) 0
    (variable? exp) (if (same-variable? exp var) 1 0)
    (sum? exp) (make-sum (deriv (addend exp) var)
                         (deriv (augend exp) var))
    (product? exp) (make-sum
                    (make-product
                     (multiplier exp)
                     (deriv (multiplicand exp) var))
                    (make-product
                     (deriv (multiplier exp) var)
                     (multiplicand exp)))
    (exponentiation? exp) (make-product (make-product (exponent exp)
                                                      (make-exponentiation (base exp)
                                                                           (make-sum (exponent exp) -1)))
                                        (deriv (base exp) var))
    :else (throw (Exception. (format "unknown expression type: DERIV %s" exp)))))

(comment
  (= '(* 2 x) (deriv '(** x 2) 'x))
  (= '(* 3 (** x 2)) (deriv '(** x 3) 'x)))
