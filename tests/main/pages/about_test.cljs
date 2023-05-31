(ns main.pages.about-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [helix.core :refer [$]]
            [lib.test :as t]
            [main.lib.core :refer [not-nil?]]
            [main.pages.about :refer [about-page]]))

(use-fixtures :each {:after t/cleanup})

(deftest home-page-tests
  (let [page (t/render ($ about-page))]

    (testing "Description"
      (is (not-nil? (t/get-by-testid page "description"))))))
