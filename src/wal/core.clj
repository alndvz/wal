(ns wal.core)

;; Need to write something to a file, and make sure it's there
;; Needs to read a bunch of messages from the file
;; Once the messages have been read from the file they should
;; removed

;; The entry point to the WAL should be read a message
;; (write-and-return message file)
;; write the message to the file
;; return everything from the file

(defn write-and-return
  [message file]
  (spit file message :append true)
  (slurp file))
