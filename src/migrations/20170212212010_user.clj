(ns migrations..20170212212010-user
    (:require 	[clojure.java.jdbc :as sql]
                [sogeti-server.db :as m]))

(defn up
  "Migrates the database up to version 20170212212010."
  []
    (sql/db-do-commands 
  	m/db
  	["CREATE TABLE USER (
  		ID BIGINT NOT NULL PRIMARY KEY, 
  		FIRST_NAME VARCHAR(255) NOT NULL,
  		LAST_NAME VARCHAR(255),
      EMAIL VARCHAR(320) NOT NULL,
      USERNAME VARCHAR(30) NOT NULL,
      CREATED_DATE BIGINT NOT NULL,
      LAST_MODIFIED_DATE BIGINT NOT NULL, 
      PHONE_NUMBER VARCHAR(20) NOT NULL)"])
  (println "migrations..20170212212010-user up..."))

(defn down
  "Migrates the database down from version 20170212212010."
  []
    (sql/db-do-commands 
  	m/db
  	["DROP TABLE USER"])
  (println "migrations..20170212212010-user down..."))
