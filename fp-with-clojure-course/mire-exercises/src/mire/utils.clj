(ns mire.utils)

(defn pick-rand
  "retorna um elemento aleatorio de algum vetor"
  [vect]
  (vect (rand-int (count vect))))