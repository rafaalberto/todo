(ns todo.server
  (:require [midje.sweet :refer :all]
            [matcher-combinators.midje :refer [match]]
            [clojure.data.json :as json]
            [todo.integration-template :as integration]
            [todo.database.task :as d.task]))

(against-background
  [(before :contents [(integration/start-server)
                      (d.task/clear!)])
   (after :contents (integration/stop-server))]
  (fact "Should create task"
        (json/read-str (:body (integration/test-request :post "/task"
                                                        "{\"title\":\"Clojure API 1\",\"description\":\"API 1\" }")) :key-fn keyword)
        => (match {:title "Clojure API 1" :description "API 1"}))
  (fact "Should get all tasks"
        (json/read-str (:body (integration/test-request :get "/task")) :key-fn keyword)
        => (match [{:title "Clojure API 1" :description "API 1"}]))
  #_(fact "Should get one task"
          (json/read-str (:body (integration/test-request :get "/task/1")) :key-fn keyword)
          => (match {:title "Clojure API 1" :description "Getting Clojure"})))