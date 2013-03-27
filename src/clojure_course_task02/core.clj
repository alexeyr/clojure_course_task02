(ns clojure-course-task02.core
  (:gen-class))

(defn filter-files [pred path]
  (let [root (clojure.java.io/as-file path)
        f #(filter-files pred %)]
    (if 
      (.isDirectory root)
        (pmap f (.listFiles root))
        (let [name (.getName root)]
          (if (pred name) [name] [])))))

(defn find-files [file-name path]
  "Implement searching for a file using his name as a regexp."
  (let [file-name-re (re-pattern file-name)]
    (flatten (filter-files #(re-matches file-name-re %) path))))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))
