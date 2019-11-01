/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dongeulcomapny.com.myandroidarchitecture.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dongeulcomapny.com.myandroidarchitecture.repository.DataRepository
import dongeulcomapny.com.myandroidarchitecture.viewModel.RoomFragmentViewModel
import dongeulcomapny.com.myandroidarchitecture.viewModel.UserFragmentViewModel
import java.lang.IllegalArgumentException

/**
 * Factory for all ViewModels.
 */

class ViewModelFactory constructor(
    private val repository: DataRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when{
                isAssignableFrom(UserFragmentViewModel::class.java) ->
                    UserFragmentViewModel(repository)
                isAssignableFrom(RoomFragmentViewModel::class.java)->
                    RoomFragmentViewModel(repository)
                else->
                    throw IllegalArgumentException("Unknown Viewmodel Class")
            }
        } as T

}
