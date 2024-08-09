data class User(val name: String, val email: String, val phone: String) {

    fun mapToCSV() = "$name,$email,$phone,"

    companion object {
        fun mapFromCSV(line: String): User? {
            val userInfoList = line.split(",")
            if (userInfoList.size < 3) return null
            return User(userInfoList[0], userInfoList[1], userInfoList[2])
        }
    }
}