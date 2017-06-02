(ns rads.kafka.transit.util
  (:require [cognitect.transit :as transit])
  (:import (java.io ByteArrayOutputStream ByteArrayInputStream)))

(defn serialize [value]
  (let [output-stream (ByteArrayOutputStream.)]
    (-> (transit/writer output-stream :msgpack)
        (transit/write value))
    (.toByteArray output-stream)))

(defn deserialize [bytes]
  (let [input-stream (ByteArrayInputStream. bytes)
        reader (transit/reader input-stream :msgpack)]
    (transit/read reader)))
