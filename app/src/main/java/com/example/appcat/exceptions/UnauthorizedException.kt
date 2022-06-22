package com.example.appcat.exceptions

import java.lang.Exception

class UnauthorizedException : Exception() {
    override val message: String?
        get() = "Your API key is wrong"
}
