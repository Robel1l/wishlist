package com.example.wishlist01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

// connect wishlist to recycler view
class WishlistAdapter(private val wish_i: MutableList<WishlistItem>,
    private val onItemClick: (position: Int) -> Unit):
    RecyclerView.Adapter<WishlistAdapter.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        // declare UI elements for the wishlist items
        val wishTView: TextView
        val urlTView: TextView
        val prTView: TextView

        init {
            //link the properties to the UI elements
            wishTView = itemView.findViewById(R.id.item_nameView)
            urlTView = itemView.findViewById(R.id.ur_lView)
            prTView = itemView.findViewById(R.id.cost_View)

        }
    }

    // create new view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //get context for the parent
        val context = parent.context
        // create layoutInflate
        val inf = LayoutInflater.from(context)
        // inflate the item layout
        val cV = inf.inflate(R.layout.wishlist_item, parent, false)
        //return a new holder instance
        return ViewHolder(cV)
    }

    // bind the data to the views
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        // get item based on position
        val item = wish_i.get(position)

        // bind items to textview in layout
        holder.wishTView.text = item.name
        holder.urlTView.text = item.url
        holder.prTView.text = item.price.toString()

        holder.itemView.setOnClickListener { onItemClick(holder.adapterPosition) }
    }

    override fun getItemCount(): Int {
        return wish_i.size
    }


    }
