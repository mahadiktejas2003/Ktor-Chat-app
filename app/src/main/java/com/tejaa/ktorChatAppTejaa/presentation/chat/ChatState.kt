package com.tejaa.ktorChatAppTejaa.presentation.chat

import com.tejaa.ktorChatAppTejaa.domain.model.Message

data class ChatState(
    //This class combines the States  we need in this screeen,

    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false

)

