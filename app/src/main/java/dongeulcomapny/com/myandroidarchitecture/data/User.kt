package dongeulcomapny.com.myandroidarchitecture.data

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import dongeulcomapny.com.myandroidarchitecture.R

@Entity(tableName = "Users")
data class User constructor(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "userNo")  var userNo: Long=0,
    @ColumnInfo(name = "userId") var userId: String = "",
    @ColumnInfo(name = "nickNm") var userName: String = "",
    @ColumnInfo(name = "thumb") var thumb: String = "",
    @ColumnInfo(name = "bgThumb") var bgThumb: String = "",
    @ColumnInfo(name = "desc") var desc: String = "",
    @ColumnInfo(name = "customNm") var customNm: String = "",
    @ColumnInfo(name = "favoritesTime") var favoritesTime: Long = 0
) {
    val customName: String
        get() = if (customName.isNotEmpty()) customName else userName

    companion object {
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context).load(imageUrl).placeholder(R.drawable.btn_pro_01)
                .transition(DrawableTransitionOptions().crossFade())
                .apply(RequestOptions().circleCrop()).into(view)

        }
    }

}