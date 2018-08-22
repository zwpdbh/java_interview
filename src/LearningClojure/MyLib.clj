(ns LearningClojure.MyLib)

(defn average
  [numbers]
  (/ (apply + numbers) (count numbers)))
