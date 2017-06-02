(ns rads.kafka.transit.MsgpackMessageReader
  (:require
    [clojure.edn :as edn]
    [clojure.java.io :as io]
    [rads.kafka.transit.util :as util])
  (:import
    (kafka.common MessageReader)
    (java.io PushbackReader InputStream)
    (org.apache.kafka.clients.producer ProducerRecord)
    (java.util Properties))
  (:gen-class
    :init init-state
    :state state
    :implements [kafka.common.MessageReader]
    :constructors {[] []}))

(defn -init-state []
  [[] (atom {:reader nil :topic nil})])

(defn -init [this ^InputStream input-stream ^Properties props]
  (let [topic (.getProperty props "topic")
        reader (PushbackReader. (io/reader input-stream))]
    (swap! (.state this) merge {:reader reader :topic topic})))

(defn -readMessage [this]
  (let [{:keys [reader topic]} @(.state this)
        value (util/serialize (edn/read reader))]
      (ProducerRecord. topic value)))

(defn -close [this]
  (let [{:keys [reader]} @(.state this)]
    (.close reader)))
