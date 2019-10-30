package com.example.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.bookstore.ConnectionBD.AppDatabase
import com.example.bookstore.domain.Book
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bd-wesley"
        )
            .allowMainThreadQueries()
            .build()
    }
    //var livros:List<Book>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        var livros:MutableList<Book> = db.bookDao().listAll1()




        var adapter = LivrosAdapterRecycler(this, livros)
        recyclerview.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerview.layoutManager = layout


        recyclerview.addOnItemTouchListener(
            MyRecyclerViewClickListener(
                this@RecyclerActivity,
                recyclerview,
                object : MyRecyclerViewClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        Toast.makeText(this@RecyclerActivity, "Clique simples", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onItemLongClick(view: View, position: Int) {
                        val removida = livros[position]
                        livros.remove(removida)
                        recyclerview.adapter!!.notifyItemRemoved(position)
                        Toast.makeText(this@RecyclerActivity, "Clique longo", Toast.LENGTH_SHORT)
                            .show()
                       val snack = Snackbar.make(
                            recyclerview.parent as View,"Removido",Snackbar.LENGTH_LONG )
                            .setAction("Cancelar") {
                                livros.add(position, removida)
                                recyclerview.adapter!!.notifyItemInserted(position)
                            }
                        snack.show()
                    }
                })

        )
    }
}
