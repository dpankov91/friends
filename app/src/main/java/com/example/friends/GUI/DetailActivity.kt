package com.example.friends.GUI

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.friends.Model.BEFriend
import com.example.friends.Model.Friends
import com.example.friends.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    var isCreate = true
    var friendsWebsite = ""
    var friendsEmail = arrayOf("")
    var LOG = "logs"
    var friendsPhone = ""
    val PERMISSION_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (intent.extras != null) {
            isCreate = false
            val extras: Bundle = intent.extras!!
            val name = extras.getString("name")
            val phone = extras.getString("phone")
            val favorite = extras.getBoolean("favorite")
            val address = extras.getString("address")
            val email = extras.getString("email")
            val website = extras.getString("website")
            friendsWebsite = website.toString()
            friendsEmail = arrayOf(email.toString())
            friendsPhone = phone.toString()


            if(favorite){
                check_favourite.isChecked = true
            }
            tvName.setText(name)
            tvPhone.setText(phone)
            etAddress.setText(address)
            etEmail.setText(email)
            etWebsite.setText(website)
            imgFavorite.setImageResource(if (favorite) R.drawable.ok else R.drawable.notok)
        }
        if (intent.extras == null) {
            isCreate = true
            tvName.text = null
            tvPhone.text = null
            etAddress.text = null
            etEmail.text = null
            etWebsite.text = null
            btnDelete.visibility = View.INVISIBLE
        }
        else
        {
            Log.d(LOG, "system error: intent.extras for detailactivity is null!!!!")
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
        if (isCreate) {
            val friendToCreate = BEFriend(name = tvName.text.toString(), phone = tvPhone.text.toString(), isFavorite = check_favourite.isChecked
                        ,address = etAddress.text.toString(), email = etEmail.text.toString(), webSite = etWebsite.text.toString())
            Friends.addFriend(friendToCreate)
        }else{
            val friendToUpdate = Friends.findFriendByName(intent.extras?.getString("name"))
            if (friendToUpdate != null) {
                friendToUpdate.name = tvName.text.toString()
                friendToUpdate.phone = tvPhone.text.toString()
                friendToUpdate.isFavorite = check_favourite.isChecked
                friendToUpdate.address = etAddress.text.toString()
                friendToUpdate.email = etEmail.text.toString()
                friendToUpdate.webSite = etWebsite.text.toString()
            }
        }
        finish()
    }

    fun onClickOpenSMS(view: View) {
        showYesNoDialog()
    }

    private fun showYesNoDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("SMS Handling")
        alertDialogBuilder
            .setMessage("Click Direct if SMS should be send directly. Click Start to start SMS app...")
            .setCancelable(true)
            .setPositiveButton("Direct") { dialog, id -> sendSMSDirectly() }
            .setNegativeButton("Start") { dialog, id -> startSMSActivity() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun sendSMSDirectly() {
        Toast.makeText(this, "An sms will be send", Toast.LENGTH_LONG)
            .show()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_DENIED) {
                Log.d(LOG, "permission denied to SEND_SMS - requesting it")
                val permissions = arrayOf(Manifest.permission.SEND_SMS)
                requestPermissions(permissions, PERMISSION_REQUEST_CODE)
                return
            } else Log.d(LOG, "permission to SEND_SMS granted!")
        } else Log.d(LOG, "Runtime permission not needed")
        sendMessage()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        Log.d(LOG, "Permission: " + permissions[0] + " - grantResult: " + grantResults[0])
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val m = SmsManager.getDefault()
        val text = "Hi, it goes well on the android course..."
        m.sendTextMessage(friendsPhone, null, text, null, null)
    }

    private fun startSMSActivity(){
        val sendIntent = Intent(Intent.ACTION_VIEW)
        sendIntent.data = Uri.parse("sms:$friendsPhone")
        startActivity(sendIntent)
    }

    fun onClickOpenCall(view: View) {
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:$friendsPhone")
        startActivity(callIntent)
    }

    fun onClickOpenWebsite(view: View) {
        val url = friendsWebsite
        Log.d(LOG, url)
        val browserIntent = Intent(Intent.ACTION_VIEW)
        browserIntent.data = Uri.parse(url)
        startActivity(browserIntent)
    }

    fun onClickOpenEmail(view: View) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "plain/text"
        val recievers = friendsEmail
        Log.d(LOG, recievers.toString())
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recievers)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Lets meet")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Do you wanna catch up in bar ?")
        startActivity(emailIntent)
    }
}