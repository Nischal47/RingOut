package com.example.myapplication.ringout

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.myapplication.Calling


import java.io.IOException
import com.ringcentral.*
import com.ringcentral.definitions.*
import com.ringcentral.definitions.MakeRingOutRequest
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.handler.AbstractHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class Call_RingOut  {

    constructor( RECIPIENT_NUMBER: String,context: Context){
        this.RECIPIENT_NUMBER=RECIPIENT_NUMBER
        this.mContext=context
    }
    val mContext:Context
    var RECIPIENT_NUMBER :String

    var RINGCENTRAL_CLIENTID = "U6mQ4NZDS-606j40IGsPEQ"
    var RINGCENTRAL_CLIENTSECRET = "LkfnevYmQFyEvZsg0prrLQyBDrlXTBShS4iVSpqyrXFQ"
    var RINGCENTRAL_SERVER = "https://platform.devtest.ringcentral.com"

    var RINGCENTRAL_USERNAME = "+14707992801"
    var RINGCENTRAL_PASSWORD = "Kiodaija123"
    var RINGCENTRAL_EXTENSION = "101"


    var restClient: RestClient? = null


    fun RingCentral(){

        try {
            restClient =
                RestClient(RINGCENTRAL_CLIENTID, RINGCENTRAL_CLIENTSECRET, RINGCENTRAL_SERVER)
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


