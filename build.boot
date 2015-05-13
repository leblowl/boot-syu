(set-env!
  :target-path "target"
  :resource-paths #{"resources"}
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.6.0"]
                  [version-clj "0.1.2"]])

(require
  '[boot-update-deps.app :as app])

(deftask test
  "Test app task."
  []
  (app/update-deps))
