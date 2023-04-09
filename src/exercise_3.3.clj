(ns exercise-3.3)

(defn make-account [initial-balance]
  (let [balance (atom initial-balance)
        withdraw (fn [amount]
                   (if (>= @balance amount)
                     (swap! balance - amount)
                     (throw (Exception. "Insufficient funds"))))

        deposit (fn [amount]
                  (swap! balance + amount))

        dispatch (fn [m]
                   (cond
                     (= m 'withdraw) withdraw
                     (= m 'deposit) deposit
                     :else (throw (Exception. (format "Unknown request: MAKE-ACCOUNT %s" m)))))]
    dispatch))

(comment
  (def acc (make-account 100))

  ((acc 'withdraw) 50) ;; => 50
  ((acc 'withdraw) 60) ;; => "Insufficient funds"
  ((acc 'deposit) 40)  ;; => 90
  ((acc 'withdraw) 60) ;; => 30
  )
(defn make-account-with-password [initial-balance password]
  (let [balance (atom initial-balance)
        stored-password password
        withdraw (fn [amount]
                   (if (>= @balance amount)
                     (swap! balance - amount)
                     (throw (Exception. "Insufficient funds"))))

        deposit (fn [amount]
                  (swap! balance + amount))

        dispatch (fn [password m]
                   (if (= password stored-password)
                     (cond
                       (= m 'withdraw) withdraw
                       (= m 'deposit) deposit
                       :else (throw (Exception. (format "Unknown request: MAKE-ACCOUNT %s" m))))
                     (constantly "Incorrect password")))]
    dispatch))

(comment
  (def psw-acc (make-account-with-password 100 'secret-password))

  ((psw-acc 'secret-password 'withdraw) 40)     ;; => 60
  ((psw-acc 'some-other-password 'withdraw) 40) ;; => "Incorrect password"
  )
