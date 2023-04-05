package kdp.Chapter02


interface Property {
    val name: String
    val value: Any
}

interface ServerConfiguration {
    val properties: List<Property>
}

data class PropertyImpl(override val name: String, override val value: Any) :Property

data class ServerConfigurationImpl(override val properties: List<Property>) : ServerConfiguration
/*
fun property(prop: String): Property {
    val (name, value) = prop.split(":")
    return when (name) {
//        "port" -> PropertyImpl(name, value.trim().toInt())
//        "enviroment" -> PropertyImpl(name, value.trim())
        "port" -> IntProperty(name, value.trim().toInt())
        "enviroment" -> StringProperty(name, value.trim())
        else -> throw RuntimeException("Unknown property: $name")
    }
}

fun server(propertyStrings: List<String>): ServerConfiguration {
    val parsedProperties = mutableListOf<Property>()
    for (p in propertyStrings) {
        parsedProperties += property(p)
    }
    return ServerConfigurationImpl(parsedProperties)
}
*/

data class IntProperty(override val name: String, override val value: Int) : Property

data class StringProperty(override val name: String, override val value: String) : Property

class Parser {
    companion object {
        fun property(prop: String): Property {
            val (name, value) = prop.split(":")
            return when (name) {
                "port" -> IntProperty(name, value.trim().toInt())
                "enviroment" -> StringProperty(name, value.trim())
                else -> throw RuntimeException("Unknown property: $name")
            }
        }

        fun server(propertyStrings: List<String>): ServerConfiguration {
            val parsedProperties = mutableListOf<Property>()
            for (p in propertyStrings) {
                parsedProperties += property(p)
            }
            return ServerConfigurationImpl(parsedProperties)
        }
    }
}

fun main() {
//    val portProperty = property("port: 8080")
//    val enviroment = property("enviroment: production")

//    val port: Int? = portProperty as? Int     // safa cast
//    val port: Int = if (portProperty is IntProperty) portProperty.value else 0  // smart casts

//    println(server(listOf("port: 8080", "enviroment: production")))

    val portProperty = Parser.property("port: 8080")
    val enviroment = Parser.property("enviroment: production")
    val server = Parser.server(listOf("port: 8080", "enviroment: production"))
}