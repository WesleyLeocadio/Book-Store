package com.example.bookstore

import android.content.Context
import android.util.Log
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
        var holder:ViewHolder
        var view:View

        if (convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.inflaterlivro, parent, false)

            holder = ViewHolder(view)
            view.tag = holder
        }else{
            view = convertView
            holder = convertView.tag as ViewHolder
        }


        var livroAtual = books.get(position)
        holder.titulo.text=livroAtual.name

        holder.autor.text = livroAtual.author
        holder.nota.rating=livroAtual.note

        //Log.i("info","${livroAtual.lido}")

//        if (livroAtual.lido) {
//           image.setImageResource(R.drawable.livroaberto)
//        } else {
//            image.setImageResource(R.drawable.livroaberto)
//        }
        return view

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