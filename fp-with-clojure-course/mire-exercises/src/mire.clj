(ns mire
  (:require [clojure.java.io :as io]
            [mire.commands :as cmd]
            [server.socket :refer (create-server)]))

(def prompt "> ")

(defn -main [in out]
  (binding [*in* (io/reader in)
            *out* (io/writer out)]
    (print prompt) (flush)
    (loop [input (read-line)]
      (println (cmd/execute input))
      (print prompt)
      (flush)
      (recur (read-line)))))

(def server (create-server 3333 -main))

