(def v+ (fn [a, b] (mapv + a b)))
(def v- (fn [a, b] (mapv - a b)))
(def v* (fn [a, b] (mapv * a b)))
(def vd (fn [a, b] (mapv / a b)))
(defn scalar [v, u] (apply + (v* v u)))
(defn vect [v, u] [(- (* (nth v 1) (nth u 2)) (* (nth v 2) (nth u 1))), (- (* (nth v 2) (nth u 0)) (* (nth v 0) (nth u 2))), (- (* (nth v 0) (nth u 1)) (* (nth v 1) (nth u 0)))])
(defn v*s [v, a] (mapv * v (iterate inc a)))
(def m+ (fn [a, b] (mapv v+ a b)))
(def m- (fn [a, b] (mapv v- a b)))
(def m* (fn [a, b] (mapv v* a b)))
(def md (fn [a, b] (mapv vd a b)))
(defn transpose [A] (apply mapv vector A))
(defn m*s [A, a] (mapv v*s A (iterate inc a)))
(defn m*v [A, v] (mapv scalar A (iterate partial v)))
(defn m*m [A, B] (mapv (fn [v] (mapv scalar (transpose B) (iterate partial v))) A))
(def c+ (fn [a, b] (mapv m+ a b)))
(def c- (fn [a, b] (mapv m- a b)))
(def c* (fn [a, b] (mapv m* a b)))
(def cd (fn [a, b] (mapv md a b)))