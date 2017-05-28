(ns rads.kafka.transit.MsgpackConverter
  (:require [rads.kafka.transit.util :as util])
  (:import
    (org.apache.kafka.connect.storage Converter)
    (org.apache.kafka.connect.data SchemaAndValue))
  (:gen-class
    :implements [org.apache.kafka.connect.storage.Converter]
    :constructors {[] []}))

(defn -configure [_ _ _])

(defn -fromConnectData [_ _ _ ^Object value]
  (util/serialize value))

(defn -toConnectData [_ _ ^bytes value]
  (SchemaAndValue. nil (util/deserialize value)))
