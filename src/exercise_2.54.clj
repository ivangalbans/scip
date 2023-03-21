(ns exercise-2.54)

(defn equal?
  [a b]
  (cond
    (and (symbol? a) (symbol? b)) (= a b)
    (and (empty? a)  (empty? b)) true
    (empty? a)       false
    (empty? b)       false
    :else            (and (equal? (first a) (first b))
                          (equal? (rest a) (rest b)))))

(comment
  (= true (equal? '('a '('b)) '('a '('b))))
  (= false (equal? '('a '('b)) '('a '('b 'c)))))
