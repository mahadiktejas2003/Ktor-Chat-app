package com.tejaa.ktorChatAppTejaa.data.remote

import com.tejaa.ktorChatAppTejaa.data.remote.MessageService.Endpoints.GetAllMessages.url
import com.tejaa.ktorChatAppTejaa.data.remote.dto.MessageDto
import com.tejaa.ktorChatAppTejaa.domain.model.Message
import com.tejaa.ktorChatAppTejaa.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
//class to implement the interface ChatSocketService

class ChatSocketServiceImplementation (

    private val client: HttpClient                 //An argument  to initatiate that Websocket Connection
    ): ChatSocketService{

        private var socket: WebSocketSession?=null

    override suspend fun initSession(username: String): Resource <Unit> {
        return try {
            socket= client.webSocketSession {
                url (  "${ChatSocketService. Endpoints.ChatSocket.url}?username=$username")//which url ? ->Defined it in the MessageService.
            }
            if(socket?.isActive==true){
                Resource.Success(Unit)//return the resouce that succcess , passing nothing as the data

            } else Resource.Error("Couldn't establish connection.")
        } catch(e: Exception) {
            e.printStackTrace()
            Resource. Error ( e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun sendMessage(message: String) {


        try {

            socket?.send(Frame.Text(message))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun observeMessages(): Flow<Message> {
     //this will return a Flow of messages-we should trigger every single time
        //we get a message here on this Client
        //so again Retrun try catch black

        return try{
            //in here-we wanna get the messages and convert them to a Flow
            // We want Flow as type Messages
            //so werite this -receiveAsflow->it gives us a flow of type Frames
            //see using hovering cursor on these methods.
            socket?.incoming
            ?.receiveAsFlow()
                ?.filter{it is Frame.Text}  //rest is dropped
                ?.map{
                    val json= (it as? Frame.Text)?.readText() ?: ""
                    //Convert this json string to such a message object
                    val messageDto= Json.decodeFromString<MessageDto>(json)
                    messageDto.toMessage()

                }?:flow{ }

        }
        catch(e:Exception){
            e.printStackTrace()
            flow{ }

        }
    }

    override suspend fun closeSession() {

        //close the session-
        socket?.close()

    }
}