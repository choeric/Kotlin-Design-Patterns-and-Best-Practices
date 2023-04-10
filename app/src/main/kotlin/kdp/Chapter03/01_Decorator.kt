package kdp.Chapter03

//open class StarTrekRepository {
//    private val starshipCaptains = mutableMapOf("USS Enterprise" to "Jean-Luc Picard")
//
//    open fun getCaptain(starshipName:String)= starshipCaptains[starshipName] ?: "Unknown"
//
//    open fun addCaptain(starshipName: String, captainName: String) {
//        starshipCaptains[starshipName] = captainName
//    }
//}
//
//class LoggingGetCaptainStarTrekRepository: StarTrekRepository() {
//    override fun getCaptain(starshipName: String): String {
//        if (starshipName.length > 15) {
//            throw RuntimeException("")
//        }
//        return super.getCaptain(starshipName)
//    }
//}

interface StarTrekRepository {
//    fun getCaptain(starshipName: String): String
//    fun addCaptain(starshipName: String, captainName: String)
    operator fun get(starshipName: String): String
    operator fun set(starshipName: String, captainName: String)
}

class DefaultStarTrekRepository : StarTrekRepository {
    private val starshipCaptains = mutableMapOf("USS Enterprise" to "Jean-Luc Picard")

//    override fun getCaptain(starshipName: String): String {
//        return starshipCaptains[starshipName] ?: "Unknown"
//    }
//
//    override fun addCaptain(starshipName: String, captainName: String) {
//        starshipCaptains[starshipName] = captainName
//    }

    override fun get(starshipName: String): String {
        return starshipCaptains[starshipName] ?: "Unknown"
    }

    override fun set(starshipName: String, captainName: String) {
        starshipCaptains[starshipName] = captainName
    }
}

class LoggingGetCaptain(private val repository: StarTrekRepository) : StarTrekRepository by repository {
    override fun get(starshipName: String): String {
        println("Getting captain for $starshipName")
        return repository.get(starshipName)
    }
}

class ValidatingAdd(private val repository: StarTrekRepository) : StarTrekRepository by repository {
    private val maxNameLength = 15
    override fun set(starshipName: String, captainName: String) {
        require(captainName.length < maxNameLength) {
            "$captainName name is longer than $maxNameLength characters!"
        }
        repository.set(starshipName, captainName)
    }
}

fun main() {
    val defaultStarTrekRepository = DefaultStarTrekRepository()
    val withValidating = ValidatingAdd(defaultStarTrekRepository)
    val withLoggingAndValidating = LoggingGetCaptain(withValidating)
//    withLoggingAndValidating.getCaptain("USS Enterprise")
//    withLoggingAndValidating.addCaptain("USS Voyager", "Kathryn Janewa")
    withLoggingAndValidating["USS Enterprise"]
    withLoggingAndValidating["USS Voyager"] = "Kathryn Janeway"

    println(withLoggingAndValidating is LoggingGetCaptain) // This is our top level decorator, no problem here
    println(withLoggingAndValidating is StarTrekRepository) // This is the interface we implement, still no problem
//    println(withLoggingAndValidating is ValidatingAdd) // We wrap this class, but compiler cannot validate it
//    println(withLoggingAndValidating is DefaultStarTrekRepository) // We wrap this class, but compiler cannot validate it
}