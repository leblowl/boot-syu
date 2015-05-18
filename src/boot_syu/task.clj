(ns boot-syu.task
  (:require [boot-syu.core :as core]
            [boot.core :as boot :refer [deftask set-env!]]
            [boot.util :as util]))

(deftask syu
  "Update all project dependencies."
  [s snapshots bool "include SNAPSHOT versions"
   q qualified bool "include alpha, beta, etc... versions"
   a all       bool "include snapshots and qualified versions"]
  (boot/with-pre-wrap fileset
    (util/info "Updating project dependencies...\n")
    (let [opts {:snapshots? (or snapshots all)
                :qualified? (or qualified all)}]
      (core/syu opts))
    fileset))
