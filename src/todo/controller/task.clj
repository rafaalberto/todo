(ns todo.controller.task
  (:require [todo.logic.task :as l.task]
            [todo.database.task :as d.task]
            [todo.utils :as utils]
            [clojure.data.json :as json]))

(defn get-all [_]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str (d.task/find-all)
                            :value-fn utils/uuid->string)})

(defn get-one [{:keys [path-params]}]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str (-> (:id path-params)
                                (utils/string->uuid)
                                (d.task/find-one))
                            :value-fn utils/uuid->string)})

(defn create [{{:keys [title description]} :json-params}]
  {:status  201
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str (-> (l.task/create title description)
                                (d.task/insert!))
                            :value-fn utils/uuid->string)})

(defn delete [{:keys [path-params]}]
  {:status  204
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str (-> (:id path-params)
                                (utils/string->uuid)
                                (d.task/delete!))
                            :value-fn utils/uuid->string)})