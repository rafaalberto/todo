(defproject todo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [io.pedestal/pedestal.service "0.5.10"]
                 [io.pedestal/pedestal.route "0.5.10"]
                 [io.pedestal/pedestal.jetty "0.5.10"]
                 [org.slf4j/slf4j-simple "1.7.36"]
                 [org.clojure/data.json "0.2.6"]
                 [midje "1.10.5"]
                 [nubank/matcher-combinators "3.5.0"]
                 [com.stuartsierra/component "1.1.0"]]
  :plugins [[lein-midje "3.2.2"]]
  :repl-options {:init-ns todo.core})
