(ns main.pages.home-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [helix.core :refer [$]]
            [lib.test :as t]
            [main.pages.home :refer [home-page]]))

(use-fixtures :each {:after t/cleanup})

(deftest home-page-tests
  (let [page (t/render ($ home-page))]

    (testing "Search"
      (t/type-in (t/get-by-testid page "search") "foo bar")
      (t/submit (t/get-by-testid page "search-form"))
      (is (= "/code?search=foo+bar" (t/get-current-url)))
      (t/set-current-url! "/"))))

