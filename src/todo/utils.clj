(ns todo.utils
  (:import (java.util UUID)))

(defn uuid->string [key value]
  (if (= key :id)
    (str value)
    value))

(defn string->uuid [value]
  (UUID/fromString value))