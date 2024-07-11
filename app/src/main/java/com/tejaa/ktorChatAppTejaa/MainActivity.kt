package com.tejaa.ktorChatAppTejaa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tejaa.ktorChatAppTejaa.presentation.chat.ChatScreen
import com.tejaa.ktorChatAppTejaa.presentation.username.UsernameScreen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.internal.GeneratedComponent
import androidx.lifecycle.SavedStateHandle
@AndroidEntryPoint
class MainActivity :  ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "username_screen"
            ) {
                composable("username_screen") {
                    UsernameScreen(onNavigate = navController::navigate)
                }
                composable(
                    route = "chat_screen/{username}",
                    arguments = listOf(
                        navArgument(name = "username") {
                            type = NavType.StringType
                            nullable = true
                        }
                    )
                ) {
                    val username = it.arguments?.getString("username")
                    ChatScreen(username = username)
                }
            }
        }
    }
}