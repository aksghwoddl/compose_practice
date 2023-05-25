package com.example.composeexampleproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeexampleproject.ui.state.Message
import com.example.composeexampleproject.ui.theme.ComposeExampleProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val message = Message("Android" , "Compose")
                    MessageCard(message)
                }
            }
        }
    }
}

@Composable
fun MessageCard(message : Message) {
    Row(
        modifier = Modifier.padding(all = 8.dp) ,
        verticalAlignment = Alignment.CenterVertically ,

    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Sample Image" ,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message.author ,
                color = MaterialTheme.colorScheme.secondary ,
                style = MaterialTheme.typography.titleSmall ,
                modifier = Modifier.padding(all = 4.dp) ,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.extraSmall , shadowElevation = 1.dp) {
                Text(
                    text = message.body ,
                    color = MaterialTheme.colorScheme.secondary ,
                    style = MaterialTheme.typography.titleSmall ,
                    modifier = Modifier.padding(all = 4.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MessageCardPreview() {
    ComposeExampleProjectTheme {
        Surface {
            val message = Message("Android" , "Compose")
            MessageCard(message)
        }
    }
}