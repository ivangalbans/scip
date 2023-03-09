(ns exercise-2.36)

(defn accumulate [f initial sequence]
  (if (empty? sequence)
    initial
    (f (first sequence)
       (accumulate f initial (rest sequence)))))

(defn accumulate-n [op init seqs]
  (if (empty? (first seqs))
    nil
    (cons (accumulate op init (first seqs))
          (accumulate-n op init (rest seqs)))))

(comment
  (def s '((1 2 3) (4 5 6) (7 8 9) (10 11 12)))
  (accumulate-n + 0 s))
