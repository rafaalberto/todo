(ns todo.server
  (:require [midje.sweet :refer :all]
            [matcher-combinators.midje :refer [match]]
            [clojure.data.json :as json]
            [todo.integration-template :as integration]
            [todo.database.task :as d.task]))

(defn create-task! [payload]
  (json/read-str (:body (integration/test-request :post "/task" payload)) :key-fn keyword))

(defn find-one [task-id]
  (json/read-str (:body (integration/test-request :get (str "/task/" task-id))) :key-fn keyword))

(defn delete-task! [task-id]
  (json/read-str (:body (integration/test-request :delete (str "/task/" task-id))) :key-fn keyword))

(against-background
  [(before :contents [(integration/start-server)
                      (d.task/clear!)])
   (after :contents (integration/stop-server))]
  (fact "Should create a task"
        (let [task-created (create-task! "{\"title\":\"Clojure API 1\",\"description\":\"API 1\"}")]
          task-created => (match {:title "Clojure API 1" :description "API 1"})

          (fact "Should get one task"
                (let [task-id (:id task-created)]
                  (find-one task-id) => (match {:id task-id :title "Clojure API 1" :description "API 1"})))

          (fact "Should delete a task"
                (delete-task! (:id task-created)) => nil)

          (fact "Should get any task after deleted"
                          (let [task-id (:id task-created)]
                            (find-one task-id) => (match [])))

          (fact "Should returns empty when there is no task"
                (let [task-id (random-uuid)]
                  (json/read-str (:body (integration/test-request :get (str "/task/" task-id))) :key-fn keyword)
                  => (match []))))))
