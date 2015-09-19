(defproject clj-sensu "0.1.0-SNAPSHOT"
  :description "FIXME: wrapper for sensu API"
  :url "http://github.com/julienba/clj-sensu"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [cheshire "5.3.1"]
                 [clj-http "2.0.0" :exclusions [org.clojure/clojure]]])
