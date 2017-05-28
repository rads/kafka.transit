(ns rads.kafka.transit.MsgpackSerializer
  (:require [rads.kafka.transit.util :as util])
  (:gen-class
    :implements [org.apache.kafka.common.serialization.Serializer]
    :constructors {[] []}))

(defn -configure [_ _ _])

(defn -serialize [_ _ ^Object data]
  (util/serialize data))

(defn -close [_])
