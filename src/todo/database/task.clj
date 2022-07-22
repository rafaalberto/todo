(ns todo.database.task)

(def tasks (atom []))

(defn find-all []
  @tasks)

(defn insert [task]
  (swap! tasks conj task))