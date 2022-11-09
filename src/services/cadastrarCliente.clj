(ns services.cadastrarCliente (:gen-class)
    (:require [cassandra.config :refer [insert select]]))
(defn cadastrar-cliente [data]
  (insert "tbl_clientes" (dissoc data :situacao))
  (assoc data :situacao "processado"))