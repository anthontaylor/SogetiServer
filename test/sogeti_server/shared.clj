(ns sogeti-server.shared
  (:require [clojure.test :refer :all]
            [ring.util.http-predicates :refer [ok? not-found? bad-request? created?]]
            [ring.mock.request :as mock]
            [schema-generators.generators :as g]
            [sogeti-server.schema :as s]
            [sogeti-server.db :as db]
            [cheshire.core :refer [parse-string]]
            [sogeti-server.handler :refer :all]))

(defn data-base-prep [f]
  (db/truncate)
  (f))

(defn coerce-body [body] (-> body slurp (parse-string true)))
(defn rm-date [m] (dissoc m :created_date :last_modified_date))
