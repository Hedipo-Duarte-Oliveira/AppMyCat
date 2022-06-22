package com.example.appcat.exceptions

import java.lang.Exception

class LimitsRequestException : Exception() {
    override val message: String?
        get() = "You have exceeded yor API key rate limits"
}
