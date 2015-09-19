(ns clj-sensu.core
  (:require
   [clj-sensu.rest :as rest :refer [GET POST DELETE url-with-path]]))

;; =============================================================================
;; Sensu API

(defn- api-call
  [{:keys [base-url auth]} method url & {:keys [params body]}]
  (case method
    :GET    (GET (url-with-path base-url url) auth params)
    :POST   (POST (url-with-path base-url url) auth body)
    :DELETE (DELETE (url-with-path base-url url) auth)))

;; =============================================================================
;; Info

(defn info
  [cfg]
  (api-call cfg :GET "/info"))

;; =============================================================================
;; Clients

(defn clients
  [cfg]
  (api-call cfg :GET "/clients"))

(defn clients-by-name
  [cfg name]
  (api-call cfg :GET (str "/clients/" name)))

(defn clients-history
  [cfg name]
  (api-call cfg :GET (str "/clients/" name "/history")))

(defn create-client
  [cfg client]
  (api-call cfg :POST "/clients" :body client))

(defn delete-client
  [cfg name]
  (api-call cfg :DELETE (str "/clients/" name)))

;; =============================================================================
;; Results

(defn results
  [cfg]
  (api-call cfg :GET "/results"))

(defn results-by-client
  [cfg client]
  (api-call cfg :GET (str "/results" client)))

(defn results-by-client-check
  [cfg client check]
  (api-call cfg :GET (str "/results" client "/" check)))

(defn results
  [cfg]
  (api-call cfg :GET "/results"))

;; =============================================================================
;; Events

(defn events
  [cfg & {:keys [limit offset] :as params}]
  (api-call cfg :GET "/events" :params params))

(defn events-by-name
  [cfg name & {:keys [limit offset] :as params}]
  (api-call cfg :GET (str "/events/" name) :params params))

;; =============================================================================
;; Stashes

(defn stashes
  [cfg & {:keys [limit offset] :as params}]
  (api-call cfg :GET "/stashes" :params params))

(defn stashes-by-path
  [cfg path & {:keys [limit offset] :as params}]
  (api-call cfg :GET (str "/stashes" path) :params params))

(defn create-stash
  ([cfg body]
   (api-call cfg :POST "/stashes" :body body))
  ([cfg path message expire]
   (create-stash cfg {:path path
                      :content {:message message}
                      :expire expire}))
  ([cfg path message]
   (create-stash cfg {:path path
                      :content {:message message}})))

(defn delete-stash
  [cfg path]
  (api-call cfg :DELETE (str "/stashes/" path)))

;; =============================================================================
;; Aggregates

(defn aggregates
  [cfg & {:keys [limit offset] :as params}]
  (api-call cfg :GET "/aggregates" :params params))

(defn aggregates
  [cfg & {:keys [limit offset] :as params}]
  (api-call cfg :GET "/aggregates" :params params))

(defn aggregates-by-check
  [cfg check & {:keys [limit offset] :as params}]
  (api-call cfg :GET (str "/aggregates/" check) :params params))

(defn aggregates-by-check-issued
  [cfg check issued & {:keys [limit offset] :as params}]
  (api-call cfg :GET (str "/aggregates/" check "/" issued) :params params))

;; =============================================================================
;; Checks

(defn checks
  [cfg]
  (api-call cfg :GET "/checks"))

(defn checks-by-check
  [cfg check]
  (api-call cfg :GET (str "/checks/" check)))

;; =============================================================================
;; Health

(defn health
  [cfg consummers messages]
  (api-call cfg :GET "/health" :params {:consummers consummers :messages messages}))

