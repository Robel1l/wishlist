package com.example.wishlist01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.core.util.Pools
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // declare variables for the wish list of items
    private lateinit var wish: MutableList<WishlistItem>
    private lateinit var wishE: EditText
    private lateinit var urlE: EditText
    private lateinit var prE: EditText
    private lateinit var submit: Button
    private lateinit var wishRv: RecyclerView
    private lateinit var wlistAdapter: WishlistAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // list that holds all the items
        wish = mutableListOf()
        // find the recyclerview for layout
        wishRv = findViewById(R.id.listRv)

        // create the adapter fr recycle view
        wlistAdapter = WishlistAdapter(wish){position ->
            // remove item from the list
            wish.removeAt(position)
            //notification when item is removed
            wlistAdapter.notifyItemRemoved(position)
            Toast.makeText(this,"removed item", Toast.LENGTH_SHORT).show()
        }

        // attach adapter to recycle view and manage layout
        wishRv.adapter = wlistAdapter
        wishRv.layoutManager = LinearLayoutManager(this)

        //initialize and link UI elements to variables
        wishE = findViewById(R.id.item_name)
        urlE = findViewById(R.id.url)
        prE = findViewById(R.id.cost)
        submit = findViewById(R.id.sB)


        // clickListener for the submit button
        submit.setOnClickListener {
            val name = wishE.text.toString()
            val url = urlE.text.toString()

            // an if statement that only adds item to list if it has a name
            if (name.isNotEmpty()){
                // convert price to a double or to float if it fails to convert
                val price = prE.text.toString().toDoubleOrNull() ?: 0.0
                val newwish = WishlistItem(name, url, price)

                // add item to list and notify adapter
                wish.add(newwish)
                wlistAdapter.notifyItemInserted(wish.size -1)

                // clear the input after adding an item
                wishE.text.clear()
                urlE.text.clear()
                prE.text.clear()

            }
            // show an error if item name is empty
            else {
                Toast.makeText(this, "Enter item name", Toast.LENGTH_SHORT).show()
            }
        }


    }
    // remove item from list when clicked
    private fun onItemClick(position: Int){
        val itemRemoved = wish[position]
        wish.removeAt(position)
        wlistAdapter.notifyItemRemoved(position)
        Toast.makeText(this, "${itemRemoved.name} removed", Toast.LENGTH_SHORT).show()
    }
}