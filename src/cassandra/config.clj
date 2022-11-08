(ns cassandra.config
  (:gen-class)
  (:require [qbits.alia :as alia]))
(def session (alia/session {:session-keyspace "biblioteca_grupo2_csd"
                            :contact-points ["host.docker.internal:9042"]
                            :load-balancing-local-datacenter "datacenter1"}))
(defn criar-string-where [where]
  (if (map? where)
    (let [lista_strings (map (fn [chave]
                               (str " AND " (subs (str chave) 1) "= " chave))
                             (keys where))
          pre-string-where (reduce #(str %1 " " %2) lista_strings)
          string-where (subs (str pre-string-where) 5)]
      (str " WHERE " string-where ";"))
    ";"))
(defn criar-string-colunas [dados]
  (let [string-chaves (map (fn [valor]
                             (subs (str valor) 1))
                           (keys dados))]
    (str "(" (reduce #(str %1 ", " %2) string-chaves) ")")))
(defn criar-string-valores [dados]
  (let [chaves (keys dados)]
    (str "(" (reduce #(str %1 ", " %2) chaves) ")")))
(defn criar-prepare [tabela, dados]
  (let [string_colunas (criar-string-colunas dados)
        string_valores (criar-string-valores dados)
        string-prepare (str "INSERT INTO ", tabela, " ", string_colunas, " VALUES ", string_valores, ";")]
    (alia/prepare session string-prepare)))
(defn insert [tabela, dados]
  (let [prepare-insert (criar-prepare tabela, dados)]
    (alia/execute session prepare-insert {:values dados})))
(defn select [tabela, where]
  (let [string-where (criar-string-where where)
        string-select (str "SELECT * FROM " tabela string-where)
        prepare-select (alia/prepare session string-select)]
    (alia/execute session prepare-select {:values where})))
