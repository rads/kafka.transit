(ns rads.kafka.transit.MsgpackMessageFormatter
  (:require
    [clojure.java.io :as io]
    [rads.kafka.transit.util :as util])
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

(defn -writeTo [this ^ConsumerRecord record ^PrintStream output]
  (when (.get (:props @(.state this)) "print.key")
    (.print output (str (pr-str (util/deserialize (.key record))) " ")))
  (.println output (pr-str (util/deserialize (.value record)))))

(defn -close [_])
