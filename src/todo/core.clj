(ns todo.core
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [todo.env-components :as env-components]))

(defn -main [& _]
  (component/start (env-components/local-environment)))