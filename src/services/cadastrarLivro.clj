(ns services.cadastrarLivro (:gen-class)
    (:require [cassandra.config :refer [insert select]]))
(defn cadastrar-livro [data]
;;So enviar para o inser um mapa que tenha somente as chaves com os mesmos nomes das colunas da tabela
  (insert "nome-da-tabela", data)
;;retornar o 'data' que veio de argumento;
  )