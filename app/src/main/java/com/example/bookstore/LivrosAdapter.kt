package com.example.bookstore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.bookstore.domain.Book

class LivrosAdapter(c: Context, f: List<Book>) : BaseAdapter() {


    var context: Context = c
    var books:List<Book> = f

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = LayoutInflater.from(context).inflate(R.layout.inflaterlivro, parent, false)
        var titulo = v.findViewById<TextView>(R.id.textInfTitle)
        var autor = v.findViewById<TextView>(R.id.textInfAutor)
        var ano = v.findViewById<TextView>(R.id.textAno)
        var nota= v.findViewById<RatingBar>(R.id.textInfNota)

        var image = v.findViewById<ImageView>(R.id.imageView)

        var livroAtual = books.get(position)
        titulo.text = livroAtual.name
        autor.text = livroAtual.author
        nota.rating=livroAtual.note
        return v

    }

    override fun getItem(position: Int): Any {
        return books.get(position)

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getCount(): Int {
        return books.size

    }



}