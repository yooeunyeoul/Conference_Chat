package dongeulcomapny.com.myandroidarchitecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dongeulcomapny.com.myandroidarchitecture.R
import dongeulcomapny.com.myandroidarchitecture.databinding.Fragment1Binding

class UserFragment : Fragment() {

    private lateinit var viewDataBinding: Fragment1Binding

//    private val viewModel by viewModels<UserFragmentViewModel> {
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment1, container, false)
        viewDataBinding = Fragment1Binding.bind(view).apply {
//            viewmodel =
        }


        return super.onCreateView(inflater, container, savedInstanceState)
    }

}