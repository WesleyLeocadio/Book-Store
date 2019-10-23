package com.example.bookstore.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book (var name:String, var author:String, var year:Int, var note :Float, var img: Int){

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

    var bitten: Boolean = false

}