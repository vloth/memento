(ns main.layout.navbar-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [helix.core :refer [$]]
            [lib.test :as t]
            [main.layout.navbar :as layout.navbar]
            [main.lib.core :refer [not-nil?]]))

(use-fixtures :each {:after t/cleanup})

(deftest menu-tests
  (let [navbar (t/render ($ layout.navbar/desktop-nav))
        nav-items (vals layout.navbar/nav-items)]

    (testing "Render top menus"
      (doseq [{:keys [label]} nav-items]
        (is (not-nil? (t/get-by-role navbar "link" #js {:name label})))))


    ; TODO: Cannot find invisible role in the screen
    ; maybe we should click on top menu and have the sub navs be rendered
    ; so that we can assert on them, also sounds like a better test
    ; (testing "Render sub menus"
    ;   (doseq [item (->> (vals layout.navbar/nav-items)
    ;                     (mapcat :children)
    ;                     (map second))]
    ;     (is (not-nil? (t/get-by-role navbar "link" #js {:name (:label item) :hidden true})))))

    (testing "Navigation"
      (let [{:keys [label href]} (last nav-items)]
        (t/click (t/get-by-role navbar "link" #js {:name label}))
        (is (= href (t/get-current-url)))
        (t/set-current-url! "/")))))
