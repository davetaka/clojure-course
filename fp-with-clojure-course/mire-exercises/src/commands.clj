(ns commands
  (:require [clojure.string :as str]))

(defn current-time []
  (str "Agora é " (java.util.Date.)))

(def commands {"time" current-time
               "look" (fn [] "Você vê uma sala vazia, esperando para ser preenchida")})

(defn execute 
  "Execute a command that is passed to us."
  [input] 
  (let [input-words (str/split input #"\s+")
        command (first input-words)
        args (rest input-words)]
    (apply (commands command) args)))