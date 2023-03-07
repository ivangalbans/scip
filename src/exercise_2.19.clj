(ns exercise-2.19)

(def first-denomination first)

(def except-first-denomination rest)

(def no-more? empty?)

(defn cc [amount coin-values]
  (cond
    (= amount 0)
    1

    (or (< amount 0)
        (no-more? coin-values))
    0

    :else
    (+ (cc
        amount
        (except-first-denomination
         coin-values))
       (cc
        (- amount
           (first-denomination
            coin-values))
        coin-values))))

(comment
  (def us-coins (list 50 25 10 5 1))
  (= 292 (cc 100 us-coins)))
