package com.example.composearchitectureexample.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composearchitectureexample.ui.list.ListActivity
import com.example.composearchitectureexample.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    private fun MainApp(context : Context , mainViewModel: MainViewModel = viewModel()) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max) ,
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.Center
        ) {
            InputUserField(mainViewModel)
            Spacer(modifier = Modifier.height(30.dp))
            Spacer(Modifier.height(20.dp))
            ComposeButton("저장"){
                mainViewModel.insertUser()
                Toast.makeText(context , "성공적으로 등록했습니다!" , Toast.LENGTH_LONG).show()
            }
            ComposeButton("목록"){
                startListActivity()
            }
        }
    }

    /**
     * TextField를 컴포즈하는 함수
     * **/
    @Composable
    private fun InputUserField(mainViewModel: MainViewModel) {
            val nameField : String by mainViewModel.nameFiledState
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "이름")
                    Spacer(modifier = Modifier.width(10.dp))
                    BasicTextField(
                        value = nameField ,
                        onValueChange = {mainViewModel.setNameFieldState(it) },
                        Modifier
                            .width(250.dp)
                            .border(1.dp, Color.Black)
                            .height(50.dp) ,
                        textStyle = TextStyle.Default.copy(fontSize = 25.sp) ,
                        singleLine = true
                    )
                }
                val ageField : String by mainViewModel.ageFieldState
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = "나이")
                        Spacer(modifier = Modifier.width(10.dp))
                        BasicTextField(
                            value = ageField ,
                            onValueChange = {mainViewModel.setAgeFieldState(it) },
                            Modifier
                                .width(250.dp)
                                .border(1.dp, Color.Black)
                                .height(50.dp) ,
                            textStyle = TextStyle.Default.copy(fontSize = 25.sp) ,
                            singleLine = true ,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
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

    /**
     * 목록화면을 실행하는 함수
     * **/
    private fun startListActivity() {
        with(Intent(this@MainActivity , ListActivity::class.java)){
            startActivity(this)
            finish()
        }
    }
}