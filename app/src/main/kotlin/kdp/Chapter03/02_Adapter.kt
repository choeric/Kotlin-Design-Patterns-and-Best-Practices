package kdp.Chapter03

interface USPlug {
    val hasPower: Int
}

interface EUPlug {
    val hasPower: String
}

interface UsbMini {
    val hasPower: Power
}

enum class Power {
    TRUE, FALSE
}

interface UsbTypeC {
    val hasPower: Boolean
}

fun cellPhone(chargeCable: UsbTypeC) {
    if (chargeCable.hasPower)
        println("I've Got The Power!")
    else
        println("No power")
}

fun usPowerOutlet() = object: USPlug {
    override val hasPower = 1
}

fun charge(plug: EUPlug): UsbMini {
    return object : UsbMini {
        override val hasPower: Power
            get() = Power.valueOf(plug.hasPower)
    }
}

fun USPlug.toEUPlug(): EUPlug {
    val hasPower = if (this.hasPower == 1) "TRUE" else "FALSE"
    return object : EUPlug {
        override val hasPower: String
            get() = hasPower

    }
}

fun UsbMini.toUsbTypeC(): UsbTypeC {
    val hasPower = this.hasPower == Power.TRUE
    return object : UsbTypeC {
        override val hasPower: Boolean
            get() = hasPower
    }
}

val ret = cellPhone(
    charge(
        usPowerOutlet().toEUPlug()
    ).toUsbTypeC()
)