(ns exercise-1.43
  (:require [common]
            [exercise-1.42]))

(defn repeated [f n]
  (reduce exercise-1.42/compose (repeat n f)))

(comment
  ((repeated common/square 2) 5) ;; => 625
  )
