(ns exercise-1.34
  (:require [common]))

(defn f [g]
  (g 2))

(comment
  (f common/square)  ;; => 4
  (f #(* % (inc %))) ;; => 6

  (f f) ;; => `java.lang.ClassCastException` class java.lang.Long cannot be cast to class
        ;;    clojure.lang.IFn (java.lang.Long is in module java.base of loader 'bootstrap';
        ;;    clojure.lang.IFn is in unnamed module of loader 'app')
        ;;
  ;; Why ?
  ;; (f f) => (f 2) => (2 2)
  )
