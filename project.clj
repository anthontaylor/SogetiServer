(defproject sogeti-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [metosin/compojure-api "1.2.0-alpha1"]
                 [ring/ring-defaults "0.2.1"]
                 [org.clojure/java.jdbc "0.7.0-alpha1"]
                 [honeysql "0.8.1"]
                 [cheshire "5.4.0"]
                 [environ "1.1.0"]                 
                 [drift "1.5.3"]
                 [mysql/mysql-connector-java "5.1.18"]
                 [metosin/schema-tools "0.9.0"]
                 [org.clojure/tools.trace "0.7.9"]
                 [prismatic/schema-generators "0.1.0"]
                 [clj-time "0.13.0"]
                 [spootnik/unilog "0.7.17"]]
  :jvm-opts ["-Djdbc.drivers=com.mysql.jdbc.Driver"]
  :plugins [[lein-ring "0.9.7"]
            [drift "1.5.1"]
            [lein-environ "1.1.0"]]
  :ring {:handler sogeti-server.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [drift "1.5.3"]
                        [ring/ring-mock "0.3.0"]]
         :env {
               :log-env "test"
               :root-db "sogeti"
               :db "sogeti"
               :database "sogeti_d"
               :user "sogeti_u"
               :db-subname "//localhost:3306/sogeti_d"}}
   :docker {:dependencies [[javax.servlet/servlet-api "2.5"]
                           [ring/ring-mock "0.3.0"]
                           [drift "1.5.3"]]
          :env {
                 :log-env "docker"
                 :root-db "sogeti"
                 :db "sogeti"
                 :database "sogeti_d"
                 :user "sogeti_u"
                 :db-subname "//database:3306/sogeti_d"}}})
