(ns wal.core-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [wal.core :refer :all]))

(def empty-file "test/fixtures/empty-file")

(defn reset-fixture
  [tests]
  (io/delete-file empty-file :silently true)
  (tests))

(use-fixtures :each reset-fixture)

(deftest write-to-file-test
  (testing "Does the message get written to the file?"
    (let [test-string "test"]
      (write-and-return test-string empty-file)
      (is (= (slurp empty-file) (str test-string "\n"))))))

(deftest is-message-returned
  (testing "Does the message get returned from write-and-return?"
    (is (= (write-and-return "test" empty-file) '("test")))))

(deftest multiple-message-return
  (testing "Do multiple messages get returned if the log is not empty?"
    (write-and-return "test" empty-file)
    (is (= (write-and-return "test1" empty-file) '("test" "test1")))))
