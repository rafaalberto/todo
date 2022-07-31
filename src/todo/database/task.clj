(ns todo.database.task)

(def tasks (atom []))

(defn find-all []
  @tasks)

(defn find-one [id]
  (some #(if (= (:id %) id) %) @tasks))

(defn insert [task]
  (swap! tasks conj task))
