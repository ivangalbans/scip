(ns exercise-2.75
  (:require [common :refer [square]]))

(defn make-from-real-imag
  [x y]
  (fn [op]
    (cond
      (= op 'real-part) x
      (= op 'imag-part) y
      (= op 'magnitude) (Math/sqrt (+ (square x) (square y)))
      (= op 'angle)     (Math/atan y #_x) ;; TODO: discuss
      :else             (throw (Exception. (format "Unknown operation %s" op))))))

(comment
  (def polar-number (make-from-real-imag 2 3))
  (= 2 (polar-number 'real-part))
  (= 3 (polar-number 'imag-part)))

(defn make-from-mag-ang
  [r a]
  (fn [op]
    (cond
      (= op 'real-part) (* r (Math/cos a))
      (= op 'imag-part) (* r (Math/sin a))
      (= op 'magnitude) r
      (= op 'angle)     a
      :else (throw (Exception. (format "Unknown operation %s" op))))))

(comment
  (def polar-number (make-from-mag-ang ((make-from-real-imag 2 3) 'magnitude)
                                       ((make-from-real-imag 2 3) 'angle)))
  (= 2 (polar-number 'real-part))
  (= 3 (polar-number 'imag-part)))
