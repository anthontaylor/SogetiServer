(ns sogeti-server.db
  (:refer-clojure :exclude [update format])
  (:require [clojure.java.jdbc :as j]
            [clojure.string :refer [split-lines]]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]
            [environ.core :refer [env]]
            [clj-time.core :as t]))

(def db {:classname "com.mysql.jdbc.Driver"
         :subprotocol "mysql"
         :subname (env :db-subname)
         :user "root"
         :password (env :root-db)})

(defn get-user-query  
  [user_id] 
  (-> (select :*)
      (from :USER)
      (where [:= :ID user_id])
      sql/format))

(defn now
  []
  (.getMillis (t/now)))

(defn add-time-details
  [maps]
  (let [last-modified-date (now)
        created-date (now)
        time-entries {:last_modified_date last-modified-date :created_date created-date}]
          (merge maps time-entries)))

(defn get-user
  [user_id]
  (->> 
   user_id
   get-user-query
   (j/query db)
   first))

(defn insert-user
  [user]
  (->> user
       add-time-details
       (j/insert! db :USER))
  (get-user (:id user)))

(defn truncate
  []
  (j/with-db-transaction [conn db] 
    (j/execute! conn ["TRUNCATE TABLE USER"])))
