(ns todo.controller.task
  (:require [todo.logic.task :as l.task]
            [todo.database.task :as d.task]
            [clojure.data.json :as json])
  (:import (java.util UUID)))

(defn- uuid->string [key value]
  (if (= key :id)
    (str value)
    value))

(defn- string->uuid [value]
  (UUID/fromString value))

(defn get-all [_]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str (d.task/find-all)
                            :value-fn uuid->string)})

(defn get-one [{:keys [path-params]}]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str (d.task/find-one (string->uuid (:id path-params)))
                            :value-fn uuid->string)})

(defn- create-task [title description]
  (d.task/insert (l.task/create title description)))

(defn create [{{:keys [title description]} :json-params}]
  {:status  201
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str (create-task title description)
                            :value-fn uuid->string)})