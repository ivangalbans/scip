(ns exercise-1.32)

(defn accumulate
  [combiner null-value term a next b]
  (if (> a b)
    null-value
    (combiner (term a)
              (accumulate combiner null-value term (next a) next b))))

(defn prod [term a next b]
  (accumulate * 1 term a next b))

(defn sum [term a next b]
  (accumulate + 0 term a next b))

(defn sumatoria [a b]
  (sum identity a inc b))

(defn factorial [n]
  (prod identity 1 inc n))

(comment
  (sumatoria 1 10) ;; => 55
  (factorial 5)    ;; => 120
  )

(defn accumulate-iterative
  [combiner null-value term a next b]
  (letfn [(iter [x result]
            (if (> x b)
              result
              (recur (next x) (combiner (term x) result))))]
    (iter a null-value)))

(defn prod-iterative [term a next b]
  (accumulate-iterative * 1 term a next b))

(defn sum-iterative [term a next b]
  (accumulate-iterative + 0 term a next b))

(defn sumatoria-iterative [a b]
  (sum-iterative identity a inc b))

(defn factorial-iterative [n]
  (prod-iterative identity 1 inc n))

(comment
  (sumatoria-iterative 1 10) ;; => 55
  (factorial-iterative 5)    ;; => 120
  )
