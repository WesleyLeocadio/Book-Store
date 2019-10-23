package com.example.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.bookstore.ConnectionBD.AppDatabase
import com.example.bookstore.domain.Book
import kotlinx.android.synthetic.main.activity_cadastrar.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bd-wesley")
            .allowMainThreadQueries()
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //preparaBanco()
        btnCadastrar.setOnClickListener {
            startActivity(Intent(this, CadastrarActivity::class.java))

        }
        btnListarLivros.setOnClickListener {
            startActivity(Intent(this, ConsultarActivity::class.java))

        }
        btnListView.setOnClickListener {
            startActivity(Intent(this, ListViewActivity::class.java))

        }

    }


    fun preparaBanco(){
        db.bookDao().deleteAll()

        val l1 = Book("Harry Potter e a pedra filosofal", "J. K. Rowling", 200, ratingBar.rating.plus(2) ,R.drawable.livrofechado, true)
        val l2 = Book("Harry Potter e a Câmara Secreta", "J. K. Rowling", 200, ratingBar.rating.plus(2)  ,R.drawable.livrofechado, true)
        val l3 = Book("Harry Potter e o Prisioneiro de Azkaban", "J. K. Rowling", 200, ratingBar.rating.plus(2)  ,R.drawable.livrofechado, true)
        val l4 = Book("Harry Potter e o Cálice de Fogo", "J. K. Rowling", 200, ratingBar.rating.plus(2) ,R.drawable.livrofechado, true)
        val l5 = Book("Harry Potter e a Ordem da Fênix", "J. K. Rowling", 200, ratingBar.rating.plus(2)  ,R.drawable.livrofechado, true)
        val l6 = Book("Harry Potter e o Enigma do Príncipe", "J. K. Rowling", 200, ratingBar.rating.plus(2)  ,R.drawable.livrofechado, true)
        val l7 = Book("Harry Potter e as Relíquias da Morte", "J. K. Rowling", 200,ratingBar.rating.plus(2)  ,R.drawable.livrofechado, true)
        val l8 = Book("O pistoleiro", "Stephen King", 200,ratingBar.rating.plus(2) ,R.drawable.livrofechado, true)
        val l9 = Book("A Escolha dos Três ", "Stephen King", 200, ratingBar.rating.plus(2)  ,R.drawable.livrofechado, true)


        //cadastra novamente
        db.bookDao().inserirAll(
            l1,
            l2,
            l3,
            l4,
            l5,
            l6,
            l7,
            l8,
            l9
        )
    }
}
