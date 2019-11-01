package dongeulcomapny.com.myandroidarchitecture.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dongeulcomapny.com.myandroidarchitecture.data.User
import org.jetbrains.annotations.NotNull

@Dao
interface TaskDao {

    @Query("SELECT * FROM Users")
    suspend fun getUser():List<User>

    @Query("SELECT * FROM Users WHERE userNo = :userNumber")
    suspend fun getUser(userNumber :Long):User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:User)
}