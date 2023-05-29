(ns main.lib.chakra
  (:require ["@chakra-ui/icons" :as icons]
            ["@chakra-ui/react" :as chakra-ui]))

; providers
(def chakra-provider chakra-ui/ChakraProvider)

; components
(def flex chakra-ui/Flex)
(def container chakra-ui/Container)
(def heading chakra-ui/Heading)
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
(def input chakra-ui/Input)

; hooks
(def use-color-mode-value chakra-ui/useColorModeValue) 
(def use-breakpoint-value chakra-ui/useBreakpointValue)
(defn use-disclojure []
  (js->clj (chakra-ui/useDisclosure) :keywordize-keys true))

; icons
(def icons 
  {:chevron-right icons/ChevronRightIcon
   :chevron-down icons/ChevronDownIcon
   :close icons/CloseIcon
   :hamburguer icons/HamburgerIcon})
