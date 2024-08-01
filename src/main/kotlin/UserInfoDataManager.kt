import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Stream

class UserInfoDataManager(private val params: UserInfoParams) {

    private var connection: HttpURLConnection? = null

    init {
        createConnection()
    }

    fun getConnection() = if (connection == null) createConnection() else connection

    fun readInputStream(inputStream: InputStream): List<String> {
        val linesList = mutableListOf<String>()
        val reader = BufferedReader(InputStreamReader(inputStream))
        val lines = reader.lines()
        lines.forEach {
            linesList.add(it)
        }
        reader.close()
        return linesList
    }

    private fun createConnection() = (URL(params.sourceUrl).openConnection() as HttpURLConnection).also {
        it.requestMethod = params.requestMethod
    }
}