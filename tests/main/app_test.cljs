(ns main.app-test
  (:require [clojure.test :refer [testing deftest is]]))


(deftest a-test
  (is (= 1 1)))

(testing "dummy tests"
  (deftest dummy-test
    (is (= {:value 1}
           {:value 1}))))
