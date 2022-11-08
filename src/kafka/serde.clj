(ns kafka.serde
  (:import [org.apache.kafka.common.serialization Serdes Serde Serializer Deserializer StringSerializer StringDeserializer])
  (:require [clojure.data.json :as json])
  (:gen-class))
(deftype Desserializador []
  Deserializer
  (close [_])
  (configure [_ configs isKey])
  (deserialize [_ topic data]
    (json/read-str (.deserialize (StringDeserializer.) topic data) :key-fn keyword)))
(deftype Serializador []
  Serializer
  (close [_])
  (configure [_ configs isKey])
  (serialize [_ topic data]
    (.serialize (StringSerializer.) topic (json/write-str data))))
(deftype novo-serde []
  Serde
  (close [_])
  (configure [_ configs isKey])
  (deserializer [_] (Desserializador.))
  (serializer [_] (Serializador.)))
(defn serde-personalizado []
  (novo-serde.))