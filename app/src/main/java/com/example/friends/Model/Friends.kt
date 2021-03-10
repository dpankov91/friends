package com.example.friends.Model

import android.util.Log

object Friends {

    val mFriends = mutableListOf<BEFriend>(
    BEFriend("Bob", "56481124", isFavorite = false),
    BEFriend("Sinan", "54233212", isFavorite = true),
    BEFriend("Dylan", "54233244", isFavorite = false),
    BEFriend("Kevin", "56481333", isFavorite = true),
    BEFriend("Mario", "56481284", isFavorite = false),
    BEFriend("Selim", "53481221", isFavorite = true),
    BEFriend("Bastian", "56481183", isFavorite = false),
    BEFriend("Jessey", "58433126", isFavorite = true),
    BEFriend("Pamely", "52288901", isFavorite = true),
    BEFriend("Marine", "56112124", isFavorite = false),
    BEFriend("Yulia", "59221331", isFavorite = false),
    BEFriend("Venesa", "57019910", isFavorite = true)
    )

    fun deleteFriendByName(name: String){
        val friend =   mFriends.find { friend -> friend.name == name }
        Log.d("TAG","friend deleted name = $name")
        mFriends.remove(friend)
    }

    fun addFriend(friend: BEFriend){
        mFriends.add(friend)
    }

    fun findFriendByName(name: String?): BEFriend? {
        val friend =  mFriends.find { friend -> friend.name == name }
        return friend
    }

    fun updateFriend(friendToUpdate: BEFriend){

    }

    fun getAll(): MutableList<BEFriend> = mFriends

    fun getAllNames(): Array<String> = mFriends.map { friend -> friend.name }.toTypedArray()



}