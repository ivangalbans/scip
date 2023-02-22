(ns exercise-1.42
  (:require [common]))

(defn compose
  ([f] f)
  ([f g] (fn [x] (f (g x))))
  ([f g & fs] (reduce compose (list* f g fs))))

(comment
  ((compose common/square inc) 6) ;; => 49
  )
