package dongeulcomapny.com.myandroidarchitecture.repository

import dongeulcomapny.com.myandroidarchitecture.data.TaskDataSource
import dongeulcomapny.com.myandroidarchitecture.data.User
import dongeulcomapny.com.myandroidarchitecture.data.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.concurrent.ConcurrentHashMap
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

            val newTasks = fetchTasksFromRemoteOrLocal(forceUpdate)
            (newTasks as Result.CallBackSuccess)?.let { refreshCache(it.data) }

            return@withContext newTasks

        }
    }

    private fun refreshCache(users: List<User>) {

        cachedUser?.clear()
        users.sortedBy {
            it.userName
        }.forEach {
            cacheAndPerform(it){}
        }

    }

    private fun cacheAndPerform(user: User, perform:(User)-> Unit) {

        val cachedTask = cacheTask(user)
        perform(cachedTask)

    }

    private fun cacheTask(user: User): User {

        val cachedTask = User(userName = user.userName, desc =  user.desc)

        if (cachedTask == null) {
            cachedUser = ConcurrentHashMap()
        }

        cachedUser?.put(cachedTask.userId, cachedTask)
        return cachedTask

    }

    private suspend fun fetchTasksFromRemoteOrLocal(forceUpdate: Boolean): Result<List<User>> {

        if (forceUpdate) {
            return Result.CallBackError(Exception("Can't force refresh remote data source is unavailable"))
        }

       val localTasks = dataLocalDataSource.getUser()
        if (localTasks is Result.CallBackSuccess) return localTasks
        return Result.CallBackError(Exception("Error fetching from remote and local"))

    }


    override suspend fun saveUser() {
        dataLocalDataSource.saveUser(User())
    }

    override suspend fun deleteData() {

    }
}