package com.example.friends.GUI

import android.app.ListActivity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.friends.Model.BEFriend
import com.example.friends.Model.Friends
import com.example.friends.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val friends = Friends
        val friendNames = friends.getAllNames()

        val adapter: ListAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, friendNames
        )

        friendList.adapter = adapter

        friendList.setOnItemClickListener { _, _, position, _ -> onListItemClick(position) }

    }

    fun onListItemClick( position: Int ) {
        // position is in the list!
        // first get the name of the person clicked
        /*val name = Friends().getAll()[position].name
        // and a greeting
        Toast.makeText(
            this,
            "Hi $name! Have you done your homework?",
            Toast.LENGTH_LONG
        ).show()*/
        val intent = Intent(this, DetailActivity::class.java)
        val friend = Friends.getAll()[position]

        intent.putExtra("name", friend.name )
        intent.putExtra("phone", friend.phone)
        intent.putExtra("favorite", friend.isFavorite)
        startActivity(intent)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.getItemId()

        when (id) {
            R.id.action_settings -> {
                Toast.makeText(this, "Action Settings selected...", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_add -> {

                val intent = Intent(this, DetailActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_close -> {
                finish()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}