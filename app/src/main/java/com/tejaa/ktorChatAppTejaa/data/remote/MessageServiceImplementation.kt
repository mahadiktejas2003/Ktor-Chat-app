package com.tejaa.ktorChatAppTejaa.data.remote

import com.tejaa.ktorChatAppTejaa.data.remote.dto.MessageDto
import com.tejaa.ktorChatAppTejaa.domain.model.Message
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class MessageServiceImplementation (
//
    //here make sure that we have access to our API which will be of tpe HTTTP Client
    //we can use it to make Network calls
    private val client: HttpClient
): MessageService {
        override suspend fun getAllMessages(): List<Message> {

            //errorr handling so that app don't crash.
            return try{
                    //use our Cient - type .get -
                        // -Cuz we wanna make GET request- to get list of messages already in chat.
                client.get<List<MessageDto>>(MessageService.Endpoints.GetAllMessages.url)
                   . map{ it.toMessage() }
            }

            catch(e: Exception) {
                emptyList()
            }

        }
    }