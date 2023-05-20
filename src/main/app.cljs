(ns main.app
  (:require ["react-dom/client" :as rdom]
            [main.components.layout :as components.layout]
            [helix.core :refer [$]]))

(defonce root
  (rdom/createRoot
   (js/document.getElementById "app")))

(defn render []
  (.render root
           ($ components.layout/providers
              ($ components.layout/layout))))

(defn ^:export init []
  (render))
