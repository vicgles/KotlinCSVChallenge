fun main() {
    fetchUserInfoData()
}

fun fetchUserInfoData() {
    val userInfoDataRepository = UserInfoDataRepositoryImpl()
    val response = userInfoDataRepository.fetchUserInfoData()
    println(response)
}