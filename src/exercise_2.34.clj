(ns exercise-2.34)

(defn accumulate [f initial sequence]
  (if (empty? sequence)
    initial
    (f (first sequence)
       (accumulate f initial (rest sequence)))))

(defn horner-eval
  [x coefficient-sequence]
  (accumulate
   (fn [item acc]
     (+ item (* x acc)))
   0
   coefficient-sequence))

(comment
  (= 79 (horner-eval 2 (list 1 3 0 5 0 1))))
