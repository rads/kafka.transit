(ns rads.kafka.transit.MsgpackSerde
  (:require
    [franzy.serialization.transit.serializers :as serializers]
    [franzy.serialization.transit.deserializers :as deserializers])
  (:gen-class
    :implements [org.apache.kafka.common.serialization.Serde]
    :constructors {[] []}))

(defn -serializer [_]
  (serializers/transit-serializer :msgpack))

(defn -deserializer [_]
  (deserializers/transit-deserializer :msgpack))

(defn -configure [_ _ _])

(defn -close [_])

