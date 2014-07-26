(ns cljs-bot.core
  (:require-macros
    [cljs.core.async.macros :refer [go]])
  (:require
    [cljs.core.async :refer [<! timeout chan]]))

(enable-console-print!)

(def five (js/require "johnny-five"))
(def Board (.-Board five))
(def Led (.-Led five))

(def board (Board.))

(defn rand-int2
  [a b]
  (+ a (rand-int (inc (- b a)))))

(defn go-go-bounce!
  [leds rate bounces]
  (go
    (dotimes [_ bounces]

      ; do a bounce
      (doseq [i [0 1 2 3 4 3 2 1]]

        ; turn on/off appropriate lights
        (doseq [[j led] (map-indexed vector leds)]
          (if (= i j)
            (.on led)
            (.off led)))

        (if (= i 4)
          (<! (timeout (* 2 rate))))

        ; pause
        (<! (timeout rate))))

    ; turn off all lights
    (doseq [led leds] (.off led))))

(defn go-go-count!
  [leds rate]
  (go
    (dotimes [i 32]

      ; turn on/off appropriate lights
      (doseq [[j led] (map-indexed vector leds)]
        (if (= 1 (bit-and (bit-shift-right i j) 1))
          (.on led)
          (.off led)))

      (<! (timeout rate))

    )))

(defn on-ready
  []
  (let [leds [(Led. 8)
              (Led. 9)
              (Led. 10)
              (Led. 11)
              (Led. 12)]]

    (go
      (loop []
        (let [f (rand-nth
                  [#(go-go-bounce! leds
                                   (rand-int2 25 100)
                                   (rand-int2 1 3))
                   #(go-go-count! leds
                                  (rand-int2 25 100))
                   ])]
          (<! (f)))
        (recur)))
    
    ))

(defn -main [& args]
  (.on board "ready" on-ready))

(set! *main-cli-fn* -main)
