(ns state.atoms)

(def a (atom 0))
(def b (atom 1))

(defn slow [f] (Thread/sleep 300) f)
(defn slower [f] (Thread/sleep 400) f)

(future
  (do
    (swap! a (comp slow inc))
    (swap! b (comp slower dec))))

(future
  (loop [i 10]
    (when (pos? i)
      (do
        (prn (str "a, b = " @a "," @b))
        (Thread/sleep 100)
        (recur (dec i))))))