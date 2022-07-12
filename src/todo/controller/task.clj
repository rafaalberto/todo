(ns todo.controller.task
  (:require [todo.logic.task :as l.task]
            [clojure.data.json :as json]))

(defn get-all [_]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str (l.task/get-all))})

(defn get-one [{:keys [path-params]}]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str (l.task/get-one (:id path-params)))})

(defn create [{{:keys [title]} :json-params}]
  {:status  201
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str (l.task/create))})
