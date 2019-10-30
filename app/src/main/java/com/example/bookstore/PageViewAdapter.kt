package com.example.bookstore

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.example.bookstore.domain.Book


class PageViewAdapter(var context:Context, var livros:List<Book>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.i("TESTE","INSTANCIOU ")

        val view = LayoutInflater.from(context)
            .inflate(R.layout.livro_pageview_inflater, container, false)
        val img:ImageView = view.findViewById(R.id.imagemPersonagem)
        img.setImageResource(livros[position].img)
        val nome:TextView = view.findViewById(R.id.textPageNome)
        nome.text=livros[position].author
        val ano:TextView = view.findViewById(R.id.textPageAno)
        ano.text=livros[position].year.toString()

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return livros.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        Log.i("TESTE","${ livros[position].name}")
        return livros[position].name
    }
}