(ns migrations..20170226202605-surveys
  (:require 	[clojure.java.jdbc :as sql]
             [sogeti-server.db :as m]))

(defn up
  "Migrates the database up to version 20170226202605."
  []
  (sql/db-do-commands
   m/db
   ["CREATE TABLE SURVEYS (
  		ID BIGINT NOT NULL PRIMARY KEY, 
       TITLE VARCHAR(255),
      QUESTION TEXT)"])
  (println "migrations..20170226202605-surveys up..."))

(defn down
  "Migrates the database down from version 20170226202605."
  []
  (sql/db-do-commands
   m/db
   ["DROP TABLE SURVEYS"])
  (println "migrations..20170226202605-surveys down..."))
