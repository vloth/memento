(ns main.layout.navbar-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [helix.core :refer [$]]
            [lib.test :as t]
            [main.layout.navbar :as layout.navbar]
            [main.lib.core :refer [not-nil?]]))

(use-fixtures :each {:after t/cleanup})

(deftest menu-tests
  (let [navbar (t/render ($ layout.navbar/navbar))]

    (testing "Render top menus"
      (doseq [[id] layout.navbar/nav-items]
        (is (not-nil? (t/get-by-testid navbar id)))))

    (testing "Render sub menus"
      (doseq [[id] (mapcat :children layout.navbar/nav-items)]
        (is (not-nil? (t/get-by-testid navbar id)))))

    (testing "Navigation"
      (let [[id last-menu] (last layout.navbar/nav-items)]
        (t/click (t/get-by-testid navbar id))
        (is (= (:href last-menu) (t/get-current-url)))
        (t/set-current-url! "/")))))
