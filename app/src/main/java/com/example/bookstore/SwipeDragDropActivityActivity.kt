package com.example.bookstore

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.bookstore.ConnectionBD.AppDatabase
import com.example.bookstore.domain.Book
import kotlinx.android.synthetic.main.activity_swipe_drag_drop_activity.*

class SwipeDragDropActivityActivity : AppCompatActivity() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bd-wesley")
            .allowMainThreadQueries()
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_drag_drop_activity)

        var listaLivros:MutableList<Book> = db.bookDao().listAll1()
        var adapter = LivrosAdapterRecyclerSwipedragdrop(this,listaLivros )
        recyclerview.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerview.layoutManager = layout

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.START or ItemTouchHelper.END )
        {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {

                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                val adapter = recyclerView.adapter as LivrosAdapterRecyclerSwipedragdrop
                adapter.mover(fromPosition, toPosition)

                return true

            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                var posicao = viewHolder.adapterPosition
                var adapter = recyclerview.adapter as  LivrosAdapterRecyclerSwipedragdrop

                adapter.removerComTempo(posicao)

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ){
                val itemView = viewHolder.itemView
                val background = ColorDrawable(resources.getColor(R.color.colorPrimary))

                if (viewHolder.adapterPosition === -1) {
                    return
                }

                if (dX < 0) {
                    background.setBounds(
                        (itemView.right + dX).toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                }else if (dX > 0) {
                    background.setBounds(
                        itemView.left,
                        itemView.top,
                        (dX).toInt(),
                        itemView.bottom
                    )
                }

                background.draw(c)

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )

            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return true
            }

        })

        itemTouchHelper.attachToRecyclerView(recyclerview)

    }
}
