import java.net.HttpURLConnection

class UserInfoDataRepositoryImpl : UserInfoDataRepository {

    override fun fetchUserInfoData(): String {
        val userInfoParams = UserInfoParams(Utils.sourceUrl, Utils.requestMethod)
        val userInfoDataManager = UserInfoDataManager(userInfoParams)
        val connection = userInfoDataManager.getConnection()
        try {
            if (connection?.responseCode == HttpURLConnection.HTTP_OK) {
                val users = mutableListOf<User>()
                val lines = userInfoDataManager.readInputStream(connection.inputStream).toMutableList()
                val response = StringBuilder()
                lines.removeFirst()
                lines.forEach {
                    User.mapFromCSV(it)?.let { user ->
                        users.add(user)
                    }
                }
                val sortedList = users.sortedBy { it.email }
                sortedList.forEach {
                    response.apply {
                        response.append(it.mapToCSV())
                    }
                }
                return response.toString().removeSuffix(Utils.dataSeparator)
            } else {
                println(Utils.errorMessage)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.disconnect()
        }
        return Utils.errorMessage
    }
}