package dongeulcomapny.com.myandroidarchitecture.data

import dongeulcomapny.com.myandroidarchitecture.data.network.Result

interface TaskDataSource {

    suspend fun getUser(): Result<List<User>>
    suspend fun getUser(userNo: Long): Result<User>
    suspend fun saveUser(user:User)
}