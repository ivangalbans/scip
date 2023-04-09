(ns exercise-3.1)

(defn make-accumulator [initial]
  (let [sum (volatile! initial)]
    (fn [n]
      (vswap! sum + n))))

(def A1 (make-accumulator 5))

(A1 10)

(def B1 (make-accumulator 5))

(B1 10)

(defn make-accumulator2 [initial]
  (let [sum (atom initial)]
    (fn [n]
      (swap! sum + n))))

(def A2 (make-accumulator2 5))
(= 15 (A2 10))
(= 25 (A2 10))

(def B2 (make-accumulator2 5))
(= 15 (B2 10))
(= 25 (B2 10))

(comment

  ;; with dynamic
  (def A2 (make-accumulator 5))
  (= 15 (A2 10))
  (= 25 (A2 10))

;; with atom
  (def A2 (make-accumulator2 5))
  (= 15 (A2 10))
  (= 25 (A2 10)))
