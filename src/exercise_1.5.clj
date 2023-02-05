(ns exercise-1.5
  (:refer-clojure :exclude [test]))

;; What behavior is observed with an interpreter that uses applicative-order vs normal-order evaluation ?

(defn p []
  (p))

(defn test [x y]
  (if (= x 0)
    0
    y))

;; `applicative-order`

(comment

  (test 0 (p)) ;; => Stack Overflow

  (if (= 0 0)
    0
    (p))

  (if (= 0 0)
    0
    (p))

  ;; ...
  )
;; `normal-order`

(comment

  (test 0 (p))

  (if (= 'x 0)
    0
    (p))

  (if (= 0 0)
    0
    (p))

  0)

(defn A []
  (println "A")
  1)

(defn B [x]
  (println "B")
  (if true
    x
    nil))

;; applicative-order evaluation

(B (A)) ;; => "Output: A B"
