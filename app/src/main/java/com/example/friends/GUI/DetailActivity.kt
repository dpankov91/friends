package com.example.friends.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.friends.Model.BEFriend
import com.example.friends.Model.Friends
import com.example.friends.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    var isCreate = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (intent.extras != null) {
            isCreate = false
            val extras: Bundle = intent.extras!!
            val name = extras.getString("name")
            val phone = extras.getString("phone")
            val favorite = extras.getBoolean("favorite")
            if(favorite){
                check_favourite.isChecked = true
            }
            tvName.setText(name)
            tvPhone.setText(phone)
            imgFavorite.setImageResource(if (favorite) R.drawable.ok else R.drawable.notok)
        }
        if (intent.extras == null) {
            isCreate = true
            tvName.text = (null)
            tvPhone.text = null
            btnDelete.visibility = View.INVISIBLE
        }
        else
        {
            Log.d("xyz", "system error: intent.extras for detailactivity is null!!!!")
        }

        btnDelete.setOnClickListener{ v -> onClickDelete(v)}
        btnSave.setOnClickListener{ v -> onClickSave(v)}
    }

    fun onClickBack(view: View) { finish() }

    fun onClickDelete(view: View) {
        val friends = Friends
        friends.deleteFriendByName(tvName.text.toString())
        finish()
    }

    fun onClickSave(view: View) {
        if (isCreate == true) {
            val friendToCreate = BEFriend(name = tvName.text.toString(), phone = tvPhone.text.toString(), isFavorite = check_favourite.isChecked)
            Friends.addFriend(friendToCreate)
        }else{
            val friendToUpdate = Friends.findFriendByName(intent.extras?.getString("name"))
            if (friendToUpdate != null) {
                friendToUpdate.name = tvName.text.toString()
                friendToUpdate.phone = tvPhone.text.toString()
                friendToUpdate.isFavorite = check_favourite.isChecked
            }
        }
        finish()
    }

    fun onClickSaveNewFriend(view: View) {
        val friendToAdd = BEFriend( name = tvName.text.toString(), phone =  tvPhone.text.toString(), isFavorite = check_favourite.isChecked)
        val friends = Friends
        friends.addFriend(friendToAdd)
        finish()
    }



}