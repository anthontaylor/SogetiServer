(ns sogeti-server.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.http-response :refer [ok not-found created bad-request]]
            [handler.user :as user]
            [handler.events :as events]
            [cheshire.core :as json]))

(def app
  (api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:info {:title "Sogeti API"
                   :description "Sogeti APIs"}
            :tags [{:name "api", :description "API"}]}}}

   (context "/api" []
     :tags ["api"]
     user/endpoints
     events/endpoints)))

