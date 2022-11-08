(ns projeto-grupo2csd.core
  (:require 
   [kafka.core :refer [iniciar-kafka-stream]]
   [taoensso.timbre :as log]
   )
  (:gen-class))
(defn -main [& args]
  ;; (log/info "")
  (iniciar-kafka-stream))