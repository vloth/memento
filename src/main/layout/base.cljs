(ns main.layout.base
  (:require
   ["react-router-dom" :as rrd]
   ["@chakra-ui/react" :as chakra-ui]
   [helix.core :refer [$ <>]]
   [main.layout.navbar :as layout.navbar]
   [main.lib :refer [defnc]]))

(defnc providers [{:keys [children]}]
  ($ chakra-ui/ChakraProvider
     ($ rrd/BrowserRouter children)))

(defnc layout []
  (<> ($ layout.navbar/navbar)
      ($ rrd/Outlet)))
