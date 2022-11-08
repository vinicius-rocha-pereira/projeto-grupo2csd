(ns kafka.processor
  (:import [org.apache.kafka.streams.processor Processor ProcessorSupplier To])
  (:require
   [services.cadastrarUsuario :refer [cadastrar-usuarios]]
   [services.cadastrarCliente :refer [cadastrar-cliente]]
   [services.cadastrarLivro :refer [cadastrar-livro]]
   [services.fazerLogin :refer [fazer-login]]
   [services.fazerMovimentacao :refer [fazer-movimentacao]]
   )
  (:gen-class))
(deftype Processador [^:volatile-mutable context]
  Processor
  (close [_])
  (init [_ c]
    (set! context c))
  (process [_ chave valor]
    (case (.topic context)
      "cadastrar-usuarios"
      (when (= (:situacao valor) "pendente")
        (.forward context (:cpf valor) (cadastrar-usuarios valor) (To/child "cmd-cad-usr")))
      "cadastrar-cliente"
      (when (= (:situacao valor) "pendente")
        (.forward context (:cpf valor) (cadastrar-cliente valor) (To/child "cmd-cad-cli")))
      "cadastrar-livro"
      (when (= (:situacao valor) "pendente")
        (.forward context (:cpf valor) (cadastrar-livro valor) (To/child "cmd-cad-lvr")))
      "fazer-login"
      (when (= (:situacao valor) "pendente")
        (.forward context (:cpf valor) (fazer-login valor) (To/child "cmd-exec-lgn")))
      "fazer-movimentacao"
      (when (= (:situacao valor) "pendente")
        (.forward context chave (fazer-movimentacao valor) (To/child "cmd-exec-mvn")))
      nil)))
(deftype Suplier-processador []
  ProcessorSupplier
  (get [_]
    (Processador. nil)))
(defn suplier []
  (Suplier-processador.))