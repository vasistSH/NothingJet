package com.vasist.nothingjet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberAsyncImagePainter
import com.vasist.nothingjet.module.UserList
import com.vasist.nothingjet.network.RetrofitHelper
import com.vasist.nothingjet.network.UserListService
import com.vasist.nothingjet.repository.RandomUserRepository
import com.vasist.nothingjet.viewModel.RandomUserViewModel
import com.vasist.nothingjet.viewModel.RandomUserViewModelFactory


class MainActivity : ComponentActivity() {

    private lateinit var randomUserViewModel: RandomUserViewModel
    private lateinit var results: MutableList<UserList>
    private lateinit var repository: RandomUserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userListService = RetrofitHelper.getInstance().create(UserListService::class.java)
        repository = RandomUserRepository(userListService)
        randomUserViewModel = ViewModelProvider(
            this, RandomUserViewModelFactory(repository)
        )[RandomUserViewModel::class.java]
        randomUserViewModel.quotes.observe(this) {
            results = it.results
            setContent {
                UserListScreen(randomUserViewModel)
                /*Themes()*/
                /*reComposableEg()*/
                /*notificationScreen()*/
            }
        }
    }


    @Composable
    fun UserListScreen(randomUserViewModel: RandomUserViewModel) {
        val userListState = randomUserViewModel.quotes
        LaunchedEffect(userListState) {
            randomUserViewModel.quotes
        }
        var selectedUser by remember { mutableStateOf<UserList?>(null) }

        val userList = userListState.value?.results

        LazyColumn {
            items(userList!!) { user ->
                UserListItem(user = user, onItemClick = { selectedUser = it })
            }
        }
    }


    @Composable
    fun UserListItem(user: UserList, onItemClick: (UserList) -> Unit) {

        var showDialog  by remember { mutableStateOf(false) }

        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(2.dp)
                .clickable {
                    showDialog = true
                    onItemClick(user)
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(2.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(user.picture.medium),
                    contentDescription = "User profile picture",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(1.dp)
                        .clip(CircleShape)
                        .border(width = 2.dp, color = Color.Cyan, shape = CircleShape)
                )
                Column(
                    modifier = Modifier
                        .weight(8f)
                        .padding(4.dp)
                ) {
                    Text(
                        text = user.name.title.plus(" ${user.name.first}")
                            .plus(" ${user.name.last}"),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(text = user.phone, fontWeight = FontWeight.Light)
                    Text(text = user.gender, fontWeight = FontWeight.Light)
                }
            }
        }

        if (showDialog) {
            AlertDialogExample(
                user = user,
                showDialog = showDialog,
                onCloseDialog = { showDialog = false }
            )
        }
    }
}