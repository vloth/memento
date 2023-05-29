(ns lib.test
  (:require ["@testing-library/react" :as tlr]
            [helix.core :refer [$]]
            [main.layout.base :as layout.base]))

; rendering
(defn testing-container []
  (as-> (js/document.createElement "div") div
    (js/document.body.appendChild div)))

(def cleanup tlr/cleanup)

(defn render [children]
  (tlr/render ($ layout.base/providers children)
              #js {:container (testing-container)}))

; dom
(defn get-by-testid [^js container test-id]
  (.getByTestId container test-id))

; interaction
(defn click [^js element]
  (.click tlr/fireEvent element))

(defn type-in [^js element value]
  (.focus tlr/fireEvent element)
  (.change tlr/fireEvent element #js {:target #js {:value value}}))

(defn submit [^js element]
  (.submit tlr/fireEvent element))

; url
(defn get-current-url []
  (str (.. js/window -location -pathname)
       (.. js/window -location -search)))

(defn set-current-url! [href]
  (.replaceState js/history #js {} "" href))
