(ns exercise-2.59)

(defn element-of-set? [x set]
  (cond
    (empty? set) false
    (= x (first set)) true
    :else (element-of-set? x (rest set))))

(comment
  (= false (element-of-set? 3 '()))
  (= false (element-of-set? 3 '(1 2 4)))
  (= true  (element-of-set? 3 '(1 2 3 4))))

(defn adjoin-set [x set]
  (if (element-of-set? x set)
    set
    (cons x set)))

(comment
  (= '(3)       (adjoin-set 3 '()))
  (= '(3 1 2 4) (adjoin-set 3 '(1 2 4)))
  (= '(1 2 3 4) (adjoin-set 3 '(1 2 3 4))))

(defn intersection-set [set1 set2]
  (cond
    (or (empty? set1) (empty? set2))
    '()

    (element-of-set? (first set1) set2)
    (cons (first set1)
          (intersection-set (rest set1)
                            set2))

    :else (intersection-set (rest set1)
                            set2)))

(comment
  (= '() (intersection-set '(1 2) '(3 4)))
  (= '(2) (intersection-set '(1 2) '(3 2 4))))

(defn union-set [set1 set2]
  (cond
    (empty? set1) set2
    (empty? set2) set1

    (element-of-set? (first set1) set2)
    (union-set (rest set1) set2)

    :else (cons (first set1)
                (union-set (rest set1) set2))))

(comment
  (= '(1 2 3 4) (union-set '(1 2) '(3 4)))
  (= '(1 3 2 4) (union-set '(1 2) '(3 2 4))))
