# clj-sensu

Wrapper for sensu API

## Usage

```clojure
(require '[clj-sensu.core :as sensu])

(def config
  {:base-url "http://you.sensu.url:4567"
   :auth ["YourLogin" "YourPassword"]})

(sensu/info config)

(sensu/clients config)
```

## License

Distributed under the Eclipse Public License version 1.0.
