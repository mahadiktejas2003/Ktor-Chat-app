package com.tejaa.ktorChatAppTejaa.data.remote

import com.tejaa.ktorChatAppTejaa.domain.model.Message
import com.tejaa.ktorChatAppTejaa.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(
        username: String
    ): Resource<Unit>


    suspend fun sendMessage(message: String)



    fun observeMessages(): Flow<Message>

    suspend fun closeSession()


    companion object{
        //local deployement:
        //const val BASE_URL= "ws://192.168.38.172:8080"  //bcz-8080 port is chosen in ktor
        const val BASE_URL = "ws://18.234.132.137:8080"  //aws server


    }

    sealed class Endpoints(val url: String){//Define Endpoints in it.
    object ChatSocket: Endpoints("$BASE_URL/chat-socket")//user baser url=BASE_URL

    }

}

// implementation  class0  "ChatSocketServiceImplementation"