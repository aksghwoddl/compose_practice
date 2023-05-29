package com.example.composearchitectureexample.ui.main

import android.content.Context
import android.icu.text.ListFormatter.Width
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val context = LocalContext.current
                MainApp(context = context)
            }
        }
    }

    /**
     * MainActivity 전체를 컴포즈 하는 함수
     * **/
    @Composable
    private fun MainApp(context : Context) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max) ,
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.Center
        ) {
            InputUserTextField()
            Spacer(Modifier.height(20.dp))
            ComposeButton("저장"){
                Toast.makeText(context , "버튼이 클릭됐습니다!" , Toast.LENGTH_LONG).show()
            }
            ComposeButton("목록"){
                Toast.makeText(context , "버튼이 클릭됐습니다!" , Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * 저장 컴포넌트들을 Compose하는 함수
     * **/
    @Composable
    private fun InputUserTextField() {
        Column {
            val (text , setText) = remember{ mutableStateOf("") }

            BasicTextField(
                value = text ,
                onValueChange = setText ,
                Modifier
                    .width(250.dp)
                    .border(1.dp, Color.Black)
                    .height(50.dp) ,
                textStyle = TextStyle.Default.copy(fontSize = 25.sp) ,
                singleLine = true
            )
        }
    }

    /**
     * 버튼을 컴포즈하는 함수
     * **/
    @Composable
    private fun ComposeButton(text : String , onListButtonClick : () -> Unit) {
        Button(onClick = onListButtonClick){
            Text(
                text = text ,
                fontSize = 15.sp
            )
        }
    }

    @Preview
    @Composable
    private fun PreviewMainActivity() {
        Surface(
            modifier = Modifier.fillMaxSize() ,
            color = MaterialTheme.colorScheme.background ,

        ) {
            val context = LocalContext.current
            MainApp(context = context)
        }
    }
}