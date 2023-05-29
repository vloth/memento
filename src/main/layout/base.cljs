(ns main.layout.base
  (:require ["react-router-dom" :as rrd]
            [helix.core :refer [$ <>]]
            [main.layout.navbar :as layout.navbar]
            [main.lib.chakra :as c]
            [main.lib.helix :refer [defnc]]))

(defnc providers [{:keys [children]}]
  ($ c/chakra-provider
     ($ rrd/BrowserRouter children)))

(defnc layout []
  (<> ($ layout.navbar/navbar)
      ($ rrd/Outlet)))
