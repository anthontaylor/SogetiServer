(ns sogeti-server.schema
	(:require [schema.core :as s]))

(s/defschema CreateUser
  {:first_name s/Str
   :last_name s/Str
   :email s/Str
   :username s/Str
   :phone_number s/Str
   :id Long})

(s/defschema ReturnUser
  (merge CreateUser
    {:created_date Long
     :last_modified_date Long}))
