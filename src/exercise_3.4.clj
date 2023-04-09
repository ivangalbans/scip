(ns exercise-3.4)

(defn make-account-with-password [initial-balance password call-the-cops]
  (let [balance (atom initial-balance)
        stored-password password
        counter (atom 0)
        withdraw (fn [amount]
                   (if (>= @balance amount)
                     (swap! balance - amount)
                     (throw (Exception. "Insufficient funds"))))

        deposit (fn [amount]
                  (swap! balance + amount))

        dispatch (fn [password m]
                   (if (= password stored-password)
                     (do
                       (reset! counter 0)
                       (cond
                         (= m 'withdraw) withdraw
                         (= m 'deposit) deposit
                         :else (throw (Exception. (format "Unknown request: MAKE-ACCOUNT %s" m)))))
                     (do
                       (swap! counter inc)
                       (when (= @counter 7)
                         (call-the-cops))
                       (constantly "Incorrect password"))))]
    dispatch))

(comment
  (def psw-acc (make-account-with-password 100 'secret-password (fn [] (println "Call the cops"))))

  ((psw-acc 'secret-password 'withdraw) 40)     ;; => 60

  (doseq [_ (range 7)]
    ((psw-acc 'some-other-password 'withdraw) 40)))
