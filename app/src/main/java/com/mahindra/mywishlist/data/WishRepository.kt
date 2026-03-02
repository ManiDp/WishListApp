package com.mahindra.mywishlist.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {
    suspend fun addAwish(wish: Wish){
        wishDao.addAwish(wish)
    }

    fun getWishes() : Flow<List<Wish>> = wishDao.getAllWishes()


    fun getAWishById(id: Long) : Flow<Wish>  {
        return wishDao.getAWishByID(id)
    }

    suspend fun updateAWish(wish: Wish){
        wishDao.updateAWish(wish)
    }

    suspend fun deleteAwish(wish: Wish){
        wishDao.deleteAWish(wish)
    }
}