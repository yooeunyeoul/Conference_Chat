package dongeulcomapny.com.myandroidarchitecture.data.source

import dongeulcomapny.com.myandroidarchitecture.data.TaskDataSource
import dongeulcomapny.com.myandroidarchitecture.data.User
import dongeulcomapny.com.myandroidarchitecture.data.network.Result
import java.lang.Exception
import java.util.LinkedHashMap

object FakeDataRemoteDataSource: TaskDataSource {
    override suspend fun saveUser(user: User) {


    }

    override suspend fun getUser(): Result<List<User>> {
        return Result.CallBackSuccess(TASKS_SERVICE_DATA.values.toList())

    }

    override suspend fun getUser(userNo: Long): Result<User> {
        TASKS_SERVICE_DATA["dd"]?.let {
            return Result.CallBackSuccess(it)
        }
        return Result.CallBackError(Exception("Could not find task"))
    }

    private var TASKS_SERVICE_DATA: LinkedHashMap<String, User> = LinkedHashMap()

}