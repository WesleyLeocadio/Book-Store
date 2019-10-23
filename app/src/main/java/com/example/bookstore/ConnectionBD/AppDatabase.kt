package com.example.bookstore.ConnectionBD

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookstore.Dao.BookDao
import com.example.bookstore.domain.Book

@Database (entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao



}