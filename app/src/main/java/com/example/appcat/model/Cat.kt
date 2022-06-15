package com.example.appcat.model

import com.example.appcat.utils.Constants
import java.io.Serializable

data class Cat(

    val height: Int,
    val width: Int,
    val url: String,
    val reference_image_id: String?,
    val name: String,
    val description: String

) : Serializable {

    fun getImage(): String {
        return getImageUrl()
    }
    private fun getImageUrl(): String {
        return Constants.BASE_IMG_URL.plus("$reference_image_id.jpg")
    }
}
