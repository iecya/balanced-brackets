(ns balanced-brackets.core-test
  (:require [clojure.test :refer :all]
            [balanced-brackets.core :refer :all]
            [clojure.java.io :as io]))

(defn- test-case
  [n]
  (let [hr-input-lines (->> (str "hr_inputs/test_case_" n ".txt") io/resource io/reader line-seq (drop 1))
        hr-output-lines (-> (str "hr_outputs/test_case_" n ".txt") io/resource io/reader line-seq vec)]
    (doseq [line hr-input-lines]
      (let [line-idx (.indexOf hr-input-lines line)
            result (isBalanced line)]
        (when-not (= (nth hr-output-lines line-idx) result)
          (println "Line: " line)
          (println "Line idx: " line-idx))
        (is (= (nth hr-output-lines line-idx) result))))))

(deftest test-all-case
  (testing "Testing HackeRank test case 6"
           (test-case 6))

  (testing "Testing HackeRank test case 7"
           (test-case 7))

  (testing "Testing HackeRank test case 9"
           (test-case 9))

  (testing "Testing HackeRank test case 10"
           (test-case 10))

  (testing "Testing HackeRank test case 11"
           (test-case 11))

  (testing "Testing HackeRank test case 16"
           (test-case 16))

  (testing "Testing HackeRank test case 17"
           (test-case 17)))
