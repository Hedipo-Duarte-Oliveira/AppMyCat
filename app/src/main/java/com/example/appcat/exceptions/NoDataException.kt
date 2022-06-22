package com.example.appcat.exceptions

import java.lang.Exception

class NoDataException : Exception() {
    override val message: String?
        get() = "You request specific single item that we don\\'t have at this"
}
