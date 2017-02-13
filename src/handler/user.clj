(ns handler.user
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.http-response :refer [ok not-found created bad-request]]
            [sogeti-server.schema :as s]
            [sogeti-server.db :as db]
            [clojure.tools.trace :refer [trace]]            
            [compojure.api.sweet :refer :all]))

(defn get-user
	[user]
	(db/get-user user))

(defn create-user
  [user]
  (db/insert-user user))

(def endpoints
	(context "/user" []
		:tags ["user"]
                
(POST "/" request
    :body [user s/CreateUser]
    :responses {201 {:schema s/ReturnUser
                     :description "Resource Created"}
                400 {:description "Bad Request"}}
    :summary "Create's a user resource"
    (if-let [{id :id :as returned-user} (create-user user)]
      (created (str "/api/user/" id) returned-user)
      (bad-request "Error")))

(GET "/:id" []
:responses {200 {:schema s/ReturnUser
                 :description "Request a resources"}
           404  {:description "Not found"}}
:path-params [id :- Long]
:summary "get's a user id"
(if-let [user (get-user id)]
  (ok user)
  (not-found)))))
