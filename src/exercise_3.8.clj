(ns exercise-3.8)

(def initial (atom nil))

(defn f [x]
  (when-not @initial
    (reset! initial x))
  (println x)
  (if (= @initial x)
    x
    0))

(comment
  (def initial (atom nil))
  (+ (f 0) (f 1)) ;; => 0

  (def initial (atom nil))
  (+ (f 1) (f 0)) ;; => 1
  )
