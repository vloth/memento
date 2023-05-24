(ns main.app-test
  (:require ["@testing-library/react" :as tlr]
            [clojure.test :refer [deftest is use-fixtures]]
            [helix.core :refer [$]]
            [main.layout.base :as layout.base]
            [main.layout.navbar :as layout.navbar]))

(defn testing-container []
  (as-> (js/document.createElement "div") div
    (js/document.body.appendChild div)))

(defn setup-root [f]
  (f)
  (tlr/cleanup))

(use-fixtures :each setup-root)

(defn render [children]
  (tlr/render ($ layout.base/providers children)
              #js {:container (testing-container)}))

(def not-nil? (comp not nil?))

(defn get-by-testid [^js container test-id]
  (.getByTestId container test-id))

(deftest has-home-menu
  (-> (render ($ layout.navbar/navbar))
      (get-by-testid  "menu-home")
      ((fn [link] (is (not-nil? link))))))
