(ns mire.commands
  (:require [clojure.string :as cstr]
            [mire.rooms :as roms]
            [mire.utils :as utils]))

(defn look 
  "obtem uma descrição do que tem na sala atual"
  [] 
  (str (:desc roms/*current-room*)
       "\nSaída: " (keys (:exits roms/*current-room*))
       "\n."))

(defn move
  "Nós temos que sair daqui, mande seus comandos"
  [direction]
  (let [target-name ((:exits roms/*current-room*) (keyword direction))
        target (roms/rooms target-name)]
    (if target 
      (do (roms/set-current-room target)
          (look))
      "Você não pode ir nessa direção")))

(def commands {"move" move
               "north" #(move :north)
               "south" #(move :south)
               "east" #(move :east)
               "west" #(move :west)
               "look" look})

(defn execute 
  "executa qualquer comando pelo nome"
  [input] 
  (let [input-words (cstr/split input #"\s+")
        command (first input-words)
        args (rest input-words)]
    (apply (commands command) args)))