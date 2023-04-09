(ns exercise-3.6)

(def seed (atom 1))

(defn rand-update [x]
  (let [A 11
        B 13
        m 101]
    (mod (+ (* A x) (* B x)) m)))

(defn rand2 [op]
  (cond
    (= op 'reset)
    (fn [new-val]
      (reset! seed new-val))

    (= op 'generate)
    (swap! seed rand-update)))

((rand2 'reset) 0)
(rand2 'generate)
