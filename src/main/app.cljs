(ns main.app
  (:require ["react" :as r]
            ["react-dom/client" :as rdom]
            ["react-router-dom" :as rrd]
            [helix.core :refer [$ defnc]]
            [main.layout.base :as layout.base]
            [main.pages.home :as pages.home]
            [main.pages.about :as pages.about]))

(defn routes []
  ($ rrd/Routes
     ($ rrd/Route {:path "/" :element ($ layout.base/layout)}
        ($ rrd/Route {:index true :element ($ pages.home/home-page)})
        ($ rrd/Route {:path "about" :element ($ pages.about/about-page)}))))

(defnc app []
  ($ layout.base/providers ($ routes)))

(defonce root
  (rdom/createRoot (js/document.getElementById "app")))

(defn render []
  (.render root ($ r/StrictMode ($ app))))

(defn ^:export init [] (render))
