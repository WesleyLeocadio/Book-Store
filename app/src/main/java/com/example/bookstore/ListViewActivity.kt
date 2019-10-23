package com.example.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.bookstore.ConnectionBD.AppDatabase
import com.example.bookstore.domain.Book
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {


    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bd-wesley")
            .allowMainThreadQueries()
            .build()
    }
    var livros:List<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        livros = db.bookDao().listAll()
        listview.adapter = LivrosAdapter(this, livros as List<Book>)
        listview.setOnItemClickListener{adapterView, view, i, l ->
            var frutaSelecionada = livros?.get(i)

    }


    }
}
