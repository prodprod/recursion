(ns recursion)

(defn product [coll]
  (if (empty? coll) 1
    (* (first coll)
       (product (rest coll)))))

(defn singleton? [coll]
 (if (and ((complement empty?) coll) (empty? (rest coll))) true false ))

(defn my-last [coll]
  (if (empty? coll) nil
    (if (singleton? coll) (first coll)
    (my-last (rest coll)))))

(defn max-element [a-seq]
  (if (empty? a-seq) nil
    (if (singleton? a-seq) (first a-seq)
      (max (first a-seq) (max-element (rest a-seq))))))

(defn seq-max [seq-1 seq-2]
  (if (> (count seq-1) (count seq-2)) seq-1 seq-2))

(defn longest-sequence [a-seq]
  (if (empty? a-seq) nil
    (if (singleton? a-seq) (first a-seq)
      (seq-max (first a-seq) (longest-sequence (rest a-seq))))))

(defn my-filter [pred? a-seq]
  (if (empty? a-seq)
    a-seq
    (if (pred? (first a-seq))
      (cons (first a-seq) (my-filter pred? (rest a-seq)))
      (my-filter pred? (rest a-seq)))))

(defn sequence-contains? [elem a-seq]
  (if (empty? a-seq) false
    (if (= elem (first a-seq)) true
      (sequence-contains? elem (rest a-seq)))))

(defn my-take-while [pred? a-seq]
  (if (empty? a-seq) '()
    (if (pred? (first a-seq))
      (cons (first a-seq) (my-take-while pred? (rest a-seq)))
      '() )))

(defn my-drop-while [pred? a-seq]
  (if (empty? a-seq) '()
    (if (pred? (first a-seq))
      (my-drop-while pred? (rest a-seq))
       a-seq )))

(defn seq= [a-seq b-seq]
  (cond
    (and (empty? a-seq) (empty? b-seq)) true
    (and (empty? a-seq) (not (empty? b-seq))) false
    (and (empty? b-seq) (not (empty? a-seq))) false
    (= (first a-seq) (first b-seq)) (seq= (rest a-seq) (rest b-seq))
     :else false))

(defn my-map [f seq-1 seq-2]
  (cond
   (or (empty? seq-1) (empty? seq-2)) (sequence [])
   :else (cons (f (first seq-1) (first seq-2)) (my-map f (rest seq-1) (rest seq-2)))))

(defn power [n k]
  (cond
   (= n 0) 0
   (= k 0) 1
   :else (* n (power n (- k 1)))))

(defn fib [n]
  (cond
   (= n 0) 0
   (= n 1) 1
   :else (+ (fib (- n 1)) (fib (- n 2)))))

(defn my-repeat [how-many-times what-to-repeat]
  (if (< how-many-times 1) (sequence [])
    (cons what-to-repeat (my-repeat (- how-many-times 1) what-to-repeat))))

(defn my-range [up-to]
  (if (< up-to 1) (sequence [])
    (cons (- up-to 1) (my-range (- up-to 1)))))

(defn tails [a-seq]
  (if (empty? a-seq) (cons (sequence []) (sequence []))
    (cons (sequence a-seq) (tails (rest a-seq)))))

(defn inits [a-seq]
 (reverse (map reverse (tails (reverse a-seq)))))

(defn rotations [a-seq]
  (cond
   (empty? a-seq) (cons (sequence []) (sequence []))
   (singleton? a-seq) (sequence a-seq)
   :else (rest (map concat (tails a-seq) (inits a-seq)))))

(defn my-frequencies-helper [freqs a-seq]
  (cond
   (empty? a-seq) freqs
   (contains? freqs (first a-seq)) (my-frequencies-helper (assoc freqs (first a-seq) (+ (get freqs (first a-seq)) 1)) (rest a-seq))
   :else (my-frequencies-helper (assoc freqs (first a-seq) 1) (rest a-seq))))

(defn my-frequencies [a-seq]
 (my-frequencies-helper {} a-seq))

(defn un-frequencies [a-map]
  (if (empty? a-map) '[]
   (concat
    (repeat (first (vals a-map)) (first (keys a-map)))
    (un-frequencies (rest a-map)))))

(defn my-take [n coll]
  (if (or (= n 0) (empty? coll)) (sequence [])
   (sequence (cons (first coll) (my-take (- n 1) (rest coll))))))

(defn my-drop [n coll]
  (if (= n 0) (sequence coll)
   (sequence (my-drop (- n 1) (rest coll)) )))

(defn halve [a-seq]
  (let [n (int (/ (count a-seq) 2))]
   (cons (my-take n a-seq) (cons (my-drop n a-seq) '[]))))

(defn seq-merge [a-seq b-seq]
  (cond
   (empty? a-seq) b-seq
   (empty? b-seq) a-seq
   (> (first a-seq) (first b-seq)) (cons (first b-seq) (seq-merge a-seq (rest b-seq)))
   :else (cons (first a-seq) (seq-merge (rest a-seq) b-seq))))

(defn merge-sort [a-seq]
  (cond
   (or (empty? a-seq) (singleton? a-seq)) a-seq
   :else (seq-merge (merge-sort(first (halve a-seq))) (merge-sort(second (halve a-seq))))))

(defn monotonic? [a-seq]
  (if (empty? a-seq) true
    (or (apply >= a-seq) (apply <= a-seq))))

(defn split-into-monotonics [a-seq]
  (let [i (take-while monotonic? (inits a-seq))]
    (if (empty? a-seq) '()
      (cons (last i) (split-into-monotonics (drop (- (count i) 1)  a-seq))))))


(defn permutations [a-set]
)

(defn powerset [a-set]
  [:-])

