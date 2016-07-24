(ns wallaby.core
  "Functions for working with the Walmart Open API"
  (:require [clojure.data.json :as json]
            [clojure.string :refer [join]]
            [org.httpkit.client :as http]
            [query-string.core :as query]))

(def ^:const endpoint "https://api.walmartlabs.com/v1")

(defn- handle-response
  "Returns the response body as a map"
  [{:keys [body]}]
  (json/read-str body))

(defn request
  "Performs a request against the Walmart Open API and returns a map"
  ([api params config]
  {:pre [(contains? params :apiKey)]}
   (-> (str endpoint "/" api "?" (query/create params))
       (http/get config handle-response)))
  ([api params] (request api params {})))

(defn product-lookup
  "Request from the Product Lookup API"
  ([params config] (request "items" params config))
  ([params] (request "items" params)))
