package dongeulcomapny.com.myandroidarchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dongeulcomapny.com.myandroidarchitecture.R
import dongeulcomapny.com.myandroidarchitecture.data.User
import dongeulcomapny.com.myandroidarchitecture.databinding.ListItemUserBinding
import dongeulcomapny.com.myandroidarchitecture.util.DIffCallback
import dongeulcomapny.com.myandroidarchitecture.viewModel.UserFragmentViewModel

class UserAdapter(private val viewModel: UserFragmentViewModel) :
    ListAdapter<Any, UserAdapter.UserViewHolder>(DIffCallback.UsersDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent = parent)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        if (item is User) {
            holder.bind(viewModel,item)
        }
    }

    class UserViewHolder private constructor(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: UserFragmentViewModel, item: User) {
            binding.viewmodel = viewModel
            binding.user = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_user, parent, false)
                val binding = ListItemUserBinding.bind(view)

                return UserViewHolder(binding)
            }
        }
    }
}