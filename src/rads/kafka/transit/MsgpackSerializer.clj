(ns rads.kafka.transit.MsgpackSerializer
  (:require [cognitect.transit :as transit])
  (:import (java.io ByteArrayOutputStream))
  (:gen-class
    :implements [org.apache.kafka.common.serialization.Serializer]
    :constructors {[] []}))

(defn -configure [_ _ _])

(defn -serialize [this ^String topic data]
  (let [output-stream (ByteArrayOutputStream.)]
    (-> (transit/writer output-stream :msgpack)
        (transit/write data))
    (.toByteArray output-stream)))

(defn -close [_])
