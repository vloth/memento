(ns main.app
  (:require ["react-dom/client" :as rdom]
            [helix.core :refer [$ <>]]
            [helix.dom :as d]
            [main.lib :refer [defnc]]))

(defnc component-one [{:keys [show?]}]
  (<> (if show? "yes" "no")))

(defnc app []
  (d/div (d/h1 "memento") 
         ($ component-one {:show? true})))

(defonce root
  (rdom/createRoot (js/document.getElementById "app")))

(defn render []
  (.render root ($ app)))

(defn ^:export init []
  (render))
