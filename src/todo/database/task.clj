(ns todo.database.task)

(def tasks (atom []))

(defn find-all []
  @tasks)

(defn find-one [id]
  (let [search (some #(if (= (:id %) id) %) @tasks)]
    (if (nil? search) [] search)))

(defn insert! [task]
  (swap! tasks conj task))

(defn delete! [id]
  (let [task #(= % (find-one id))
        updated-tasks (remove task @tasks)]
    (print updated-tasks)
    (reset! tasks updated-tasks)))

(defn clear! []
  (reset! tasks []))