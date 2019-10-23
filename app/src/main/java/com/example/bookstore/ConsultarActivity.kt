package com.example.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.room.Room
import com.example.bookstore.ConnectionBD.AppDatabase
import com.example.bookstore.domain.Book
import kotlinx.android.synthetic.main.activity_consultar.*

class ConsultarActivity : AppCompatActivity() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bd-wesley")
            .allowMainThreadQueries()
            .build()
    }

    var books = ArrayList<Book>()
    var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)

        setBooks()

        newBook()

        btnDireita.setOnClickListener {
            cont++
            newBook()
        }

        btnEsquerda.setOnClickListener {
            cont--
            newBook()
        }
        btnDelete.setOnClickListener {
            Log.i("cont","${books.get(cont)}")
            db.bookDao().deletar(books.get(cont))
            setBooks()


        }





    }

    fun setBooks(){
        books.clear()
        db.bookDao().listAll().forEach {
            books.add(it)

        }
        newBook()

    }

    fun newBook() {
        if (books.size > 0) {
            textTitle.text = books.get(cont).name
            textAutor.text = books.get(cont).author
            textAno.text=books.get(cont).year.toString()
            textNota.text = books.get(cont).note.toString()
            btnDireita.visibility = View.VISIBLE
            btnEsquerda.visibility = View.VISIBLE
            btnDelete.visibility= View.VISIBLE
            btnEditar.visibility= View.VISIBLE

            checkLenght()
        } else {
            btnDireita.visibility = View.INVISIBLE
            btnEsquerda.visibility = View.INVISIBLE
            btnDelete.visibility= View.INVISIBLE
            btnEditar.visibility= View.INVISIBLE
            clear()

        }
    }
    fun checkLenght() {
        if (cont + 1 >= books.size) {
            btnDireita.visibility = View.INVISIBLE
        } else {
            btnEsquerda.visibility = View.VISIBLE
        }


        if (cont - 1 < 0 ) {
            btnEsquerda.visibility = View.INVISIBLE
        }
    }
    fun clear(){
        textTitle.text = ""
        textAutor.text = ""
        textAno.text = ""
        textNota.text = ""
    }

}
