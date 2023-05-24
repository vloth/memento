(ns main.lib.helix
  #?(:clj (:require [helix.core :as helix]))
  #?(:cljs (:require-macros [main.lib.helix])))

#?(:clj
   (defmacro defnc [type params & body]
     (let [opts? (map? (first body)) ;; whether an opts map was passed in
           opts (if opts?
                  (first body)
                  {})
           body (if opts?
                  (rest body)
                  body)
           default-opts {:helix/features {:fast-refresh true}}]
       `(helix.core/defnc ~type ~params
          ~(merge default-opts opts)
          ~@body))))
