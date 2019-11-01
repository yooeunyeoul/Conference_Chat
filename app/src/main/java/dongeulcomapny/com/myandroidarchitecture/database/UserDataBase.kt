package dongeulcomapny.com.myandroidarchitecture.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dongeulcomapny.com.myandroidarchitecture.data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

}