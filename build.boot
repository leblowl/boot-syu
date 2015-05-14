(set-env!
  :target-path "target"
  :resource-paths #{"resources"}
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.6.0"]
                  [slingshot "0.12.2"]
                  [ancient-clj "0.3.9"]])

(require
  '[boot-update-deps.app :as app])

(deftask test
  "Test app task."
  []
  (app/update-deps))
