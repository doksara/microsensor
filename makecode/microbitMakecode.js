basic.forever(function () {
    bluetooth.advertiseUrl(
    "M1" + ";" + input.temperature() + ";" + input.lightLevel() + ";" + pins.analogReadPin(AnalogPin.P0),
    5,
    false
    )
})
