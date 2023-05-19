(ns node
  (:require ["global-jsdom" :as global-jsdom]
            [cljs.test :as test]))

(defn supress-errors [errors]
  (let [print-error (.-error js/console)]
    (set! (.-error js/console)
          (fn [& inputs]
            (when-not (some (fn [m] (re-matches m (first inputs)))
                            errors)
              (apply print-error inputs))))))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn main []
  (supress-errors #{#"Warning: Function components cannot be given refs.*"})
  (global-jsdom)
  (test/run-all-tests #".*-test$"))
