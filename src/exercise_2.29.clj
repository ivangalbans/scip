(ns exercise-2.29)

(defn make-mobile [left right]
  [left right])

(defn make-branch [length structure]
  [length structure])

(defn left-branch
  [mobile]
  (when (sequential? mobile)
    (first mobile)))

(defn right-branch
  [mobile]
  (when (sequential? mobile)
    (second mobile)))

(defn branch-length
  [branch]
  (when (sequential? branch)
    (first branch)))

(defn branch-structure
  [branch]
  (when (sequential? branch)
    (second branch)))

(defn total-weight
  [mobile]
  (cond
    (nil? mobile) 0
    (not (sequential? mobile)) mobile
    :else (+ (total-weight (branch-structure (left-branch mobile)))
             (total-weight (branch-structure (right-branch mobile))))))

(comment
  (= 8 (total-weight (make-mobile (make-branch 1 3)
                                  (make-branch 1 5))))

  (= 12 (total-weight (make-mobile (make-branch 1 3)
                                   (make-branch 1 (make-mobile (make-branch 1 4)
                                                               (make-branch 1 5)))))))

(defn balanced?
  [mobile]
  (let [left-branch     (left-branch mobile)
        right-branch    (right-branch mobile)
        left-structure  (branch-structure left-branch)
        right-structure (branch-structure right-branch)]
    (if-not (sequential? mobile)
      true
      (and (= (* (branch-length left-branch) (total-weight left-structure))
              (* (branch-length right-branch) (total-weight right-structure)))
           (balanced? left-structure)
           (balanced? right-structure)))))

(comment
  (= false (balanced? (make-mobile (make-branch 1 3)
                                   (make-branch 1 5))))

  (= true (balanced? (make-mobile (make-branch 1 3)
                                  (make-branch 1 3))))

  (= true (balanced? (make-mobile (make-branch 1 (make-mobile (make-branch 2 3) (make-branch 2 3)))
                                  (make-branch 1 (make-mobile (make-branch 2 3) (make-branch 2 3)))))))
