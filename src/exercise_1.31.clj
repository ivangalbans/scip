(ns exercise-1.31)

(defn prod [term a next b]
  (if (> a b)
    1
    (* (term a)
       (prod term (next a) next b))))

(defn pi [n]
  (letfn [(next [x] (+ x 2))
          (term [x] (* (/ x (dec x)) (/ x (inc x))))]

    (* 4 (/ 2 3) (prod term 4 next n))))

(comment
  (double (pi 100))    ;; => 3.126078900215411
  (double (pi 1000))   ;; => 3.140023818600597
  (double (pi 10000))  ;; => 3.141435593589908
  )
(defn prod-iterative [term a next b]
  (letfn [(iter [x result]
            (if (> x b)
              result
              (recur (next x) (* (term x) result))))]
    (iter a 1)))

(defn pi-iterative [n]
  (letfn [(next [x] (+ x 2))
          (term [x] (* (/ x (dec x)) (/ x (inc x))))]
    (* 4 (/ 2 3) (prod-iterative term 4 next n))))

(comment
  (double (pi-iterative 100))   ;; => 3.126078900215411
  (double (pi-iterative 1000))  ;; => 3.140023818600597
  (double (pi-iterative 10000)) ;; => 3.141435593589908
  )
