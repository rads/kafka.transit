(ns rads.kafka.transit.MsgpackDeserializer
  (:require [cognitect.transit :as transit])
  (:import
    (org.apache.kafka.common.serialization Deserializer)
    (java.io ByteArrayInputStream))
  (:gen-class
    :implements [org.apache.kafka.common.serialization.Deserializer]
    :constructors {[] []}))

(defn -configure [_ _ _])

(defn -deserialize [this ^String topic ^bytes data]
  (let [input-stream (ByteArrayInputStream. data)
        reader (transit/reader input-stream :msgpack)]
    (transit/read reader)))

(defn -close [_])
