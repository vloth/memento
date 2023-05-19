(ns main.app
  (:require ["react-dom/client" :as rdom]
            [helix.core :refer [$]]
            [main.component :as c]))

(defonce root
  (rdom/createRoot 
    (js/document.getElementById "app")))

(defn render []
  (.render root ($ c/app)))

(defn ^:export init []
  (render))
