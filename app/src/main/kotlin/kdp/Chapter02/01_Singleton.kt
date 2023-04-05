package kdp.Chapter02


object Logger {
    init {
        println("I was accessed for the first time")
        // Initialization logic goes here
    }
}

fun main() {
    Logger
    println("singleton run")
}