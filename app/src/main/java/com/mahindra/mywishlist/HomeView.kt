package com.mahindra.mywishlist

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mahindra.mywishlist.data.DummyWish
import com.mahindra.mywishlist.data.Wish

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
){
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBarView(title = "WishList") {
                Toast.makeText(context, "Back Button Clicked", Toast.LENGTH_SHORT).show()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(20.dp),
                contentColor = Color.White,
                backgroundColor = Color.Gray,
                onClick = {
                    Toast.makeText(context, "Add Button Clicked", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.AddScreen.route + "/0L")
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ){
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())

        LazyColumn(Modifier
            .fillMaxSize()
            .padding(it)){
            items(wishList.value, key = {wish -> wish.id}){
                wish ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                            viewModel.deleteAWish(wish)
                            true
                        } else false
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            if(dismissState.dismissDirection == DismissDirection.EndToStart) Color.Red else Color.Transparent,
                            label = ""
                        )
                        val alignment = Alignment.CenterEnd

                        Box(
                            Modifier.fillMaxSize().padding(horizontal = 20.dp).background(color),
                            contentAlignment = alignment
                        ){
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White
                            )
                        }
                    },
                    directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(0.25f) },
                    dismissContent = {
                        WishItem(wish = wish,{
                            navController.navigate(Screen.AddScreen.route + "/${wish.id }")
                        })
                    }
                )
            }
        }
    }
}


@Composable
fun WishItem(wish: Wish, onClick: () -> Unit){
    Card(
        Modifier.fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() },
        elevation = 10.dp,
        backgroundColor = Color.White
    ){
        Column {
            Text(text = wish.title, fontWeight = FontWeight.Bold)
            Text(text = wish.description)
        }
    }
}
