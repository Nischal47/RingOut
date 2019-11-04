package com.example.myapplication.ringout
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.myapplication.MainActivity


import com.ringcentral.RestClient
import com.ringcentral.RestException
import java.io.IOException

class DisconnectRingOutPublic {
    constructor(responseId: String,context: Context) {
        this.responseId = responseId
        this.mContext=context
    }
    val mContext: Context
    var responseId: String

    var RINGCENTRAL_CLIENTID = "-K4qp_yOS8KwUe1f3ETxHA"
    var RINGCENTRAL_CLIENTSECRET = "9JImtvzOTyeQJJ7dOtQ8Sg_aD-FQF7SZ2m21oFOlkzAg"
    var RINGCENTRAL_SERVER = "https://platform.ringcentral.com"

    var RINGCENTRAL_USERNAME = "8520852016509316886"
    var RINGCENTRAL_PASSWORD = "Kiodaija123"
    var RINGCENTRAL_EXTENSION = "101"
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
            call_disconnect()

        } catch (e: RestException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    @Throws(RestException::class, IOException::class)


    fun call_disconnect() {


        val disconnect = restClient!!.restapi().account().extension().ringout(responseId).delete()
        Log.d("RingCentral", "Call disconnected" + disconnect!!)
        if(disconnect==""){
            var intent= Intent(mContext, MainActivity::class.java)

            mContext.startActivity(intent)
        }
    }
}