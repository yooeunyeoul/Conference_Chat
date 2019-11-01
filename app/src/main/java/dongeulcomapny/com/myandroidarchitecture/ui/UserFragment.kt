package dongeulcomapny.com.myandroidarchitecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dongeulcomapny.com.myandroidarchitecture.R
import dongeulcomapny.com.myandroidarchitecture.adapter.UserAdapter
import dongeulcomapny.com.myandroidarchitecture.databinding.FragmentUserBinding
import dongeulcomapny.com.myandroidarchitecture.util.getViewModelFactory
import dongeulcomapny.com.myandroidarchitecture.viewModel.UserFragmentViewModel

class UserFragment : Fragment() {

    private lateinit var listAdapter: UserAdapter
    private lateinit var viewDataBinding: FragmentUserBinding

    private val viewModel by viewModels<UserFragmentViewModel> {
        getViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        viewDataBinding = FragmentUserBinding.bind(view).apply {
            viewmodel = viewModel
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        viewModel.loadUsers(true)
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = UserAdapter(viewModel)
            viewDataBinding.recyclerUser.adapter = listAdapter
        }
    }

    private fun setupUserProfileDetailNavigation(){
        viewModel.openDetailUserProfileEvent.observe(this@UserFragment, Observer {
            it.getContentIfNotHandled()?.let {userNo->
                openUserProfileDetails(userNo)
            }
        })
    }

    private fun openUserProfileDetails(userNo: Long) {

    }

}