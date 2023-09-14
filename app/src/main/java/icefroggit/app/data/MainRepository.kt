package icefroggit.app.data

import icefroggit.app.domain.networking.RetrofitApi
import icefroggit.app.domain.networking.RetrofitServices

class MainRepository {
    fun retroService(): RetrofitServices = RetrofitApi.apiService
}