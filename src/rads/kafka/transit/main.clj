(ns rads.kafka.transit.main
  (:gen-class)
  (:import (kafka.tools ConsoleConsumer ConsoleProducer)))

(defn- combined-args [user-args prefix-args]
  (into-array String (concat prefix-args user-args)))

(defn -main [class-name & user-args]
  (case class-name
    "consumer" (let [prefix-args ["--formatter" "rads.kafka.transit.MsgpackMessageFormatter"]]
                 (ConsoleConsumer/main (combined-args user-args prefix-args)))
    "producer" (let [prefix-args ["--line-reader" "rads.kafka.transit.MsgpackMessageReader"]]
                 (ConsoleProducer/main (combined-args user-args prefix-args)))))
