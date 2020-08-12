(ns mire.rooms)

(def rooms
  {:start {:desc "você se encontra em uma sala circular com um pilar no meio"
           :exits {:north :closet}}
   :closet {:desc "você está em um armário apertado"
            :exits {:south :start}}})

(def ^:dynamic *current-room* {rooms :start})

(defn set-current-room [target]
  (def ^:dynamic *current-room* target))