package kdp.Chapter03

interface Trooper {
    fun move(x: Long, y: Long)
    fun attackRebel(x: Long, y: Long)
}

const val RIFLE_DAMAGE = 3L
const val REGULAR_SPEED: Meters = 1

data class StormTrooper(
    private val weapon: Weapon,
    private val legs: Legs
) : Trooper {

    override fun move(x: Long, y: Long) {
        legs.move(x, y)
    }

    override fun attackRebel(x: Long, y: Long) {
        weapon.attach(x, y)
    }
}

typealias PointsOfDamage = Long
typealias Meters = Int

interface Weapon {
    fun attach(x: Long, y: Long): PointsOfDamage
}

interface Legs {
    fun move(x: Long, y: Long): Meters
}

class Rifle: Weapon {
    override fun attach(x: Long, y: Long): PointsOfDamage = RIFLE_DAMAGE
}

class FlameThrower: Weapon {
    override fun attach(x: Long, y: Long): PointsOfDamage = RIFLE_DAMAGE * 3
}

class Batton: Weapon {
    override fun attach(x: Long, y: Long): PointsOfDamage = RIFLE_DAMAGE * 5
}

class RegularLegs: Legs {
    override fun move(x: Long, y: Long): Meters = REGULAR_SPEED
}

class AthleticLegs: Legs {
    override fun move(x: Long, y: Long): Meters = REGULAR_SPEED * 2
}

val stormTrooper = StormTrooper(Rifle(), RegularLegs())
val scoutTropper = StormTrooper(Rifle(), AthleticLegs())