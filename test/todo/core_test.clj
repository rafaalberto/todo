;(ns todo.core-test
;  (:require [clojure.test :refer :all]
;            [todo.core :refer :all]
;            [todo.logic.task :as l.task]))
;
;(deftest insert-test
;  (let [uuid (random-uuid)
;        title "Develop App"
;        description "Create a new App"]
;    (testing "Insert a new tasks"
;      (is (= {:id          uuid
;              :title       title
;              :description description}
;             (l.task/insert uuid title description))))))
;
;(deftest add-test
;  (l.task/clear)
;  (let [uuid (random-uuid)]
;    (is (= [{:id          uuid
;             :title       "A"
;             :description "B"}]
;           (l.task/add {:id          uuid
;                        :title       "A"
;                        :description "B"})))))
