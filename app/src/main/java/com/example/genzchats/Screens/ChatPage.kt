//package com.example.genzchats.Screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.ui.graphics.ShaderBrush
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.text.selection.SelectionContainer
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Send
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalFocusManager
//import androidx.compose.ui.platform.LocalSoftwareKeyboardController
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.genzchats.ChatViewModel
//import com.example.genzchats.MessageModel
//import com.example.genzchats.ui.theme.ModelMessage
//import com.example.genzchats.ui.theme.UserMessage
//import androidx.compose.material3.Icon
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.res.painterResource
//import com.example.genzchats.R
//import androidx.compose.ui.res.painterResource
//
//import androidx.compose.ui.graphics.Brush
//
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material.icons.filled.AccountCircle
//
//@Composable
//
//fun ChatPage(
//    modifier: Modifier = Modifier
//        .padding(bottom = 10.dp),
//    viewModel: ChatViewModel
//) {
//    Column(modifier = Modifier.fillMaxSize()) {
//        topbar()
//
//        // Box to contain the messages and the text input
//        Box(
//            modifier = Modifier
//                .weight(1f) // The message list takes the remaining space
//                .fillMaxWidth()
//        ) {
//            MessageList(modifier = Modifier.fillMaxSize(), messageList = viewModel.messageList)
//        }
//
//        // Text input at the bottom
//        TextInput(onMessageSend = { message ->
//            viewModel.sendMessage(message)
//        })
//    }
//}
//
//
//
//@Composable
//fun topbar(modifier: Modifier = Modifier.height(56.dp)) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(56.dp)
//            .background(
//                brush = Brush.horizontalGradient( // Adding a gradient background
//                    colors = listOf(
//                        Color(0xFF4893e6),
//                        Color(0xFF8c77c9)
//                    )
//                )
//            ),
//        contentAlignment = Alignment.Center // Aligns content to the center
//    ) {
//        // Title in the middle
//        Text(
//            text = "GenZChats",
//            fontSize = 22.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.White,
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//}
//
//
//@Composable
//fun TextInput(
//    modifier: Modifier = Modifier,
//    onMessageSend: (String) -> Unit
//) {
//    var message by remember { mutableStateOf("") }
//    val keyboardController = LocalSoftwareKeyboardController.current
//    val focusManager = LocalFocusManager.current
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp), // Padding applied outside the TextField
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        OutlinedTextField(
//            value = message,
//            onValueChange = { message = it },
//            label = { Text(text = "Type a message") },
//            modifier = Modifier
//                .weight(1f) // Ensures the TextField takes up available space
//                .clip(RoundedCornerShape(12.dp)), // Rounded corners applied properly
//            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
//            keyboardActions = KeyboardActions(
//                onSend = {
//                    if (message.isNotEmpty()) {
//                        onMessageSend(message)
//                        message = ""
//                        keyboardController?.hide()
//                        focusManager.clearFocus()
//                    }
//                }
//            ),
//            shape = RoundedCornerShape(12.dp), // Ensure proper corner rounding
//            singleLine = true // Ensure the text field stays single-line
//        )
//
//        IconButton(
//            onClick = {
//                if (message.isNotEmpty()) {
//                    onMessageSend(message)
//                    message = ""
//                    keyboardController?.hide()
//                    focusManager.clearFocus()
//                }
//            }
//        ) {
//            Icon(
//                Icons.Default.Send,
//                contentDescription = "Send",
//                tint = MaterialTheme.colorScheme.primary,
//                modifier = Modifier
//                    .size(36.dp)
//            )
//        }
//    }
//}
//
////import androidx.compose.material.icons.filled.ChatBubbleOutline
//
//
//@Composable
//fun MessageList(modifier: Modifier = Modifier, messageList: List<MessageModel>) {
//    if (messageList.isEmpty()) {
//        // Display the placeholder UI when no chats are available
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Image(painter = painterResource(id =R.drawable.gemini ),
//                contentDescription ="no chats",
//                modifier = Modifier
//                    .size(200.dp)
//                    .padding(bottom = 16.dp)
//                    .alpha(0.7f),
//            )
////            Icon(
////                imageVector = Icons.Default.Home,
////                contentDescription = "No chats",
////                modifier = Modifier
////                    .size(64.dp)
////                    .padding(bottom = 16.dp),
////                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
////            )
//            Text(
//                text = "No chats yet",
//                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
//                fontSize = 20.sp,
//
//            )
//        }
//    } else {
//        // Display the list of messages if there are chats
//        LazyColumn(
//            modifier = modifier
//                .fillMaxSize()
//                .padding(horizontal = 12.dp, vertical = 8.dp),
//            reverseLayout = true
//        ) {
//            items(messageList.reversed()) { messageModel ->
//                MessageRow(messageModel = messageModel)
//            }
//        }
//    }
//}
//
//
//@Composable
//fun MessageRow(messageModel: MessageModel) {
//    val isModel = messageModel.role == "model"
//
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp)
//    ) {
//        Box(modifier = Modifier.fillMaxWidth()) {
//            // Align the message bubble to start or end depending on who sent it.
//            Box(
//                modifier = Modifier
//                    .align(if (isModel) Alignment.CenterStart else Alignment.CenterEnd) // Correct usage here
//                    .padding(horizontal = 16.dp)
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(if (isModel) ModelMessage else UserMessage)
//                    .padding(12.dp)
//            ) {
//                SelectionContainer {
//                    Text(
//                        text = messageModel.message,
//                        fontWeight = FontWeight.Medium,
//                        fontSize = 16.sp,
//                        color = Color.White
//                    )
//                }
//            }
//        }
//    }
//}
//
//
//
//
//
//@Preview(showBackground = true)
//@Composable
//private fun ChatPagePrev() {
//    ChatPage(viewModel = ChatViewModel())
//}
package com.example.genzchats.Screens

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.ShaderBrush

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genzchats.ChatViewModel
import com.example.genzchats.MessageModel
import com.example.genzchats.ui.theme.ModelMessage
import com.example.genzchats.ui.theme.UserMessage
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import com.example.genzchats.R
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.graphics.Brush

import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.AccountCircle

@Composable
fun ChatPage(
    modifier: Modifier = Modifier
        .padding(bottom = 10.dp),
    viewModel: ChatViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        topbar()

        // Box to contain the messages and the text input
        Box(
            modifier = Modifier
                .weight(1f) // The message list takes the remaining space
                .fillMaxWidth()
        ) {
            MessageList(modifier = Modifier.fillMaxSize(), messageList = viewModel.messageList)
        }

        // Text input at the bottom
        TextInput(onMessageSend = { message ->
            viewModel.sendMessage(message)
        })
    }
}

