#wallaby

[![Clojars Project](https://img.shields.io/clojars/v/wallaby.svg)](https://clojars.org/wallaby)

A Clojure library designed to simplify working with the Walmart Open API

## Installation

Add the following dependency to your `project.clj` file:

```
[wallaby "0.1.0"]
```

## Usage

```clojure
(ns my-walmart.app
  (:require [wallaby.core :refer :all]))

;; Product Lookup Operation
(def result (product-lookup {:apiKey "my-api-key"
                             :upc    "035000521019"}))

;; results are futures containing response as a map
(def realized @result);
```

## Functions

The main function is `request`. All functions are a specialization of this function.

### `request`

```clojure
(defn request
  ([api params config] ,,,)
  ([api params] (request api params {})))

```

* `api` is a string representing the Walmart API of interest - i.e "items"
* `params` is a map that will be converted straight to query params
* `config` is an optional map that gets fed right to [http-kit](http://www.http-kit.org/client.html)

There is currently one specialized function `product-lookup` which fills in the "items" api in the `request` function

```clojure
(defn product-lookup
  ([params config] (request "items" params config))
  ([params] (requeset "items" params)))
```

`request` has a single pre-condition defined to ensure the presence of the `:apiKey` key on the `params` map.

## License

Copyright © 2016 Brian Scaturro

Distributed under the Eclipse Public License, the same as Clojure
