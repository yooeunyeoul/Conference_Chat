package dongeulcomapny.com.myandroidarchitecture.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import dongeulcomapny.com.myandroidarchitecture.data.User

object DIffCallback {

    class UsersDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }
}