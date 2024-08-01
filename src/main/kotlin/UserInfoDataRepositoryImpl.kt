import UserInfoDataRepository.Companion.dataSeparator
import UserInfoDataRepository.Companion.errorMessage
import UserInfoDataRepository.Companion.requestMethod
import UserInfoDataRepository.Companion.sourceUrl
import java.net.HttpURLConnection

class UserInfoDataRepositoryImpl : UserInfoDataRepository {

    override fun fetchUserInfoData(): String {
        val userInfoParams = UserInfoParams(sourceUrl, requestMethod)
        val userInfoDataManager = UserInfoDataManager(userInfoParams)
        val connection = userInfoDataManager.getConnection()
        try {
            if (connection?.responseCode == HttpURLConnection.HTTP_OK) {
                val lines = userInfoDataManager.readInputStream(connection.inputStream).toMutableList()
                lines.removeAt(0)
                val response = StringBuilder()
                lines.forEach {
                    response.apply {
                        append(it)
                        append(dataSeparator)
                    }
                }
                return response.toString()
            } else {
                println(errorMessage)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.disconnect()
        }
        return errorMessage
    }
}