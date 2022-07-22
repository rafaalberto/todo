(ns todo.logic.task)

(defn get-all []
  [{:title "Create API" :description "Create Clojure API"}])

(defn get-one [id]
  {:title (str "Clojure API " id) :description "Getting Clojure"})

(defn create [title description]
  {:id          (random-uuid)
   :title       title
   :description description})