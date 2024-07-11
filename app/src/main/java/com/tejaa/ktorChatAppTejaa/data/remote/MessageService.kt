package com.tejaa.ktorChatAppTejaa.data.remote

import com.tejaa.ktorChatAppTejaa.domain.model.Message

interface MessageService {

    suspend fun getAllMessages() : List<Message>

    companion object{

        const val BASE_URL= "http://192.168.239.172:8080"  //bcz-8080 port is chosen in ktor
   }

    sealed class Endpoints(val url: String){//Define Endpoints in it.
        object GetAllMessages : Endpoints("$BASE_URL/messages")

    }
}

//Now create implememtation of this interface - to tell how we wanna make this API request.
//so create the MessageServiceImplementation class-> in the remote pkg