# kafka.transit

Tools for using Transit with Kafka.

<!-- toc -->

- [Library](#library)
  * [Installation](#installation)
  * [Usage](#usage)
- [Command-Line Tools](#command-line-tools)
  * [Installation](#installation-1)
  * [Usage](#usage-1)

<!-- tocstop -->

## Library

### Installation

To use the Transit serde, add this library as a dependency to your project:

```clojure
[rads/kafka.transit "0.2.0"]
```

### Usage

```clojure
(ns kafka-transit-example
 (:import
   (java.util Properties)
   (org.apache.kafka.streams StreamsConfig KafkaStreams)
   (org.apache.kafka.streams.kstream KStreamBuilder)
   (rads.kafka.transit MsgpackSerde)))

(def props
  (doto (Properties.)
    (.put StreamsConfig/KEY_SERDE_CLASS_CONFIG MsgpackSerde)
    (.put StreamsConfig/VALUE_SERDE_CLASS_CONFIG MsgpackSerde)))

(def builder (KStreamBuilder.))

(.start (KafkaStreams. builder props))
```

## Command-Line Tools

### Installation

To use the command-line tools clone the Git repository to your local machine:

```shell
git clone git@github.com:rads/kafka.transit.git ~/lib/kafka.transit
cd ~/lib/kafka.transit
```

Now the scripts are available at `bin/`. For convenience you can create symlinks to these scripts or add `bin/` to your `$PATH`.

### Usage

These scripts are just like the default console producer and consumers except they use Transit for serialization instead of Avro or JSON.

```shell

# Produce to a Transit topic
bin/kafka-transit-console-producer --zookeeper localhost:2181 --topic transit-test

# Consume from a Transit topic
bin/kafka-transit-console-consumer --bootstrap-server localhost:9092 --topic transit-test --from-beginning
```
