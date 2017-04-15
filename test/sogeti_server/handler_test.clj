(ns sogeti-server.handler-test
  (:require [clojure.test :refer :all]
            [ring.util.http-predicates :refer [ok? not-found? bad-request? created?]]
            [ring.mock.request :as mock]
            [schema-generators.generators :as g]
            [cheshire.core :refer [generate-string parse-string]]
            [sogeti-server.schema :as s]
            [sogeti-server.db :as db]
            [sogeti-server.handler :refer :all]
            [clojure.tools.trace :refer [trace]]
            [sogeti-server.shared :refer :all]))

(use-fixtures :each data-base-prep)

(deftest get-user-test
  (testing "User application"
    (let [user (g/generate s/CreateUser)
          {id :id} (db/insert-user user)]
      (->
       (mock/request :get (str "/api/user/" id))
       app
       (as-> {:keys [body] :as response}
             (and
              (is (ok? response))))))))

(deftest invalid-user-test
  (testing "not-found route"
    (let [response (app (mock/request :get "/api/user/invalid"))]
      (is (bad-request? response)))))

(deftest get-event-test
  (testing "Event application"
    (let [event (g/generate s/Event)
          {id :id} (db/insert-event event)]
      (->
       (mock/request :get (str "/api/events/" id))
       app
       (as-> {:keys [body] :as response}
             (and
              (is (ok? response))))))))

(deftest user-create-test
  (testing "The creation of a user application"
    (let [{id :id :as user} (g/generate s/CreateUser)]
      (-> (mock/request :post "/api/user")
          (mock/body (generate-string user))
          (mock/content-type "application/json")
          app
          (as-> {:keys [body] :as response}
                (and
                 (is (created? response))
                 (is (= user (rm-date (coerce-body body))))
                 (is (= user (rm-date (db/get-user id))))))))))

(deftest event-create-test
  (testing "The creation of an event application"
    (let [{id :id :as event} (g/generate s/Event)]
      (-> (mock/request :post "/api/events")
          (mock/body (generate-string event))
          (mock/content-type "application/json")
          app
          (as-> {:keys [body] :as response}
                (and
                 (is (created? response))
                 (is (= event (rm-date (coerce-body body))))
                 (is (= event (rm-date (db/get-event id))))))))))
