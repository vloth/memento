(ns main.lib.chakra
  (:require ["@chakra-ui/react" :as chakra-ui]))

(def chakra-provider chakra-ui/ChakraProvider)

(def flex chakra-ui/Flex)
(def stack chakra-ui/Stack)
(def text chakra-ui/Text)
(def icon chakra-ui/Icon)
(def icon-button chakra-ui/IconButton)
(def link chakra-ui/Link)
(def popover chakra-ui/Popover)
(def popover-trigger chakra-ui/PopoverTrigger)
(def popover-content chakra-ui/PopoverContent)
(def collapse chakra-ui/Collapse)
(def box chakra-ui/Box)

(def use-color-mode chakra-ui/useColorMode)
(def use-breakpoint-value chakra-ui/useBreakpointValue)

(defn use-disclojure []
  (js->clj (chakra-ui/useDisclosure) :keywordize-keys true))
