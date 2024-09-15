package com.example.genzchats.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genzchats.ChatViewModel
import com.example.genzchats.MessageModel
import com.example.genzchats.ui.theme.ModelMessage
import com.example.genzchats.ui.theme.UserMessage

@Composable
fun ChatPage(modifier: Modifier = Modifier
    .padding(bottom = 20.dp)
    , viewModel: ChatViewModel) {

    Column(modifier = Modifier.fillMaxSize()) {

        topbar()
        MessageList(modifier = Modifier.weight(1f),messageList =  viewModel.messageList)
        TextInput(onMessageSend = {
            viewModel.sendMessage(it)

        })
        Spacer(modifier = Modifier.height(20.dp))
    }
    

}

@Composable
fun topbar(modifier: Modifier = Modifier.height(50.dp)) {

    Box(modifier= Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(UserMessage),
        contentAlignment = Alignment.Center) {

        Text(text = "GenZChats", fontSize = 20.sp)

    }
    
}

@Composable
fun TextInput(modifier: Modifier = Modifier, onMessageSend : (String) -> Unit) {

    var message by remember {
        mutableStateOf("")
    }
    val keyboardController= LocalSoftwareKeyboardController.current
    val focusManager= LocalFocusManager.current
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {


        OutlinedTextField(value = message, onValueChange = {message =it},
            label = { Text(text = "Type a message") },
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),


            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onSend = {
                    onMessageSend(message)
                    message=""
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }

            )
        )

        IconButton(onClick = {
            if(message.isNotEmpty()){onMessageSend(message)
                message = ""}

        }) {
            Icon(Icons.Default.Send, contentDescription = "Send",modifier = Modifier
                .width(30.dp)
                .height(30.dp))
            
        }

    }

    
}

@Composable
fun MessageList(modifier: Modifier = Modifier, messageList:List<MessageModel>) {
    LazyColumn(modifier = modifier, reverseLayout = true) {
        items(messageList.reversed()){
            MessageRow(messageModel = it)

        }
    }
}

@Composable
fun MessageRow(messageModel: MessageModel) {
    val isModel= messageModel.role=="model"
    Row(verticalAlignment = Alignment.CenterVertically) {

        Box(modifier = Modifier.fillMaxWidth()){
            Box(modifier = Modifier
                .align(
                    if (isModel) Alignment.BottomStart else Alignment.BottomEnd
                )
                .padding(
                    start = if (isModel) 8.dp else 70.dp,
                    end = if (isModel) 70.dp else 8.dp,
                    top = 8.dp, bottom = 8.dp
                )
                .clip(RoundedCornerShape(20.dp))
                .background(if (isModel) ModelMessage else UserMessage)
                .padding(16.dp)
            ){
                SelectionContainer {

                    Text(text = messageModel.message, fontWeight = FontWeight.W500)
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