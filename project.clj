(defproject cljs-bot "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2268"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]]

  :source-paths ["src"]

  :cljsbuild {
    :builds [{:id "cljs-bot"
              :source-paths ["src"]
              :compiler {
                :target :nodejs
                :optimizations :simple
                :output-to "cljs_bot.js"
                :output-dir "out"
                }}]})
