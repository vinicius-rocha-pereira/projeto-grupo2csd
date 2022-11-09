(ns services.cadastrarLivro (:gen-class)
    (:require [cassandra.config :refer [insert select]]
              [taoensso.timbre :as log]))
(defn cadastrar-livro [dados_livro]
  (insert "tbl_livros_genero" (dissoc dados_livro :situacao))
  (insert "tbl_livros_autor" (dissoc dados_livro :situacao))
  (insert "tbl_livros_titulo" (dissoc dados_livro :situacao))
  (assoc dados_livro :situacao "processado")
  )
