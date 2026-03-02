package com.mahindra.mywishlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahindra.mywishlist.data.Wish
import com.mahindra.mywishlist.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val repository: WishRepository = Graph.wishRepository
): ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChanged(newTitle: String){
        wishTitleState = newTitle
    }

    fun onWishDescriptionChanged(newTitle: String){
        wishDescriptionState = newTitle
    }

    lateinit var getAllWishes: Flow<List<Wish>>

    init{
        viewModelScope.launch(){
            getAllWishes = repository.getWishes()
        }
    }

    fun getAWishById(id: Long): Flow<Wish>{
        return repository.getAWishById(id)
    }

    fun addAWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            repository.addAwish(wish)
        }
    }

    fun deleteAWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAwish(wish)
        }
    }

    fun updateAWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateAWish(wish)
        }
    }
}