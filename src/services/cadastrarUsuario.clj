(ns services.cadastrarUsuario (:gen-class)
    (:require [cassandra.config :refer [insert select]])
    (:gen-class))
(defn cadastrar-usuarios [data]
  (insert "tbl_usuarios" (dissoc data :situacao))
  (assoc data :situacao "processado"))

;; {"cpf": "25465845500", "senha": "maria123", "bairro": "Mariana", "cidade": "São paulo", "complemento": "Apartamento", "contato": "11945621234", "data_nascimento": "1990-11-28", "email": "joao.rocha@oulook.com", "nome": "Jõao Rocha", "numero": "2524", "rua": "Mariana", "situacao":"pendente"}