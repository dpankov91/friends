package com.example.friends.Model

import android.util.Log

object Friends {

    val mFriends = mutableListOf<BEFriend>(
    BEFriend("Bob", "56481124", isFavorite = false, email = "bob@mail.com", address = "Street1", webSite = "http://www.facebook.com"),
    BEFriend("Sinan", "54233212", isFavorite = true, email = "sinan@mail.com", address = "Street2", webSite = "http://www.facebook.com"),
    BEFriend("Dylan", "54233244", isFavorite = false, email = "dylan@mail.com", address = "Street3", webSite = "http://www.facebook.com"),
    BEFriend("Kevin", "56481333", isFavorite = true, email = "kevin@mail.com", address = "Street4", webSite = "http://www.facebook.com"),
    BEFriend("Mario", "56481284", isFavorite = false, email = "mario@mail.com", address = "Street5", webSite = "http://www.facebook.com"),
    BEFriend("Selim", "53481221", isFavorite = true, email = "selim@mail.com", address = "Street6", webSite = "http://www.facebook.com"),
    BEFriend("Bastian", "56481183", isFavorite = false, email = "bastian@mail.com", address = "Street7", webSite = "http://www.facebook.com"),
    BEFriend("Jessey", "58433126", isFavorite = true, email = "jessey@mail.com", address = "Street8", webSite = "http://www.facebook.com"),
    BEFriend("Pamely", "52288901", isFavorite = true, email = "pamely@mail.com", address = "Street9", webSite = "http://www.facebook.com"),
    BEFriend("Marine", "56112124", isFavorite = false, email = "marine@mail.com", address = "Street10", webSite = "http://www.facebook.com"),
    BEFriend("Yulia", "59221331", isFavorite = false, email = "yulia@mail.com", address = "Street11", webSite = "http://www.facebook.com"),
    BEFriend("Venesa", "57019910", isFavorite = true, email = "venesa@mail.com", address = "Street12", webSite = "http://www.facebook.com")
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

    fun getAll(): MutableList<BEFriend> = mFriends

    fun getAllNames(): Array<String> = mFriends.map { friend -> friend.name }.toTypedArray()

}