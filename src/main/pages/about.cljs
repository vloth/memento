(ns main.pages.about
  (:require [helix.core :refer [$]]
            [main.lib.chakra :as c]
            [main.lib.helix :refer [defnc]]))

(defnc about-page []
  ($ c/container
     {:maxW "3xl"
      :data-testid "description"}
     ($ c/stack
        {:as c/box
         :py #js {:base 20 :md 36}}
        ($ c/text
           {:fontWeight 600
            :color "gray.600"}
           "Memento is a database of common leetcode 
             exercises with solutions.")
        ($ c/text
           {:fontWeight 400
            :fontStyle "italic"
            :color "gray.500"}
           "(No code is guaranteed to be correct. Use at your own risk!)")
        ($ c/text
           {:fontWeight 400
            :fontStyle "italic"
            :color "gray.500"}
           "Source code can be found at "
           ($ c/link {:href "https://github.com/vloth/memento"
                      :color "blue.300"}
              "github.com/vloth/memento")
           "."))))
