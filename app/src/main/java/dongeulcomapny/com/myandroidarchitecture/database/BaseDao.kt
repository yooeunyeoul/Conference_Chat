package dongeulcomapny.com.myandroidarchitecture.database

import androidx.room.Insert
import java.util.*

interface BaseDao<T> {
    @Insert
    fun insert(vararg objects: T)
}