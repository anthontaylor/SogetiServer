(ns config.migrate-config
  (:require [drift.builder :only [incremental-migration-number-generator]]
            [clojure.string :refer [split-lines split]]
            [clojure.java.jdbc :as sql]
            [environ.core :refer [env]]
            [sogeti-server.db :refer [db]]))

(defn- create-schema-table
  "Creates the schema table if it doesn't already exist."
  [args]
  (sql/db-do-commands 
   db
   ["CREATE TABLE IF NOT EXISTS schema_version (version BIGINT NOT NULL, created_at TIMESTAMP NOT NULL DEFAULT NOW());"]))

(defn- current-db-version
  []
  (:version
   (or
    (last (sql/query db "SELECT version FROM schema_version"))
    {:version 0})))

(defn- update-db-version
  [version]
  (sql/insert! db :schema_version [:version] [version]))

(defn migrate-config []
  {:directory "src/migrations/"
   :init create-schema-table
   :current-version current-db-version
   :update-version update-db-version })
