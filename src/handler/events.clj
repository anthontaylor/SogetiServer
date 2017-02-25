(ns handler.events
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.http-response :refer [ok not-found created bad-request]]
            [sogeti-server.schema :as s]
            [sogeti-server.db :as db]
            [clojure.tools.trace :refer [trace]]            
            [compojure.api.sweet :refer :all]))

(defn get-event
	[event]
	(db/get-event event))

(defn create-event
  [event]
  (db/insert-event event))

(defn update-event
  [event]
  (db/update-event event))

(def endpoints
	(context "/events" []
		:tags ["events"]
                
(POST "/" request
    :body [event s/Event]
    :responses {201 {:schema s/Event
                     :description "Resource Created"}
                400 {:description "Bad Request"}}
    :summary "Create's an event resource"
    (if-let [{id :id :as returned-event} (create-event event)]
      (created (str "/api/event/" id) returned-event)
      (bad-request "Error")))

(GET "/:id" []
     :responses {200 {:schema s/Event
                      :description "Request a resources"}
                 404  {:description "Not found"}}
     :path-params [id :- Long]
     :summary "get's an event id"
     (if-let [event (get-event id)]
       (ok event)
       (not-found)))

(PUT "/" request
      :body [event s/ReturnEvent]
      :responses { 201 {:schema s/ReturnEvent
                        :description "Resource Updated"}
                   400 {:description "Bad Request"}}
      :summary "Updates an Event"
      (if-let [{id :id :as returned-event} (update-event event)]
        (created (str "api/event/" id) returned-event)
        (bad-request "Error")))))
