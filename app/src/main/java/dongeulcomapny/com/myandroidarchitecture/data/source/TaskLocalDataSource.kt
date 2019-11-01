package dongeulcomapny.com.myandroidarchitecture.data.source

import com.orhanobut.logger.Logger
import dongeulcomapny.com.myandroidarchitecture.data.TaskDataSource
import dongeulcomapny.com.myandroidarchitecture.data.User
import dongeulcomapny.com.myandroidarchitecture.data.network.Result
import dongeulcomapny.com.myandroidarchitecture.database.TaskDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class TaskLocalDataSource internal constructor(
    private val taskDao : TaskDao,
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO
):TaskDataSource {
    override suspend fun saveUser(user: User) = withContext(ioDispatcher){
        Logger.d(user)
        taskDao.insertUser(user)
    }

    override suspend fun getUser(): Result<List<User>> = withContext(ioDispatcher){
        return@withContext try {
            Result.CallBackSuccess(taskDao.getUser())
        } catch (e: Exception) {
            Result.CallBackError(e)
        }
    }



    override suspend fun getUser(userNo: Long): Result<User> = withContext(ioDispatcher){
        try {
            val user = taskDao.getUser(userNo)
            if (user != null) {
                return@withContext Result.CallBackSuccess(user)
            } else {
                return@withContext Result.CallBackError(Exception("asdfadsf"))
            }
        } catch (e: Exception) {
            return@withContext Result.CallBackError(Exception("not found"))
        }
    }

}