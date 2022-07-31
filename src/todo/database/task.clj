(ns todo.database.task
  (:import (java.util UUID)))

(def tasks (atom []))

(defn find-all []
  @tasks)

(defn find-one [id]
  (some #(if (= (:id %) id) %) @tasks))

(defn insert [task]
  (swap! tasks conj task))
