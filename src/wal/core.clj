(ns wal.core
  (:require [clojure.java.io :as io]))

(defn write-and-return
  [message file]
  "Write a message to a file and return that message or messages from the file
   as a lazy seq"
  (spit file (str message "\n") :append true)
  (line-seq (io/reader file)))
