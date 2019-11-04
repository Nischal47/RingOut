package com.example.myapplication.ringout

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.myapplication.Calling


import com.ringcentral.RestClient
import com.ringcentral.RestException
import com.ringcentral.definitions.MakeRingOutCallerInfoRequestFrom
import com.ringcentral.definitions.MakeRingOutCallerInfoRequestTo
import com.ringcentral.definitions.MakeRingOutRequest
import java.io.IOException


class CallRingOutPublic  {

    constructor( RECIPIENT_NUMBER: String,context: Context){
        this.RECIPIENT_NUMBER=RECIPIENT_NUMBER
        this.mContext=context
    }
    val mContext: Context
    var RECIPIENT_NUMBER :String
    val TOKEN_KEY: String = "rc_token"
    val RINGCENTRAL_CLIENTID = "-K4qp_yOS8KwUe1f3ETxHA"
    val RINGCENTRAL_SERVER = "https://platform.ringcentral.com"
    val RINGCENTRAL_CLIENTSECRET = "9JImtvzOTyeQJJ7dOtQ8Sg_aD-FQF7SZ2m21oFOlkzAg"
    val REDIRECT_URI = "http://localhost:5000/oauth2callback"

    val RINGCENTRAL_USERNAME = "+16509316886"
    val RINGCENTRAL_PASSWORD = "Kiodaija123"
    val RINGCENTRAL_EXTENSION="101"

    var restClient:RestClient?=null
    @Throws(Exception::class)

    fun RingCentral(){


        try {




            restClient = RestClient(RINGCENTRAL_CLIENTID,RINGCENTRAL_CLIENTSECRET,RINGCENTRAL_SERVER)

            
            restClient!!.authorize(
                RINGCENTRAL_USERNAME,
                RINGCENTRAL_EXTENSION,
                RINGCENTRAL_PASSWORD
            )
            call()

        } catch (e: RestException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    @Throws(RestException::class, IOException::class)

    fun call() {
        val requestBody = MakeRingOutRequest()
        requestBody.from(MakeRingOutCallerInfoRequestFrom().phoneNumber(RINGCENTRAL_USERNAME))
        requestBody.to(MakeRingOutCallerInfoRequestTo().phoneNumber(RECIPIENT_NUMBER))
        requestBody.playPrompt = false





        var response = restClient!!.restapi().account().extension().ringout().post(requestBody);
        Log.d("RingCentral","Call Placed. Call status: " + response!!.status.callStatus)

        val callStatus =  restClient!!.restapi().account().extension().ringout(response!!.id).get();
        Log.d("RingCentral","Call status" + callStatus!!.status.callStatus)
        Log.d("RingCentral","Caller status" + callStatus!!.status.callerStatus)
        Log.d("RingCentral","Callee status" + callStatus!!.status.calleeStatus)

        if(response!!.status.callStatus=="InProgress" || response!!.status.callStatus=="Success"){
            var intent= Intent(mContext, Calling::class.java)
            intent.putExtra("responseid", response!!.id);
            mContext.startActivity(intent)

        }



    }

}