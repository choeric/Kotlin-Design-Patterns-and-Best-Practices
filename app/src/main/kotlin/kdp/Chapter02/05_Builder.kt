package kdp.Chapter02

data class Mail_V2(
    val to: List<String>,
    private var _message: String? = null,
    private var _cc: List<String>? = null,
    private var _title: String? = null,
    private var _important: Boolean? = null
) {
    fun message(message: String) = apply {
        this._message = message
    }
}

data class Mail_V3 (
    val to: List<String>,
    val cc: List<String> = listOf(),
    val title: String = "",
    val message: String = "",
    val important: Boolean = false
)

fun main() {
    val mailV2 = Mail_V2(listOf("manager@company.com")).message("Ping")
    println(mailV2)
}

