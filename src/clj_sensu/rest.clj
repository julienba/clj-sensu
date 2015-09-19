(ns clj-sensu.rest
  (:require [clj-http.client :as http]
            [cheshire.core :as json]))

(defn header-json
  "build ajson header with eventual authentification"
  [auth]
  (let [base-req {:accept :json :content-type :json}]
    (if auth
      (assoc base-req :basic-auth auth)
      base-req)))

(defn url-with-path [url & segments]
  (str url (clojure.string/join "/" segments)))

(defn GET
  ([uri] (GET uri nil))
  ([uri auth] (GET uri auth nil))
  ([uri auth params]
   (json/decode (:body (http/get uri (assoc (header-json auth) :query-params params)) true))))

(defn POST
  ([uri body] (POST uri nil body))
  ([uri auth body]
   (http/post uri (assoc (header-json auth) :body (json/encode body)))))

(defn DELETE
  ([uri] (DELETE uri nil))
  ([uri auth]
   (http/delete uri (header-json auth))))
