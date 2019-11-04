package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ringout.Call_RingOut


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var RINGCENTRAL_CLIENTID = "-K4qp_yOS8KwUe1f3ETxHA"
    var RINGCENTRAL_CLIENTSECRET = "9JImtvzOTyeQJJ7dOtQ8Sg_aD-FQF7SZ2m21oFOlkzAg"
    var RINGCENTRAL_SERVER = "https://platform.devtest.ringcentral.com"

    var RINGCENTRAL_USERNAME = "+16509316886"
    var RINGCENTRAL_PASSWORD = "Kiodaija123"
    var RINGCENTRAL_EXTENSION = "101"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//
//        val intent= Intent(Intent.ACTION_VIEW, Uri.parse( "https://platform.ringcentral.com/restapi/oauth/authorize?response_type=code&redirect_uri=com.example.rcphone://callback&client_id=$RINGCENTRAL_CLIENTID"))
//        startActivity(intent)

        val uri: Uri? = getIntent().data

        println(uri)


        btnDial.setOnClickListener {
            val phone = PhoneNumber.text.toString()
            call(phone)
        }

    }

    fun call(phone: String) {
        Test(phone, this).execute()
    }

    class Test(phone: String, context: Context) : AsyncTask<Void, Void, Unit>() {
        
        val mContext = context
        var phoneNumber: String = phone

        override fun doInBackground(vararg p0: Void?) {



                var callRingout = Call_RingOut(phoneNumber, mContext)
                callRingout.RingCentral()





        }


    }
}












































