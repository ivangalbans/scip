(ns exercise-3.7
  (:require [exercise-3.3 :refer [make-account-with-password]]))

(defn make-joint [protected-account old-password new-password]
  (let [old-password old-password
        new-password new-password
        dispatch (fn [password m]
                   (if (= password new-password)
                     (cond
                       (= m 'withdraw) (fn [amount]
                                         ((protected-account old-password 'withdraw) amount))
                       (= m 'deposit) (fn [amount]
                                        ((protected-account old-password 'deposit) amount))
                       :else (throw (Exception. (format "Unknown request: MAKE-ACCOUNT %s" m))))
                     (constantly "Incorrect password")))]
    dispatch))

(comment
  (def peter-acc (make-account-with-password 100 'open-sesame))

  ((peter-acc 'open-sesame 'withdraw) 10) ;; => 90

  (def paul-acc
    (make-joint peter-acc 'open-sesame 'rosebuld))

  ((paul-acc 'rosebuld 'withdraw) 10) ;; => 80

  ((peter-acc 'open-sesame 'withdraw) 10) ;; => 70
  )
