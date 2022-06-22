package com.example.appcat.exceptions

import java.lang.Exception

class BadRequestException : Exception() {
    override val message: String?
        get() = "There is something wrong with your request"
}
