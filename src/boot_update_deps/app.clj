(ns boot-update-deps.app
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [boot.core :as boot :refer [deftask]])
  (:import [java.io PushbackReader]))

(def all-jars-url "http://clojars.org/repo/all-jars.clj")
(def all-jars
  (with-open [r (PushbackReader. (io/reader all-jars-url))]
    (let [edn-seq (repeatedly (partial edn/read {:eof :EOF} r))]
      (doall (take-while #(not= :EOF %) edn-seq)))))

(defn in?
  "true if seq contains elm"
  [seq elm]
  (some #(= elm %) seq))

(defn update [deps]
  (let [dep-names (map (comp str first) deps)]
    (filter #(in? dep-names (-> % first str)) all-jars)))

(defn replace [deps])

(deftask update-deps
  "Update dependencies"
  []
  (boot/with-pre-wrap fileset
    (update (:dependencies (boot/get-env)))
    fileset))
