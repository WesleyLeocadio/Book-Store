package com.example.bookstore.Dao

import androidx.room.*
import com.example.bookstore.domain.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun listAll(): List<Book>

    @Query("SELECT * FROM book WHERE id = :id")
    fun findById(id: Long): Book

    @Query("SELECT * FROM book WHERE name = :name")
    fun findByName (name: String) : Book

    @Query("DELETE FROM book")
    fun deleteAll(): Int

    @Insert
    fun inserirAll(vararg l: Book): LongArray


    @Insert
    fun insert(book: Book): Long

    @Delete
    fun deletar(book: Book): Int

    @Update
    fun atualizar(book: Book): Int

}