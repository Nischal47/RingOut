package com.example.myapplication.callrecord

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.myapplication.MainActivity
import com.ringcentral.RestClient
import com.ringcentral.RestException
import com.ringcentral.definitions.ReadCallSessionStatusParameters
import java.io.IOException

class CallRecord {

    constructor(responseId: String,context: Context) {
        this.responseId = responseId
        this.mContext=context
    }
    val mContext: Context
    var responseId: String

    var RINGCENTRAL_CLIENTID = "VG9QnUu8RVWvb_pHbedGYA"
    var RINGCENTRAL_CLIENTSECRET = "IRunQHctT7i3qyH1kct5tgBNTQGpLATqm3XdqcNiXkFA"
    var RINGCENTRAL_SERVER = "https://platform.devtest.ringcentral.com"

    var RINGCENTRAL_USERNAME = "+16509316886"
    var RINGCENTRAL_PASSWORD = "Kiodaija123"
    var RINGCENTRAL_EXTENSION = "101"

    val readCallSessionStatusParameters: ReadCallSessionStatusParameters =  ReadCallSessionStatusParameters()
    //
    var restClient: RestClient? = null

    fun RingCentral() {

        try {
            restClient =
                RestClient(RINGCENTRAL_CLIENTID, RINGCENTRAL_CLIENTSECRET, RINGCENTRAL_SERVER)
            restClient!!.authorize(
                RINGCENTRAL_USERNAME,
                RINGCENTRAL_EXTENSION,
                RINGCENTRAL_PASSWORD
            )
            startrecording()

        } catch (e: RestException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    @Throws(RestException::class, IOException::class)


    fun startrecording() {

        val callStatus =  restClient!!.restapi().account().extension().ringout(responseId).get()
        Log.d("CallRecord",callStatus!!.id)
        val sessionId=callStatus!!.id
//
        var getsession = restClient!!.restapi().account().telephony().sessions(sessionId).get(readCallSessionStatusParameters);

        Log.d("CallRecord", "status" + getsession!!.parties.to.phoneNumber)

    }
}