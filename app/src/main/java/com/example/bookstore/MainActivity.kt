package com.example.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.bookstore.ConnectionBD.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}
