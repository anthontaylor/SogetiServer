(ns sogeti-server.db
  (:refer-clojure :exclude [update format])
  (:require [clojure.java.jdbc :as j]
            [clojure.string :refer [split-lines]]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]
            [environ.core :refer [env]]
            [clojure.tools.trace :refer [trace]]            
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

(defn get-event-query  
  [event_id] 
  (-> (select :*)
      (from :EVENTS)
      (where [:= :ID event_id])
      sql/format))

(defn get-event
  [event_id]
  (->>
   event_id
   get-event-query
   (j/query db)
   first))

(defn insert-event
  [event]
  (->> event
       (j/insert! db :EVENTS))
  (get-event (:id event)))

(defn find-event
  [user_id]
  (->>
   (get-event-query user_id)
   (j/query db)
   first))

(defn update-event-query
  [{id :id :as event}]
  (-> (update :EVENTS)
      (sset (dissoc event :id))
      (where [:= :ID id])
      sql/format))


(defn update-event
  ([event]
  (update-event db event))
  ([conn {uid :id :as event}]
    (->>  event
          update-event-query
          (j/execute! conn))
    (get-event (:id event))))

(defn truncate
  []
  (j/with-db-transaction [conn db] 
    (j/execute! conn ["TRUNCATE TABLE USER"])
    (j/execute! conn ["TRUNCATE TABLE EVENTS"])))
