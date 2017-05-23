(ns rads.kafka.transit.MsgpackSerde
  (:require [franzy.serialization.transit.serializers :as serializers]
            [franzy.serialization.transit.deserializers :as deserializers])
  (:gen-class
    :implements [org.apache.kafka.common.serialization.Serde]
    :constructors {[] []}))

(defn -serializer [this]
  (serializers/transit-serializer :msgpack))

(defn -deserializer [this]
  (deserializers/transit-deserializer :msgpack))

(defn -configure [this])

(defn -close [this])

