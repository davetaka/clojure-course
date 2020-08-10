(ns mire-exercises.core
  (:require [clojure.java.io :as io]
            [server.socket :refer (create-server)]))

(defn -main [in out]
  (binding [*in* (io/reader in)
            *out* (io/writer out)]
    (println "alguém conectou")
    (loop []
      (println (read-line))
      (flush)
      (recur))))

(def server (create-server 3333 -main))

