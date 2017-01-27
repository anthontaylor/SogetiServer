(ns sogeti-server.db
  (:refer-clojure :exclude [update format])
  (:require [clojure.java.jdbc :as j]
            [clojure.string :refer [split-lines]]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]
            [environ.core :refer [env]]
            [clojure.tools.trace :as t]))

(def db {:classname "com.mysql.jdbc.Driver"
         :subprotocol "mysql"
         :subname (env :db-subname)
         :user "root"
         :password (env :root-db)})
