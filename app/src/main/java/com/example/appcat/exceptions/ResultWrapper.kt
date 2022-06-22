package com.example.appcat.exceptions

import com.example.appcat.model.Cat
import java.lang.Exception

sealed class ResultWrapper<out T : Any> {
    object Loading : ResultWrapper<Nothing>()
    class Sucess<out T : Any>(val listcat: List<Cat>?) : ResultWrapper<T>()
    class Error(val exception: Exception, val code: Int = 0) : ResultWrapper<Nothing>()
}
