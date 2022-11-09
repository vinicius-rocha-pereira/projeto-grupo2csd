(ns kafka.core
  (:import
   [java.util Properties]
   [org.apache.kafka.common.serialization Serdes]
   [org.apache.kafka.streams KafkaStreams StreamsConfig Topology])
  (:require
   [kafka.processor :refer [suplier]]
   [kafka.serde :refer [serde-personalizado]])
  (:gen-class))
(def props
  (doto (Properties.)
    (.putAll
     {StreamsConfig/APPLICATION_ID_CONFIG "trt-topology"
      StreamsConfig/BOOTSTRAP_SERVERS_CONFIG "host.docker.internal:29092"
      StreamsConfig/DEFAULT_KEY_SERDE_CLASS_CONFIG (.getClass (Serdes/String))
      StreamsConfig/DEFAULT_VALUE_SERDE_CLASS_CONFIG (.getClass (serde-personalizado))})))
(defn topology []
  (doto (Topology.)
    (.addSource "cmd-in" (into-array String ["cadastrar-usuarios" "fazer-login" "cadastrar-cliente" "cadastrar-livro" "fazer-movimentacao"]))
    (.addProcessor "processador" (suplier) (into-array String ["cmd-in"]))
    (.addSink "cmd-cad-usr" "cadastrar-usuarios" (into-array String ["processador"]))
    (.addSink "cmd-cad-cli" "cadastrar-cliente" (into-array String ["processador"]))
    (.addSink "cmd-cad-lvr" "cadastrar-livro" (into-array String ["processador"]))
    (.addSink "cmd-exec-lgn" "fazer-login" (into-array String ["processador"]))
    (.addSink "cmd-exec-mvn" "fazer-movimentacao" (into-array String ["processador"]))))
(defn iniciar-kafka-stream []
  (.start (KafkaStreams. (topology) props)))