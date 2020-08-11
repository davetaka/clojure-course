(defproject mire-exercises "0.1.0-SNAPSHOT"
  :description "exercises from mire"
  :url ""
  :license {:name ""
            :url ""}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [server-socket "1.0.0"]]
  :main ^:skip-aot mire
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
)

