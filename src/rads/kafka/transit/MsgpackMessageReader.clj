(ns rads.kafka.transit.MsgpackMessageReader
  (:require
    [cognitect.transit :as transit]
    [clojure.edn :as edn]
    [clojure.java.io :as io])
  (:import
    (kafka.common MessageReader)
    (java.io PushbackReader ByteArrayOutputStream InputStream)
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
        output-stream (ByteArrayOutputStream.)]
    (-> (transit/writer output-stream :msgpack)
        (transit/write (edn/read reader)))
    (let [value (.toByteArray output-stream)]
      (ProducerRecord. topic value))))

(defn -close [this]
  (let [{:keys [reader]} @(.state this)]
    (.close reader)))
