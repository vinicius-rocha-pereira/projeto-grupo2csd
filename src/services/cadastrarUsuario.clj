(ns services.cadastrarUsuario (:gen-class)
    (:require [cassandra.config :refer [insert select]])
    (:gen-class))
(defn cadastrar-usuarios [data]
  (insert "tbl_usuarios" (dissoc data :situacao))
  (assoc data :situacao "processado"))
(defn validar-dados-usuario [data])
