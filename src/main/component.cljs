(ns main.component
  (:require ["@chakra-ui/react"  :as chakra]
            [helix.core :refer [$]]
            [main.lib :refer [defnc]]))

(defnc app []
  ($ chakra/Button "OK"))
