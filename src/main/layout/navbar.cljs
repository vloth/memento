(ns main.layout.navbar
  (:require ["react-router-dom" :as rrd]
            [helix.core :refer [$]]
            [main.lib.chakra :as c]
            [main.lib.helix :refer [defnc]]))

(def nav-items
  {:home {:label "Home"
          :href "/"}

   :code {:label "Code"
          :href "#"
          :children
          {:array
           {:label "Array"
            :href "/code/array"
            :sub-label
            "Arrays are a simple data structure 
             for storing lots of similar items."}
           :string
           {:label "String"
            :href "/code/string"
            :sub-label
            "In computer programming, a string is traditionally 
             a sequence of characters, either as a literal 
             constant or as some kind of variable"}}}

   :about {:label "About"
           :href "/about"}})

(defnc desktop-sub-nav [{:keys [id label href sub-label]}]
  ($ c/link
     {:as rrd/Link
      :data-testid id
      :to href
      :role "group"
      :display "block"
      :p 2
      :rounded "md"
      :_hover #js {:bg (c/use-color-mode-value "pink.50" "gray.900")}}
     ($ c/stack {:direction "row" :align "center"}
        ($ c/box
           ($ c/text
              {:transition "all .3s ease"
               :_groupHover #js {:color "pink.400"}
               :fontWeight 500} label)
           ($ c/text
              {:fontSize "sm"} sub-label))
        ($ c/flex
           {:transition "all .3s ease"
            :transform "translateX(-10px)"
            :opacity 0
            :_groupHover #js {:opacity "100%" :transform "translateX(0)"}
            :justify "flex-end"
            :align "center"
            :flex 1}
           ($ c/icon {:color "pink.400"
                      :w 5
                      :h 5
                      :as (:chevron-right c/icons)})))))

(defnc desktop-nav []
  (let [link-color (c/use-color-mode-value "gray.600" "gray.200")
        link-hover-color (c/use-color-mode-value "gray.800" "white")
        popover-content-bg-color (c/use-color-mode-value "white" "gray.800")]
    ($ c/stack {:direction "row" :spacing 4}
       (for [[id nav] nav-items]
         ($ c/box {:key (:label nav)}
            ($ c/popover {:trigger "hover" :placement "bottom-start"}
               ($ c/popover-trigger
                  ($ c/link
                     {:p 2
                      :data-testid id
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
                 ($ c/popover-content
                    {:bg popover-content-bg-color
                     :border 0
                     :boxShadow "xl"
                     :p 4
                     :rounded "xl"
                     :minW "sm"}
                    ($ c/stack
                       (for [[id child] (:children nav)]
                         ($ desktop-sub-nav
                            {:key id & child})))))))))))

(defnc mobile-nav-item [{:keys [label children href]}]
  (let [{:keys [isOpen onToggle]} (c/use-disclojure)]
    ($ c/stack
       {:spacing 4
        :onClick (and (not-empty children) onToggle)}
       ($ c/flex
          {:py 2
           :as c/link
           :href (and href "#")
           :justify "space-between"
           :align "center"
           :_hover #js {:textDecoration "none"}}
          ($ c/text
             {:fontWeight 600
              :color (c/use-color-mode-value "gray.600" "gray.200")}
             label)
          (when (not-empty children)
            ($ c/icon
               {:as (:chevron-down c/icons)
                :transition "all .25s ease-in-out"
                :transform (if isOpen "rotate(180deg)" "")
                :w 6
                :h 6})))
       ($ c/collapse
          {:in isOpen
           :animateOpacity true
           :style #js {:marginTop "0"}}
          ($ c/stack
             {:mt 2
              :pl 4
              :borderLeft 1
              :borderStyle "solid"
              :color (c/use-color-mode-value "gray.600" "gray.200")
              :align "start"}
             (for [[id child] children]
               ($ c/link
                  {:key id
                   :py 2
                   :href (:href child)}
                  (:label child))))))))

(defnc mobile-nav []
  ($ c/stack
     {:bg (c/use-color-mode-value "white" "gray.800")
      :p 4
      :display #js {:md "none"}}
     (for [[id nav] nav-items]
       ($ mobile-nav-item {:key id & nav}))))

(defnc navbar [{:keys []}]
  (let [{:keys [isOpen onToggle]} (c/use-disclojure)]
    ($ c/box
       ($ c/flex
          {:minH "60px"
           :bg (c/use-color-mode-value "white" "gray.800")
           :color (c/use-color-mode-value "gray.600" "white")
           :borderColor (c/use-color-mode-value "gray.200" "gray.900")
           :py #js {:base 2}
           :px #js {:base 4}
           :borderBottom 1
           :borderStyle "solid"
           :align "center"}
          ($ c/flex
             {:flex #js {:base 1 :md "auto"}
              :ml #js {:base -2}
              :display #js {:base "flex" :md "none"}}
             ($ c/icon-button
                {:onClick onToggle
                 :icon (if isOpen
                         ($ (:close c/icons) {:w 3 :h 3})
                         ($ (:hamburguer c/icons) {:w 3 :h 3}))
                 :variant "ghost"
                 :aria-label "Toggle Navigation"}))
          ($ c/flex {:flex #js {:base 1}
                     :justify #js {:base "end" :md "start"}}
             ($ c/text
                {:textAlign
                 (c/use-breakpoint-value
                  #js {:base "center" :md "left"})
                 :fontFamily "Sofia"
                 :color "pink.400"}
                "memento")
             ($ c/flex
                {:display #js {:base "none" :md "flex"}
                 :ml 10}
                ($ desktop-nav))))
       ($ c/collapse {:in isOpen :animateOpacity true} ($ mobile-nav)))))
