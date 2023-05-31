(ns main.pages.home
  (:require ["react" :as r]
            ["react-router-dom" :as rrd]
            [helix.core :refer [$]]
            [helix.dom :as d]
            [main.lib.chakra :as c]
            [main.lib.helix :refer [defnc]]))

(defn submit-search [navigate search]
  (fn [event]
    (.preventDefault event)
    (navigate (str "/search/" (js/decodeURIComponent search)))))

(defnc home-page []
  (let [[search set-search] (r/useState "")
        navigate (rrd/useNavigate)]
    ($ c/container {:maxW "3xl"}
       ($ c/stack
          {:as c/box
           :textAlign "center"
           :spacing #js {:base 8 :md 14}
           :py #js {:base 20 :md 36}}
          ($ c/heading
             {:fontWeight 600
              :fontSize #js {:base "2xl" :sm "4xl" :md "6xl"}
              :lineHeight "110%"}
             "Learn leet code from"
             (d/br)
             ($ c/text
                {:as "span" :color "pink.400" :fontFamily "Sofia"}
                "memento") ".")
          ($ c/text {:color "gray.500"}
             "Memento has hundred of solved leet code
              exercises for you to peek.")
          (d/form {:onSubmit (submit-search navigate search)
                   :data-testid "search-form"}
                  ($ c/input {:variant "flushed"
                              :type "text"
                              :data-testid "search"
                              :onChange #(set-search (.. % -target -value))
                              :value search
                              :placeholder "Start by searching..."
                              :borderColor "gray.400"
                              :_placeholder #js {:color "gray.400"}}))))))
