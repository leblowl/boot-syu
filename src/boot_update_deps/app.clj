(ns boot-update-deps.app
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [boot.core :as boot :refer [deftask set-env!]]
            [ancient-clj.core :as ancient]))

(defn get-latest-deps [deps opts]
  (let [get-latest-version #(ancient/artifact-outdated-string? % opts)
        artifacts (map ancient/read-artifact deps)
        latest-versions (remove nil? (map get-latest-version artifacts))]
    (map vector (map first deps) latest-versions)))

(defn replace-deps [deps latest-deps]
  (let [f "build.boot"
        dep-strs (map str deps)
        latest-dep-strs (map str latest-deps)
        replacements (map vector dep-strs latest-dep-strs)]
    (spit f (reduce #(apply str/replace %1 %2) (slurp f) replacements))))

(defn syu
  ([]
   (syu {:snapshots? false :qualified? false}))
  ([opts]
   (let [deps (:dependencies (boot/get-env))
         latest-deps (get-latest-deps deps opts)]
     (replace-deps deps latest-deps))))

(deftask update-deps
  "Update all project dependencies."
  [s snapshots bool "include SNAPSHOT versions"
   q qualified bool "include alpha, beta, etc... versions"
   a all       bool "include snapshots and qualified versions"]
  (boot/with-pre-wrap fileset
    (let [opts {:snapshots? (or snapshots all)
                :qualified? (or qualified all)}]
      (syu opts))
    fileset))
