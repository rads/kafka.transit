(ns rads.kafka.transit.MsgpackMessageFormatter
  (:require
    [cognitect.transit :as transit]
    [clojure.java.io :as io])
  (:import
    (kafka.common MessageFormatter)
    (java.io PrintStream ByteArrayInputStream)
    (java.util Properties)
    (org.apache.kafka.clients.consumer ConsumerRecord))
  (:gen-class
    :implements [kafka.common.MessageFormatter]
    :constructors {[] []}))

(defn -init [this ^Properties props])

(defn -writeTo [this ^ConsumerRecord record ^PrintStream output]
  (let [input-stream (ByteArrayInputStream. (.value record))
        reader (transit/reader input-stream :msgpack)]
    (.println output (pr-str (transit/read reader)))))

(defn -close [this])
