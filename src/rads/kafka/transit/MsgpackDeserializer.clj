(ns rads.kafka.transit.MsgpackDeserializer
  (:require [rads.kafka.transit.util :as util])
  (:import
    (org.apache.kafka.common.serialization Deserializer))
  (:gen-class
    :implements [org.apache.kafka.common.serialization.Deserializer]
    :constructors {[] []}))

(defn -configure [_ _ _])

(defn -deserialize [_ _ ^bytes data]
  (util/deserialize data))

(defn -close [_])
