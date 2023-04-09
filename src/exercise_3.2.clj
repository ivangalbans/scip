(ns exercise-3.2)

(defn make-monitored
  [f]
  (let [calls (atom 0)]
    (fn [x]
      (cond
        (= x 'how-many-calls?) @calls
        :else (do (swap! calls inc)
                  (f x))))))

(defn sqrt [x]
  (Math/sqrt x))

(def s (make-monitored sqrt))

(s 100)
(s 'how-many-calls?)
