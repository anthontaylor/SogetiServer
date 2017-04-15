(ns migrations..20170126170853-events
  (:require 	[clojure.java.jdbc :as sql]
             [sogeti-server.db :as m]))

(defn up
  "Migrates the database up to version 20170126170853."
  []
  (sql/db-do-commands
   m/db
   ["CREATE TABLE EVENTS (
  		ID BIGINT NOT NULL PRIMARY KEY, 
       TITLE VARCHAR(255),
      DESCRIPTION VARCHAR(255),
      START_TIME VARCHAR(255),
      END_TIME VARCHAR(255),
      RESERVED BOOLEAN)"])
  (println "migrations..20170126170853-events up..."))

(defn down
  "Migrates the database down from version 20170126170853."
  []
  (sql/db-do-commands
   m/db
   ["DROP TABLE EVENTS"])
  (println "migrations..20170126170853-events down..."))
