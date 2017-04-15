(ns migrations..20170227214406-groups
  (:require 	[clojure.java.jdbc :as sql]
             [sogeti-server.db :as m]))

(defn up
  "Migrates the database up to version 20170227214406."
  []
  (sql/db-do-commands
   m/db
   ["CREATE TABLE GROUPS (
  		ID BIGINT NOT NULL PRIMARY KEY, 
       TITLE VARCHAR(255),
      ADMIN BOOLEAN)"])
  (println "migrations..20170227214406-groups up..."))

(defn down
  "Migrates the database down from version 20170227214406."
  []
  (sql/db-do-commands
   m/db
   ["DROP TABLE GROUPS"])
  (println "migrations..20170227214406-groups down..."))
