(ns exercise-1.12)

(defn pascal-triangle [n]
  (case n
    1 '(1)
    2 '(1 1)
    (let [prev (pascal-triangle (dec n))]
      (concat [1] (map + prev (rest prev))  [1]))))

(comment
  (pascal-triangle 1) ;; => (1)
  (pascal-triangle 2) ;; => (1 1)
  (pascal-triangle 3) ;; => (1 2 1)
  (pascal-triangle 4) ;; => (1 3 3 1)
  (pascal-triangle 5) ;; => (1 4 6 4 1)
  )
