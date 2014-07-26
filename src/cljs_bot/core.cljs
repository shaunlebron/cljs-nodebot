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

(defn go-go-blink!
  [led]
  (go
    (loop []
      (.on led)
      (<! (timeout 100))
      (.off led)
      (<! (timeout 1000))
      (recur)))
  )

(defn on-ready
  []
  (let [led (Led. 13)]
    (.. board -repl (inject #js {:led led}))
    (go-go-blink! led)
  ))

(defn -main [& args]
  (.on board "ready" on-ready))

(set! *main-cli-fn* -main)
