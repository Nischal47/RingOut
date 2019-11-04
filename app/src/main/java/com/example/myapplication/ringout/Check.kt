//package com.example.rcphone.ringout
//
//import com.ringcentral.RestClient
//import com.ringcentral.RestException
//import khttp.responses.Response
//import java.io.IOException
//
//class Check {
//
//    val RINGCENTRAL_CLIENTID = "-K4qp_yOS8KwUe1f3ETxHA"
//    val RINGCENTRAL_SERVER = "https://platform.ringcentral.com"
//    val RINGCENTRAL_CLIENTSECRET = "9JImtvzOTyeQJJ7dOtQ8Sg_aD-FQF7SZ2m21oFOlkzAg"
//    val REDIRECT_URI = "http://localhost:5000/oauth2callback"
//
//    val RINGCENTRAL_USERNAME = "+16509316886"
//    val RINGCENTRAL_PASSWORD = "Kiodaija123"
//    var RINGCENTRAL_EXTENSION = "101"
//
//    var restClient: RestClient? = null
//
//
//    fun RingCentral(){
//
//        try {
//            khttp.post(
//                url = "https://platform.ringcentral.com/restapi/oauth/authorize",
//                data = mapOf("response_type" to "code", "redirect_uri" to "/","client_id" to "$RINGCENTRAL_CLIENTID"))
//
//            val response:Response=khttp.get("")
//            val authcode =
//
//            restClient =
//                RestClient(RINGCENTRAL_CLIENTID, RINGCENTRAL_CLIENTSECRET, RINGCENTRAL_SERVER)
//
//
//
//            val authcode=
//            restClient!!.authorize(
//                RINGCENTRAL_USERNAME,
//                RINGCENTRAL_EXTENSION,
//                RINGCENTRAL_PASSWORD
//            )
//            call()
//
//        } catch (e: RestException) {
//            e.printStackTrace()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun call() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//
//}