@Composable
fun topbar(modifier: Modifier = Modifier.height(56.dp)) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                brush = Brush.horizontalGradient( // Adding a gradient background
                    colors = listOf(
                        Color(0xFF4893e6),
                        Color(0xFF8c77c9)
                    )
                )
            ),
        contentAlignment = Alignment.Center // Aligns content to the center
    ) {
        // Title in the middle
        Text(
            text = "GenZChats",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    onMessageSend: (String) -> Unit
) {
    var message by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Padding applied outside the TextField
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text(text = "Type a message") },
            modifier = Modifier
                .weight(1f) // Ensures the TextField takes up available space
                .clip(RoundedCornerShape(12.dp)), // Rounded corners applied properly
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(
                onSend = {
                    if (message.isNotEmpty()) {
                        onMessageSend(message)
                        message = ""
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                }
            ),
            shape = RoundedCornerShape(12.dp), // Ensure proper corner rounding
            singleLine = true // Ensure the text field stays single-line
        )

        IconButton(
            onClick = {
                if (message.isNotEmpty()) {
                    onMessageSend(message)
                    message = ""
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            }
        ) {
            Icon(
                Icons.Default.Send,
                contentDescription = "Send",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(36.dp)
            )
        }
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<MessageModel>) {
    if (messageList.isEmpty()) {
        // Display the placeholder UI when no chats are available
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.gemini),
                contentDescription = "no chats",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
                    .alpha(0.7f),
            )
            Text(
                text = "No chats yet",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                fontSize = 20.sp,
            )
        }
    } else {
        // Display the list of messages if there are chats
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 8.dp), // Reduced horizontal padding
            reverseLayout = true
        ) {
            items(messageList.reversed()) { messageModel ->
                MessageRow(messageModel = messageModel)
            }
        }
    }
}

@Composable
fun MessageRow(messageModel: MessageModel) {
    val isModel = messageModel.role == "model"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Align the message bubble to start or end depending on who sent it.
            Box(
                modifier = Modifier
                    .align(if (isModel) Alignment.CenterStart else Alignment.CenterEnd) // Correct usage here
                    .padding(horizontal = 8.dp) // Reduced horizontal padding here
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isModel) ModelMessage else UserMessage)
                    .padding(12.dp)
            ) {
                SelectionContainer {
                    Text(
                        text = messageModel.message,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatPagePrev() {
    ChatPage(viewModel = ChatViewModel())
}
