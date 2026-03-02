package com.mahindra.mywishlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title: String = "",
    @ColumnInfo(name = "wish-description")
    val description: String = ""
)

object DummyWish{
    val wishList = listOf(
        Wish(1,"Apple Watch", "IOS Apple Watch"),
        Wish(2,"Apple Watch2", "IOS Apple Watch2"),
        Wish(3,"Apple Watch3", "IOS Apple Watch3"),
        Wish(4,"Apple Watch4", "IOS Apple Watch4")
    )
}
