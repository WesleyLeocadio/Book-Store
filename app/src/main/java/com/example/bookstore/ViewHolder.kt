package com.example.bookstore

import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ViewHolder (v: View): RecyclerView.ViewHolder(v){

    val titulo: TextView
    val autor: TextView
    val nota: RatingBar
    val img: ImageView
    val layoutNormal:LinearLayout
    val layoutGone:LinearLayout
    val undoButton: Button


    init {
        Log.i("HOLDER", "Fazendo buscas por id...")
        titulo = v.findViewById(R.id.textInfTitle)
        autor = v.findViewById(R.id.textInfAutor)
        nota = v.findViewById(R.id.textInfNota)
        img = v.findViewById(R.id.img)
      layoutNormal = v.findViewById(R.id.layout_normal)
       layoutGone = v.findViewById(R.id.layout_gone)
      undoButton  = v.findViewById(R.id.undo_button)



    }





}