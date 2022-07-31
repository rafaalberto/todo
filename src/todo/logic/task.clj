(ns todo.logic.task)

(defn create [title description]
  {:id          (random-uuid)
   :title       title
   :description description})