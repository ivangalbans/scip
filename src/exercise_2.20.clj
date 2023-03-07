(ns exercise-2.20)

(defn same-parity
  [x & xs]
  (let [pred (if (even? x) even? odd?)]
    (cons x (filter pred xs))))

(same-parity 1 2 3 4)

(comment
  (= '(1 3 5 7) (same-parity 1 2 3 4 5 6 7))
  (= '(2 4 6) (same-parity 2 3 4 5 6 7)))
