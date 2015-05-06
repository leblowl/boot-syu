(ns boot-update.app
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn])
  (:import [java.io PushbackReader]))

(def all-jars-url "http://clojars.org/repo/all-jars.clj")

(def all-jars
  (with-open [r (PushbackReader. (io/reader all-jars-url))]
    (let [edn-seq (repeatedly (partial edn/read {:eof :EOF} r))]
      (doall (take-while #(not= :EOF %) edn-seq)))))
