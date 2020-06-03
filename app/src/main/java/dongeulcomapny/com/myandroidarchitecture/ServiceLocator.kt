package dongeulcomapny.com.myandroidarchitecture

import android.content.Context
import androidx.room.Room
import dongeulcomapny.com.myandroidarchitecture.data.TaskDataSource
import dongeulcomapny.com.myandroidarchitecture.data.source.FakeDataRemoteDataSource
import dongeulcomapny.com.myandroidarchitecture.data.source.TaskLocalDataSource
import dongeulcomapny.com.myandroidarchitecture.database.UserDataBase
import dongeulcomapny.com.myandroidarchitecture.repository.DataRepository
import dongeulcomapny.com.myandroidarchitecture.repository.DataRepositoryImpl

object ServiceLocator {

     var database : UserDataBase? =null
    @Volatile
    var dataRepository : DataRepository ?= null

     fun provideTasksRepository(context: Context):DataRepository{
        synchronized(this){
            return dataRepository?: createDataRepository(context)
        }
    }

    private fun createDataRepository(context: Context): DataRepository {
        return DataRepositoryImpl(FakeDataRemoteDataSource, createDataLocalDataSource(context))

    }

    private fun createDataLocalDataSource(context: Context) :TaskDataSource {

        val database = database ?:createDataBase(context)
        return TaskLocalDataSource(database.taskDao())

    }

    private fun createDataBase(context: Context): UserDataBase {

        val result = Room.databaseBuilder(context.applicationContext,UserDataBase::class.java, "Tasks.db").build()
        database = result
        return result
    }
}