(ns todo.server
  (:use [clojure pprint])
  (:require [io.pedestal.http :as http]
            [todo.routes :as routes]))

(def service-map {::http/routes (routes/set-routes)
                  ::http/port   9990
                  ::http/type   :jetty
                  ::http/join?  false})

(def server (atom nil))

(defn start-server []
  (reset! server (http/start (http/create-server service-map))))

(start-server)