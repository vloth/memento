(ns main.core-test
  (:require [clojure.test :refer [are deftest]]
            [main.lib.core :refer [not-nil?]]))

(deftest not-nil?-tests
  (are [x y] (= (not-nil? x) y)
    nil false
    {}  true
    '() true))
