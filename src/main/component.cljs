(ns main.component
  (:require ["@chakra-ui/react"  :as chakra]
            [helix.core :refer [$ <>]]
            [helix.dom :as d]
            [main.lib :refer [defnc]]))

(defnc component-boolean [{:keys [value]}]
  (<> (if value "yes" "no")))

(defnc app []
  ($ chakra/ChakraProvider
     ($ chakra/Button "OK")
     (d/div (d/h1 "mementosss")
            ($ component-boolean {:value true}))))
