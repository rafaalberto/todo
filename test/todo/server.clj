(ns todo.server
  (:require [midje.sweet :refer :all]
            [matcher-combinators.matchers :as m]
            [matcher-combinators.midje :refer [match]]
            [clojure.data.json :as json]
            [todo.integration-template :as integration]))

(against-background
  [(before :facts (integration/start-server))
   (after :facts (integration/stop-server))]
  (fact "Should get all tasks"
        (json/read-str (:body (integration/test-request :get "/task")) :key-fn keyword)
        => (match [{:title "Create API" :description "Create Clojure API"}]))
  (fact "Should get one task"
        (json/read-str (:body (integration/test-request :get "/task/1")) :key-fn keyword)
        => (match {:title "Clojure API 1" :description "Getting Clojure"}))
  (fact "Should create task"
        (json/read-str (:body (integration/test-request :post "/task" "{\"title\":\"Clojure API 1\"}")) :key-fn keyword)
        => (match {:title "Clojure API 1"})))