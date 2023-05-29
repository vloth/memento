(ns main.pages.about
  (:require [helix.dom :as d]
            [main.lib.helix :refer [defnc]]))

(defnc about-page []
  (d/p "about!"))
