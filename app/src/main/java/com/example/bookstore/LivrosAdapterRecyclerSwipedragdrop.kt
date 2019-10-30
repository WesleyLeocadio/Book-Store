package com.example.bookstore

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.domain.Book
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class LivrosAdapterRecyclerSwipedragdrop(var context:Context, var books:MutableList<Book>) : RecyclerView.Adapter<ViewHolder>(){

    private val PENDING_REMOVAL_TIMEOUT:Long = 3000 // 3sec
    var itemsPendingRemoval = ArrayList<Book>()

    private val handler = Handler() // hanlder que vai guardar os runnables que devem ser executados
    var pendingRunnables: HashMap<Book, Runnable> =
        HashMap() // map de frutas com runnables pendentes, para que seja possível cancelar

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.novo_inflaterlivro, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val livrocolhido = books[position]

        var livroAtual = books.get(position)
        holder.titulo.text=livroAtual.name
        holder.img.setImageResource(R.drawable.livroaberto)
        holder.autor.text = livroAtual.author
        holder.nota.rating=livroAtual.note


        if (itemsPendingRemoval.contains(livrocolhido)) {
            //view do swipe/delete
            holder.layoutNormal.setVisibility(View.GONE)
            holder.layoutGone.setVisibility(View.VISIBLE)
            holder.undoButton.setVisibility(View.VISIBLE)
            holder.undoButton.setOnClickListener {
                // usou o undo, remover a pendingRennable
                val pendingRemovalRunnable = pendingRunnables[livrocolhido]
                Log.i("AULA17", "CLICOU")
                pendingRunnables.remove(livrocolhido)
                if (pendingRemovalRunnable != null) {
                    handler.removeCallbacks(pendingRemovalRunnable)
                }
                itemsPendingRemoval.remove(livrocolhido)
                //binda novamente para redesenhar
                notifyItemChanged(books.indexOf(livrocolhido))
            }
        } else {
            //mostra o padrão
            holder.titulo.setText(livrocolhido.name)
            holder.layoutNormal.setVisibility(View.VISIBLE)
            holder.layoutGone.setVisibility(View.GONE)
            holder.undoButton.setVisibility(View.GONE)
            holder.undoButton.setOnClickListener(null)



        }



    }

    fun remover (position: Int){

        var livro = books[position]

        if (books.contains(livro)){
            books.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    fun removerComTempo(position: Int) {

        val livro = books[position]

        if (!itemsPendingRemoval.contains(livro)) {
            itemsPendingRemoval.add(livro)
            notifyItemChanged(position)
            var pendingRemovalRunnable = Runnable {
                remover(position)
            }
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT)
            pendingRunnables[livro] = pendingRemovalRunnable
        }

    }

    fun mover(fromPosition: Int, toPosition: Int) {

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(books, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(books, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
        notifyItemChanged(toPosition)
        notifyItemChanged(fromPosition)

    }





}