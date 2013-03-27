(ns clojure-course-task02.core
  (:gen-class))

(defn find-files [file-name path]
  "Implement searching for a file using his name as a regexp."
  (let [root (clojure.java.io/as-file path)
        f #(find-files file-name %)
        file-name-re (re-pattern file-name)]
    (if 
      (.isDirectory root)
        (flatten (pmap f (.listFiles root)))
        (let [name (.getName root)]
          (if (re-matches file-name-re name) [name] [])))))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))
