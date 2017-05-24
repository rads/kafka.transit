(def project 'rads/kafka.transit)
(def version "0.3.0-SNAPSHOT")

(set-env!
  :source-paths #{"src"}
  :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"}])
  :dependencies
  '[[org.clojure/clojure "1.8.0"]
    [com.cognitect/transit-clj "0.8.300"]
    [org.apache.kafka/kafka_2.11 "0.10.2.1" :exclusions [org.slf4j/slf4j-log4j12]]])

(task-options!
 pom {:project project
      :version version
      :description "Tools for using Transit with Kafka"
      :url "https://github.com/rads/kafka.transit"
      :scm {:url "https://github.com/rads/kafka.transit"}
      :license {"MIT License"
                "https://opensource.org/licenses/MIT"}}
 aot {:namespace #{'rads.kafka.transit.MsgpackMessageReader
                   'rads.kafka.transit.MsgpackMessageFormatter
                   'rads.kafka.transit.MsgpackSerde}})

(deftask build
  "Build and install the project locally."
  []
  (comp
    (aot)
    (pom)
    (jar)
    (install)))

(deftask uberjar
  []
  (comp
    (aot)
    (uber)
    (jar :file "project.jar")
    (sift :include #{#"project.jar"})
    (target)))
