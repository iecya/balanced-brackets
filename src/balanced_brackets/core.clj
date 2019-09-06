(ns balanced-brackets.core
  (:gen-class))

(def open-brackets #{\{ \[ \(})
                       (def close-brackets #{\} \] \)})

(defn openClosedBracketsCount
  [s]
  (let [open-brackets (filter open-brackets s)
        close-brackets (filter close-brackets s)]
    (= (count open-brackets) (count close-brackets))))

(def mapping {\} \{
              \] \[
              \) \(})

(defn matchBrackets
  [s]
  (loop [[x & xs] s
         stack []]
    (cond
      (not x)
      stack

      (open-brackets x)
      (recur xs (conj stack x))

      (close-brackets x)
      (do
        (if (= (last stack) (get mapping x))
          (recur xs (vec (drop-last stack)))
          (recur nil (conj stack x))))

      :else
      (recur xs stack))))

(defn isBalanced [s]
  (cond
    ; if s lenth = 0 returns NO
    (not (seq s)) "NO"

    ; if s length = odd return NO
    (odd? (count s)) "NO"

    (close-brackets (first s)) "NO"

    ; if open brackets count != close brackets count return NO
    (not (openClosedBracketsCount s)) "NO"

    ; if close brackets position doesn't match open bracket position return no
    (seq (matchBrackets s)) "NO"

    :else "YES"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (when (and args (= 1 (count args)) (string? (first args)))
    (println "Result is: "(isBalanced (first args)))))
