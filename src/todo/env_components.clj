(ns todo.env-components
  (:require [com.stuartsierra.component :as component]
            [todo.routes :as routes]
            [todo.server :as server])
  (:use [clojure.pprint]))

(defn local-environment []
  (component/system-map
    :routes (routes/set-routes)
    :http-server (component/using (server/set-server) [:routes])))