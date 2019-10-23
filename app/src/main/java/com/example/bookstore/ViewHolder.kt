package com.example.bookstore

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class ViewHolder (v: View){

    val titulo: TextView
    val autor: TextView
    val nota: RatingBar
    val img: ImageView

    init {
        Log.i("HOLDER", "Fazendo buscas por id...")
        titulo = v.findViewById(R.id.textInfTitle)
        autor = v.findViewById(R.id.textInfAutor)
        nota = v.findViewById(R.id.textInfNota)
        img = v.findViewById(R.id.img)

    }





}