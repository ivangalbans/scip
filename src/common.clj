(ns common)

(def EPS 0.001)

(defn average [x y]
  (/ (+ x y) 2))

(defn close? [x1 x2]
  (< (Math/abs (- x1 x2)) common/EPS))

(defn square [x]
  (* x x))

(defn cube [x]
  (* x x x))

(defn gcd [a b]
  (if (= b 0)
    a
    (recur b (rem a b))))

;; assume n >= 2
(defn prime? [n]
  (letfn [(divides? [d x]
            (= (rem x d) 0))
          (find-divisor [x d]
            (cond (> (square d) x) n
                  (divides? d x) d
                  :else (find-divisor x (inc d))))
          (smallest-divisor [x]
            (find-divisor x 2))]
    (= n (smallest-divisor n))))

(defn sign [x]
  (cond
    (< x 0) -1
    (> x 0) 1
    :else 0))

(defn pow [b n]
  (cond
    (= n 0) 1
    (even? n) (square (pow b (/ n 2)))
    :else (* b (pow b (- n 1)))))

(defn accumulate [f initial sequence]
  (if (empty? sequence)
    initial
    (f (first sequence)
       (accumulate f initial (rest sequence)))))
