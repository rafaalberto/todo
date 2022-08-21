(ns todo.server
  (:use [clojure pprint])
  (:require [io.pedestal.http :as http]
            [com.stuartsierra.component :as component]))

(defonce server (atom nil))

(defn start-server! [service-map]
  (reset! server (http/start (http/create-server service-map))))

(defn stop-server! []
  (http/stop @server))

(defn restart-server! [service-map]
  (stop-server!)
  (start-server! service-map))

(defn try-to-start-server! [service-map]
  (try (start-server! service-map) (catch Exception ex (println "Error to start" (.getMessage ex))))
  (try (restart-server! service-map) (catch Exception ex (println "Error to restart" (.getMessage ex)))))

(defrecord Server [routes]
  component/Lifecycle

  (start [this]
    (let [service-map {::http/routes (:routes routes)
                       ::http/port   9990
                       ::http/type   :jetty
                       ::http/join?  false}
          _ (try-to-start-server! service-map)])
    (assoc this :http-server server))

  (stop [this]
    (assoc this :http-server nil)))

(defn set-server []
  (->Server {}))