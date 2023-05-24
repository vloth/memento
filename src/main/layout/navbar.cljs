(ns main.layout.navbar
  (:require ["@chakra-ui/icons" :as icons]
            ["@chakra-ui/react" :as chakra]
            ["react-router-dom" :as rrd]
            [clojure.string :as s]
            [helix.core :refer [$]]
            [main.lib :refer [defnc]]))

(def nav-items
  [{:label "Exercises"
    :children [{:label "Two pointer technique"
                :sub-label ""
                :href "/about"}
               {:label "Fast and Slow pointers pattern"
                :sub-label "Up-and-coming Designers"
                :href "/about"}]}

   {:label "Windows & Intervals"
    :children [{:label "Sliding windows"
                :sub-label "Up-and-coming Designers"
                :href "#"}
               {:label "Merge intervals"
                :sub-label "Up-and-coming Designers"
                :href "#"}]}

   {:label "Others"
    :children [{:label "0/1 Knapsack Pattern"
                :sub-label "Up-and-coming Designers"
                :href "#"}]}

   {:label "Home"
    :href "/"}

   {:label "About"
    :href "/about"}])

(defnc desktop-sub-nav [{:keys [label href sub-label]}]
  ($ chakra/Link
     {:as rrd/Link
      :to href
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
                      :data-testid (str "menu-" (s/lower-case (:label nav)))
                      :as rrd/Link
                      :role "link"
                      :to (or (:href nav) "#")
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

(defnc mobile-nav-item [{:keys [label children href]}]
  (let [{:keys [isOpen onToggle]}
        (js->clj (chakra/useDisclosure) :keywordize-keys true)]
    ($ chakra/Stack
       {:spacing 4
        :onClick (and (not-empty children) onToggle)}
       ($ chakra/Flex
          {:py 2
           :as chakra/Link
           :href (and href "#")
           :justify "space-between"
           :align "center"
           :_hover #js {:textDecoration "none"}}
          ($ chakra/Text
             {:fontWeight 600
              :color (chakra/useColorModeValue "gray.600" "gray.200")}
             label)
          (when (not-empty children)
            ($ chakra/Icon
               {:as icons/ChevronDownIcon
                :transition "all .25s ease-in-out"
                :transform (if isOpen "rotate(180deg)" "")
                :w 6
                :h 6})))
       ($ chakra/Collapse
          {:in isOpen
           :animateOpacity true
           :style #js {:marginTop "0"}}
          ($ chakra/Stack
             {:mt 2
              :pl 4
              :borderLeft 1
              :borderStyle "solid"
              :color (chakra/useColorModeValue "gray.600" "gray.200")
              :align "start"}
             (for [child children]
               ($ chakra/Link
                  {:key (:label child)
                   :py 2
                   :href (:href child)}
                  (:label child))))))))

(defnc mobile-nav []
  ($ chakra/Stack
     {:bg (chakra/useColorModeValue "white" "gray.800")
      :p 4
      :display #js {:md "none"}}
     (for [nav nav-items]
       ($ mobile-nav-item {:key (:label nav) & nav}))))

(defnc navbar [{:keys []}]
  (let [{:keys [isOpen onToggle]}
        (js->clj (chakra/useDisclosure) :keywordize-keys true)]
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
                          :justify #js {:base "end" :md "start"}}
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
                ($ desktop-nav))))
       ($ chakra/Collapse {:in isOpen :animateOpacity true} ($ mobile-nav)))))
