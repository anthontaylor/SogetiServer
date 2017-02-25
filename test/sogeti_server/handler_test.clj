(ns sogeti-server.handler-test
  (:require [clojure.test :refer :all]
            [ring.util.http-predicates :refer [ok? not-found? bad-request? created?]]
            [ring.mock.request :as mock]
            [schema-generators.generators :as g]
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
