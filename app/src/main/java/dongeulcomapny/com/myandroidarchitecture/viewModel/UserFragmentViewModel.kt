package dongeulcomapny.com.myandroidarchitecture.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import dongeulcomapny.com.myandroidarchitecture.clickevent.Event
import dongeulcomapny.com.myandroidarchitecture.data.User
import dongeulcomapny.com.myandroidarchitecture.data.network.Result
import dongeulcomapny.com.myandroidarchitecture.repository.DataRepository
import kotlinx.coroutines.launch

class UserFragmentViewModel(private val repository: DataRepository) : ViewModel() {
    private val _openDetailUserProfileEvent = MutableLiveData<Event<Long>>()

    val openDetailUserProfileEvent : LiveData<Event<Long>>
        get() = _openDetailUserProfileEvent

    private val _items = MutableLiveData<List<User>>().apply {
        this.value = emptyList()
    }
    val items :LiveData<List<User>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading : LiveData<Boolean> = _dataLoading

    fun openUserProfileDetail(userNo: Long) {
        _openDetailUserProfileEvent.value = Event(userNo)
    }

    fun loadUsers(forceUpdate: Boolean) {

        _dataLoading.value = true

        viewModelScope.launch {
            val getUsersResult = repository.getUserData(forceUpdate = forceUpdate)
            Logger.d(getUsersResult)
            if (getUsersResult is Result.CallBackSuccess) {
                val users = getUsersResult.data
                Logger.w(users.toString())




            }

        }

    }

    fun insertUser() {
        viewModelScope.launch {
            repository.saveUser()
        }

    }
}