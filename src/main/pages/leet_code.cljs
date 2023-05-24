(ns main.pages.leet-code
  (:require [helix.core :refer [$]]
            [helix.dom :as d]
            [main.lib.helix :refer [defnc]]
            [main.lib.chakra :as chakra]))

(defnc leet-code []
  ($ chakra/flex {:p 2 :gap 6}
     ($ chakra/box {:maxWidth "25%" :bg "green"}
        (d/p "There are many benefits to a joint design and development system. Not only
  does it bring benefits to the design team, but it also brings benefits to
  engineering teams. It makes sure that our experiences have a consistent look
  and feel, not just in our design specs, but in production"))
     ($ chakra/box
        (d/p "There are many benefits to a joint design and development system. Not only
  does it bring benefits to the design team, but it also brings benefits to
  engineering teams. It makes sure that our experiences have a consistent look
  and feel, not just in our design specs, but in production"))))
