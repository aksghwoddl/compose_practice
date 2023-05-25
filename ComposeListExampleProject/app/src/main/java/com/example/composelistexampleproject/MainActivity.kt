package com.example.composelistexampleproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelistexampleproject.ui.state.Message
import com.example.composelistexampleproject.ui.theme.ComposeListExampleProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeListExampleProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val messages = arrayListOf<Message>()
                    for (index in 0..100){
                        val sample = Message("Android" , "sample$index  가나다라마바사아자차카타파하파타카차자아사바마라다나가나다라마바사아자차카타파하파타카차자아")
                        messages.add(sample)
                    }
                    MakeMessageCardList(messages)
                }
            }
        }
    }
}

@Composable
fun MessageCard(message : Message) {
    Row(
        modifier = Modifier.padding(all = 10.dp) ,
        verticalAlignment = Alignment.CenterVertically
    ) {
       Image(
           painter = painterResource(id = R.drawable.ic_launcher_foreground),
           contentDescription = "" ,
           modifier = Modifier
               .size(40.dp)
               .clip(CircleShape)
               .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
       )

        var isExpandable by remember{ mutableStateOf(false) }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally ,
            modifier = Modifier.clickable {
                isExpandable = !isExpandable
            }.width(300.dp)
        ) {
            Text(
                text = message.author ,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = message.body ,
                fontSize = 10.sp ,
                maxLines = if(isExpandable) Int.MAX_VALUE else 1
            )
        }
    }
}

@Composable
fun MakeMessageCardList(messages : List<Message>) {
    LazyColumn{
       items(messages){ message ->
           MessageCard(message)
       }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeListExampleProjectTheme {
        Surface {
            val messages = arrayListOf<Message>()
            for (index in 0..100){
                val sample = Message("Android" , "sample$index 가나다라마바사아자차카타파하파타카차자아사바마라다나가나다라마바사아자차카타파하파타카차자아")
                messages.add(sample)
            }
            MakeMessageCardList(messages)
        }
    }
}