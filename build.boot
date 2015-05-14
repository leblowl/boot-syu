(set-env!
  :target-path "target"
  :resource-paths #{"resources"}
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.6.0"]
                  [boot/core "2.0.0-rc14"]
                  [slingshot "0.12.2"]
                  [ancient-clj "0.3.9"]])

(require
  '[boot-syu.task :as task])

(deftask install-local
  "Install to local Maven repository."
  []
  (comp (aot :all true)
        (pom :project 'boot-syu :version "0.1.0-SNAPSHOT")
        (jar)
        (install)))
