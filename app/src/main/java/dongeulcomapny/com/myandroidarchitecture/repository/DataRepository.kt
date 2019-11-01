package dongeulcomapny.com.myandroidarchitecture.repository

import dongeulcomapny.com.myandroidarchitecture.data.User
import dongeulcomapny.com.myandroidarchitecture.data.network.Result

interface DataRepository {

    suspend fun saveUser()
    suspend fun deleteData()
    suspend fun getUserData(forceUpdate: Boolean = false): Result<List<User>>
}