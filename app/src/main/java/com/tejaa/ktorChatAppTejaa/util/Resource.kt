package com.tejaa.ktorChatAppTejaa.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}

//We're goonna need this class in anoher interface we'll create
// in our Remote pkg - what is called  ChatSocketService
