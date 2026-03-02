package com.mahindra.mywishlist

import android.content.Context
import androidx.room.Room
import com.mahindra.mywishlist.Graph.wishRepository
import com.mahindra.mywishlist.data.WishDatabase
import com.mahindra.mywishlist.data.WishRepository
import kotlin.getValue

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy{
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(
            context,
            WishDatabase::class.java,
            "wishlist.db"
        ).build()
    }
}