(ns boot-syu.core
  (:require [clojure.string :as str]
            [boot.core :as boot]
            [ancient-clj.core :as ancient]))

(defn latest-version [dep opts]
  (let [artifact (ancient/read-artifact dep)]
    (or (ancient/latest-version-string! artifact opts)
        (second dep))))

(defn latest-dep [dep opts]
  [(first dep) (latest-version dep opts)])

(defn latest-deps [deps opts]
  (map #(latest-dep % opts) deps))

(defn replace-deps [deps latest-deps]
  (let [f "build.boot"
        replacements (map vector (map str deps) (map str latest-deps))
        replace-dep #(apply str/replace %1 %2)]
    (spit f (reduce replace-dep (slurp f) replacements))))

(defn syu
  ([]
   (syu {:snapshots? false :qualified? false}))
  ([opts]
   (let [deps (:dependencies (boot/get-env))
         latest (latest-deps deps opts)]
     (replace-deps deps latest))))
