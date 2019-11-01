package dongeulcomapny.com.myandroidarchitecture.repository

import dongeulcomapny.com.myandroidarchitecture.data.TaskDataSource
import dongeulcomapny.com.myandroidarchitecture.data.User
import dongeulcomapny.com.myandroidarchitecture.data.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.concurrent.ConcurrentMap

class DataRepositoryImpl constructor(
    private val dataRemoteDataSource: TaskDataSource,
    private val dataLocalDataSource: TaskDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DataRepository {

    private var cachedUser: ConcurrentMap<String, User>? = null

    override suspend fun getUserData(forceUpdate: Boolean): Result<List<User>> {
        return withContext(ioDispatcher){
            if (!forceUpdate) {
                cachedUser?.let { cachedUser->
                    return@withContext Result.CallBackSuccess(cachedUser.values.sortedBy {
                        it.userName
                    })
                }
            }
            return@withContext Result.CallBackError(Exception("illegal state"))
        }
    }


    override suspend fun saveUser() {
        dataLocalDataSource.saveUser(User())
    }

    override suspend fun deleteData() {

    }
}