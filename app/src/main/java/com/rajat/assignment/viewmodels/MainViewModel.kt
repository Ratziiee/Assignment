package com.rajat.assignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajat.assignment.di.NetworkRepository
import com.rajat.assignment.models.Posts
import com.rajat.assignment.utils.DataHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val networkRepository: NetworkRepository)  : ViewModel() {

    private val _posts = MutableLiveData<DataHandler<Posts>>()
    val posts : LiveData<DataHandler<Posts>> = _posts

    fun getPosts() {
        _posts.postValue(DataHandler.LOADING())
        viewModelScope.launch {
            val response = networkRepository.getPosts()
            _posts.postValue(handleResponse(response))
        }

    }

    private fun handleResponse(response: Response<Posts>) : DataHandler<Posts> {
        if(response.isSuccessful) {
            response.body()?.let {
                return DataHandler.SUCCESS(it)
            }
        }

        return DataHandler.ERROR(message = response.errorBody().toString())
    }
}