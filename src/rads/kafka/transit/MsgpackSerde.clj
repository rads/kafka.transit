(ns rads.kafka.transit.MsgpackSerde
  (:import (rads.kafka.transit MsgpackSerializer MsgpackDeserializer))
  (:gen-class
    :implements [org.apache.kafka.common.serialization.Serde]
    :constructors {[] []}))

(defn -serializer [_]
  (MsgpackSerializer.))

(defn -deserializer [_]
  (MsgpackDeserializer.))

(defn -configure [_ _ _])

(defn -close [_])

