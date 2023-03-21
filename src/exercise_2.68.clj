(ns exercise-2.68
  (:require [common :refer [third fourth]]
            [exercise-2.59 :refer [element-of-set?]]))

(defn left-branch [tree]
  (first tree))

(defn right-branch [tree]
  (second tree))

(defn make-leaf [symbol weight]
  (list 'leaf symbol weight))

(defn leaf? [object]
  (= (first object) 'leaf))

(defn symbol-leaf [x]
  (second x))

(defn weight-leaf [x]
  (third x))

(defn symbols [tree]
  (if (leaf? tree)
    (list (symbol-leaf tree))
    (third tree)))

(defn weight [tree]
  (if (leaf? tree)
    (weight-leaf tree)
    (fourth tree)))

(defn make-code-tree [left right]
  (list left
        right
        (concat (symbols left)
                (symbols right))
        (+ (weight left) (weight right))))

(defn choose-branch [bit branch]
  (cond
    (= bit 0) (left-branch branch)
    (= bit 1) (right-branch branch)
    :else (throw (Exception. (format "bad bit: %s CHOOSE-BRANCH" bit)))))

(defn decode [bits tree]
  (letfn [(decode-1 [bits current-branch]
            (if (empty? bits)
              '()
              (let [next-branch (choose-branch (first bits) current-branch)]
                (if (leaf? next-branch)
                  (cons (symbol-leaf next-branch)
                        (decode-1 (rest bits) tree))
                  (decode-1 (rest bits) next-branch)))))]
    (decode-1 bits tree)))

(defn encode [message tree]
  (letfn [(encode-1 [message current-branch bits]
            (cond
              (empty? message)
              bits

              (leaf? current-branch)
              (encode-1 (rest message) tree bits)

              (element-of-set? (first message) (symbols (left-branch current-branch)))
              (encode-1 message (left-branch current-branch) (conj bits 0))

              (element-of-set? (first message) (symbols (right-branch current-branch)))
              (encode-1 message (right-branch current-branch) (conj bits 1))))]

    (encode-1 message tree [])))

(comment
  (def sample-tree
    (make-code-tree (make-leaf 'A 4)
                    (make-code-tree (make-leaf 'B 2)
                                    (make-code-tree (make-leaf 'D 1) (make-leaf 'C 1)))))

  (def sample-message '(0 1 1 0 0 1 0 1 0 1 1 1 0))

  (= '(A D A B B C A) (decode sample-message sample-tree))
  (= '(0 1 1 0 0 1 0 1 0 1 1 1 0) (encode (decode sample-message sample-tree) sample-tree)))
