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
    :init init-state
    :state state
    :constructors {[] []}))

(defn -init [this ^Properties props]
  (swap! (.state this) assoc :props props))

(defn -init-state []
  [[] (atom {:props nil})])

(defn read-transit [value]
  (let [input-stream (ByteArrayInputStream. value)
        reader (transit/reader input-stream :msgpack)]
    (transit/read reader)))

(defn -writeTo [this ^ConsumerRecord record ^PrintStream output]
  (when (.get (:props @(.state this)) "print.key")
    (.print output (str (pr-str (read-transit (.key record))) " ")))
  (.println output (pr-str (read-transit (.value record)))))

(defn -close [this])
