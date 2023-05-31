(ns main.pages.not-found
  (:require [helix.core :refer [$]]
            [main.lib.chakra :as c]
            [main.lib.helix :refer [defnc]]))

(defnc not-found-page []
  ($ c/container
     {:maxW "3xl"}
     ($ c/text "NOT FOUND")))
