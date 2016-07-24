(ns wallaby.core
  "Functions for working with the Walmart Open API"
  (:require [clojure.data.json :as json]
            [clojure.string :refer [join]]
            [org.httpkit.client :as http])
  (:import  [java.net URLEncoder]))

(def ^:const endpoint "https://api.walmartlabs.com/v1")

(defn- join-pair
  "Join a key/value pair for use in a query string"
  [[k,v]]
  (str (name k) "=" (URLEncoder/encode v)))

(defn- query
  "Convert the parameter map into a query string"
  [params]
  (->> (map join-pair params)
       (join "&")))

(defn- handle-response
  "Returns the response body as a map"
  [{:keys [body]}]
  (json/read-str body))

(defn request
  "Performs a request against the Walmart Open API and returns a map"
  ([api params config]
  {:pre [(contains? params :apiKey)]}
   (-> (str endpoint "/" api "?" (query params))
       (http/get config handle-response)))
  ([api params] (request api params {})))

(defn product-lookup
  "Request from the Product Lookup API"
  ([params config] (request "items" params config))
  ([params] (request "items" params)))
