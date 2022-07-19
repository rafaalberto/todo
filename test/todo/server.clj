(ns todo.server
  (:require [midje.sweet :refer :all]
            [todo.integration-template :as integration]))

(against-background
  [(before :facts (integration/start-server))
   (after :facts (integration/stop-server))]
  (fact "Should get all tasks"
        (:body (integration/test-request :get "/task")) => "[{\"title\":\"Create API\",\"description\":\"Create Clojure API\"}]")
  (fact "Should get one task"
        (:body (integration/test-request :get "/task/1")) => "{\"title\":\"Clojure API 1\",\"description\":\"Getting Clojure\"}")
  (fact "Should create task"
        (:body (integration/test-request :post "/task" "{\"title\":\"Clojure API 1\"}")) =>
        "{\"title\":\"Clojure API 1\"}"))