(ns main.components.layout
  (:require ["@chakra-ui/react"  :as chakra]
            ["@chakra-ui/icons"  :as icons]
            [helix.core :refer [$]]
            [main.lib :refer [defnc]]))

(defnc providers [{:keys [children]}]
  ($ chakra/ChakraProvider children))

(def nav-items
  [{:label "Inspiration"
    :children [{:label "Explore Design Work"
                :sub-label "Trending Design to inspire you"
                :href "#"}
               {:label "New & Noteworthy"
                :sub-label "Up-and-coming Designers"
                :href "#"}]}

   {:label "Find Work"
    :children [{:label "Job Board"
                :sub-label "Find your dream design job"
                :href "#"}
               {:label "Freelance Projects"
                :sub-label "An exclusive list for contract work"
                :href "#"}]}

   {:label "Learn Design"
    :href "#"}

   {:label "Hire Designers"
    :href "#"}])

(defnc desktop-sub-nav [{:keys [label href sub-label]}]
  ($ chakra/Link
     {:href href
      :role "group"
      :display "block"
      :p 2
      :rounded "md"
      :_hover #js {:bg (chakra/useColorModeValue "pink.50" "gray.900")}}
     ($ chakra/Stack {:direction "row" :align "center"}
        ($ chakra/Box
           ($ chakra/Text
              {:transition "all .3s ease"
               :_groupHover #js {:color "pink.400"}
               :fontWeight 500} label)
           ($ chakra/Text
              {:fontSize "sm"} sub-label))
        ($ chakra/Flex
           {:transition "all .3s ease"
            :transform "translateX(-10px)"
            :opacity 0
            :_groupHover #js {:opacity "100%" :transform "translateX(0)"}
            :justify "flex-end"
            :align "center"
            :flex 1}
           ($ chakra/Icon {:color "pink.400"
                           :w 5
                           :h 5
                           :as icons/ChevronRightIcon})))))


(defnc desktop-nav []
  (let [link-color (chakra/useColorModeValue "gray.600" "gray.200")
        link-hover-color (chakra/useColorModeValue "gray.800" "white")
        popover-content-bg-color (chakra/useColorModeValue "white" "gray.800")]
    ($ chakra/Stack {:direction "row"
                     :spacing 4}
       (for [nav nav-items]
         ($ chakra/Box {:key (:label nav)}
            ($ chakra/Popover {:trigger "hover" :placement "bottom-start"}
               ($ chakra/PopoverTrigger
                  ($ chakra/Link
                     {:p 2
                      :href (or (:href nav) "#")
                      :fontSize "sm"
                      :fontWeight 500
                      :color link-color
                      :_hover
                      #js {:textDecoration "none"
                           :color link-hover-color}}
                     (:label nav)))
               (when (not-empty (:children nav))
                 ($ chakra/PopoverContent
                    {:bg popover-content-bg-color
                     :border 0
                     :boxShadow "xl"
                     :p 4
                     :rounded "xl"
                     :minW "sm"}
                    ($ chakra/Stack
                       (for [child (:children nav)]
                         ($ desktop-sub-nav
                            {:key (:label child) & child})))))))))))

(defnc layout [{:keys []}]
  (let [{:keys [isOpen onToggle]} (js->clj
                                   (chakra/useDisclosure)
                                   :keywordize-keys true)]
    ($ chakra/Box
       ($ chakra/Flex
          {:minH "60px"
           :bg (chakra/useColorModeValue "white" "gray.800")
           :color (chakra/useColorModeValue "gray.600" "white")
           :borderColor (chakra/useColorModeValue "gray.200" "gray.900")
           :py #js {:base 2}
           :px #js {:base 4}
           :borderBottom 1
           :borderStyle "solid"
           :align "center"}
          ($ chakra/Flex
             {:flex #js {:base 1 :md "auto"}
              :ml #js {:base -2}
              :display #js {:base "flex" :md "none"}}
             ($ chakra/IconButton
                {:onClick onToggle
                 :icon (if isOpen
                         ($ icons/CloseIcon {:w 3 :h 3})
                         ($ icons/HamburgerIcon {:w 3 :h 3}))
                 :variant "ghost"
                 :aria-label "Toggle Navigation"}))
          ($ chakra/Flex {:flex #js {:base 1}
                          :justify #js {:base "center" :md "start"}}
             ($ chakra/Text
                {:textAlign
                 (chakra/useBreakpointValue
                  #js {:base "center" :md "left"})
                 :fontFamily "heading"
                 :color
                 (chakra/useColorModeValue "gray.800" "white")}
                "Memento")
             ($ chakra/Flex
                {:display #js {:base "none" :md "flex"}
                 :ml 10}
                ($ desktop-nav)))))))
