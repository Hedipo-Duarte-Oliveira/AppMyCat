package com.example.appcat.exceptions

import java.lang.Exception

class ForbiddenException : Exception() {
    override val message: String?
        get() = "Your API key does\\'t have enouhh privileges to acess this resource"
}
