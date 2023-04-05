package kdp.Chapter02

data class User(
    val name: String,
    val role: Role,
    private val permission: Set<String>
)

enum class Role { ADMIN, SUPER_ADMIN, REGULAR_USER }

val allUsers = mutableListOf<User>()

fun createUser(_name: String, role: Role) {
    for (u in allUsers) {
        if (u.role == role) {
//            allUsers += User(name, role, u.permission)
            allUsers += u.copy(name = _name)
            return
        }
    }
}