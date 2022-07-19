(ns todo.integration-template
  (:require [clojure.test :refer :all]
            [io.pedestal.http :as http]
            [io.pedestal.test :as test]
            [todo.routes :as routes]))

(def service-map {::http/routes (routes/set-routes)
                  ::http/port   8888
                  ::http/type   :jetty
                  ::http/join?  false})

(def server (atom nil))

(defn start-server []
  (reset! server (http/start (http/create-server service-map))))

(defn stop-server []
  (http/stop @server))

(defn test-request [method url & body]
  (if body
    (test/response-for (::http/service-fn @server) method url :headers {"Content-Type" "application/json"} :body (-> body first str))
    (test/response-for (::http/service-fn @server) method url)))