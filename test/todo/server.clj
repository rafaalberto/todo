(ns todo.server
  (:require [midje.sweet :refer :all]
            [matcher-combinators.midje :refer [match]]
            [clojure.data.json :as json]
            [todo.integration-template :as integration]
            [todo.database.task :as d.task]))

(defn create-task! [payload]
  (json/read-str (:body (integration/test-request :post "/task" payload)) :key-fn keyword))

(against-background
  [(before :contents [(integration/start-server)
                      (d.task/clear!)])
   (after :contents (integration/stop-server))]
  (fact "Should create a task"
        (let [task-created (create-task! "{\"title\":\"Clojure API 1\",\"description\":\"API 1\"}")]
          task-created => (match {:title "Clojure API 1" :description "API 1"})

          (fact "Should get one task"
                (let [task-id (:id task-created)]
                  (json/read-str (:body (integration/test-request :get (str "/task/" task-id))) :key-fn keyword)
                  => (match {:id task-id :title "Clojure API 1" :description "API 1"})))

          (fact "Should returns empty when there is no task"
                (let [task-id (random-uuid)]
                  (json/read-str (:body (integration/test-request :get (str "/task/" task-id))) :key-fn keyword)
                  => (match {}))))))
