(ns todo.routes
  (:require [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [todo.controller.task :as c.task]
            [io.pedestal.http :as http]
            [com.stuartsierra.component :as component]))

(defrecord Routes []
  component/Lifecycle

  (start [this]

    (def common-interceptors [(body-params/body-params) http/json-body])

    (def routes (route/expand-routes
                  #{["/task" :get c.task/get-all :route-name :get-all-tasks]
                    ["/task/:id" :get c.task/get-one :route-name :get-one-task]
                    ["/task" :post (conj common-interceptors `c.task/create) :route-name :create-task]
                    ["/task/:id" :delete c.task/delete :route-name :delete-task]}))

    (assoc this :routes routes))

  (stop [this]
    (assoc this :routes nil)))

(defn set-routes []
  (->Routes))