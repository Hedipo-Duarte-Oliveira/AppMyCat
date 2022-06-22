package com.example.appcat.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appcat.exceptions.BadRequestException
import com.example.appcat.exceptions.ForbiddenException
import com.example.appcat.exceptions.LimitsRequestException
import com.example.appcat.exceptions.NoDataException
import com.example.appcat.exceptions.ResultWrapper
import com.example.appcat.exceptions.UnauthorizedException
import com.example.appcat.model.Cat
import com.example.appcat.usecase.UseCaseCats
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.IllegalArgumentException

class CatListViewModel(private val useCaseCats: UseCaseCats) : ViewModel() {

    private val _listcats = MutableLiveData<ResultWrapper<List<Cat>>>()
    val listcats: LiveData<ResultWrapper<List<Cat>>> get() = _listcats

    private val _listCatAdapter = MutableLiveData<List<Cat>>()
    val listCatAdapter: LiveData<List<Cat>> get() = _listCatAdapter

    private val _errorMsg = MutableLiveData<ResultWrapper<String>>()
    val errrMsg: LiveData<ResultWrapper<String>> get() = _errorMsg

    fun requestApiCat() {
        try {
            viewModelScope.launch {
                _listcats.postValue(ResultWrapper.Sucess(useCaseCats.getListCat()))
                _listCatAdapter.postValue(useCaseCats.getListCat())
            }
        } catch (http: HttpException) {
            when {
                http.code() == 400 -> {
                    _errorMsg.value = ResultWrapper.Error(BadRequestException(), http.code())
                }
                http.code() == 401 -> {
                    _errorMsg.value = ResultWrapper.Error(UnauthorizedException(), http.code())
                }
                http.code() == 403 -> {
                    _errorMsg.value = ResultWrapper.Error(ForbiddenException(), http.code())
                }
                http.code() == 429 -> {
                    _errorMsg.value = ResultWrapper.Error(LimitsRequestException(), http.code())
                }
                http.code() == 550 -> {
                    _errorMsg.value = ResultWrapper.Error(NoDataException(), http.code())
                }
            }
        }
    }
    class CatListViewModelFactory(
        private val useCaseCats: UseCaseCats
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(CatListViewModel::class.java)) {
                CatListViewModel(this.useCaseCats) as T
            } else {
                throw IllegalArgumentException("ViewModel not found")
            }
        }
    }
}
