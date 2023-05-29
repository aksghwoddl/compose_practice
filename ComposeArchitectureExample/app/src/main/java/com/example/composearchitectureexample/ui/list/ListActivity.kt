package com.example.composearchitectureexample.ui.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composearchitectureexample.common.UiState
import com.example.composearchitectureexample.data.local.entity.UserEntity
import com.example.composearchitectureexample.ui.list.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ListActivity"

@AndroidEntryPoint
class ListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                val context = LocalContext.current
                val listViewModel : ListViewModel = viewModel()
                listViewModel.getAllUser()
                UserList(context , listViewModel)
            }
        }
    }

    /**
     * 유저 목록을 UiState에 따라 그리는 함수
     * **/
    @Composable
    private fun UserList(context : Context, listViewModel : ListViewModel) {
        when(val state = listViewModel.userUiState.collectAsState().value){
            is UiState.Success -> { // 로딩 성공
                val list = state.data
                Log.d(TAG, "UserList: Success!! $list")
                if(list.isEmpty()){
                    Toast.makeText(context , "목록이 비어있습니다!" , Toast.LENGTH_LONG).show()
                } else {
                   LazyColumn{
                       items(list.size){
                           UserItemCard(user = list[it])
                       }
                   }
                }
            }

            is UiState.Failure -> { // 로딩 실패
                Toast.makeText(context , state.errorMessage , Toast.LENGTH_LONG).show()
            }

            is UiState.Loading -> { // 로딩중일때 (실제에서는 프로그레스바 같은 것을 사용)
                if(state.isLoading){
                    Log.d(TAG, "UserList: Loading.....")
                } else {
                    Log.d(TAG, "UserList: Finish......")
                }
            }
        }
    }

    /**
     * 유저 정보를 보여주는 카드를 Compose하는 함수
     * **/
    @Composable
    private fun UserItemCard(user : UserEntity) {
        Row(
            modifier = Modifier.padding(all = 10.dp)
        ) {
            Text(
                text = "이름 : ${user.name} " ,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "나이 : ${user.age}")
        }
    }

    @Composable
    @Preview
    private fun PreviewListActivity() {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val context = LocalContext.current
            UserList(context = context , listViewModel = viewModel())
        }
    }

    @Composable
    @Preview
    private fun PreviewUserItem() {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
        ) {
            UserItemCard(user = UserEntity(null , "test" , 25))
        }
    }
}