package com.example.bookstore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.domain.Book

class LivrosAdapterRecycler(c: Context, f: List<Book>) : RecyclerView.Adapter<ViewHolder>(){
    var context:Context = c
    var books:List<Book> = f
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.inflaterlivro, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        var livroAtual = books.get(position)
        holder.titulo.text=livroAtual.name

        holder.autor.text = livroAtual.author
        holder.nota.rating=livroAtual.note



    }





}