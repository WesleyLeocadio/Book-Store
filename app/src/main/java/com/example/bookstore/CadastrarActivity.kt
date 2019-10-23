package com.example.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.bookstore.ConnectionBD.AppDatabase
import com.example.bookstore.domain.Book
import kotlinx.android.synthetic.main.activity_cadastrar.*

class CadastrarActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bd-wesley")
            .allowMainThreadQueries()
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)


        btnSalvar.setOnClickListener {
            db.bookDao().insert(Book(textName.text.toString(), textAuthor.text.toString(), textAno.text.toString().toInt(),
                ratingBar.rating,R.drawable.livrofechado))
            Log.i("cadastro"," Livro Salvo"+"["+
                    Book(textName.text.toString(), textAuthor.text.toString(), textAno.text.toString().toInt(),
                        ratingBar.rating,R.drawable.livrofechado) +"]")
        }

        btnCancelar.setOnClickListener{
            finish()
        }
    }
}
