interface UserInfoDataRepository {

    fun fetchUserInfoData(): String

    companion object {
        val sourceUrl = "https://coderbyte.com/api/challenges/logs/user-info-csv"
        val requestMethod = "GET"
        val errorMessage = "Error while fetching User Info"
        val dataSeparator = ","
    }
}