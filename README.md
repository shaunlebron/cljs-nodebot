# cljs-nodebot

Gathering to play with Arduino with ClojureScript through Node+Johnny-Five

- https://ti.to/houstonjs/nodebotsday
- http://www.meetup.com/houston-js/events/195628942/

![photo](http://i.imgur.com/ZOHGlSU.jpg)

### Arduino Setup

1. Download/Install/Open [Arduino IDE](http://arduino.cc/en/Main/Software).
1. Plug in your Arduino via USB.
1. File > Examples > Firmata > Standard Firmata.
1. Click Upload (choose the port ... tty usb)

### Javascript Setup (LED example)

1. Clone the [Johnny-Five repo](https://github.com/rwaldron/johnny-five)
1. Setup the board by reading the [LED walkthrough](https://github.com/rwaldron/johnny-five/blob/master/docs/led.md)
1. Run `node eg/led.js` from the repo root to blink your LED.

### ClojureScript Setup (LED blink)

This repo uses a go-block to blink the LEDs.

```
npm install
lein cljsbuild auto
node cljs_bot.js
```

## References

- [ClojureScript on NodeJS](https://github.com/clojure/clojurescript/wiki/Quick-Start#running-clojurescript-on-nodejs)
