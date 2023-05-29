(ns node
  (:require [cljs.test :as test]))

(defn mock-match-media! []
  (let [noop (clj->js (fn []))]
    (set! (.-matchMedia js/window)
          (clj->js (fn [] #js {:matches false
                               :addListener noop
                               :addEventListener noop
                               :removeListener noop})))))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn main []
  (mock-match-media!)
  (test/run-all-tests #".*-test$"))
