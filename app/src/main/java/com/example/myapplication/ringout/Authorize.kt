package com.example.myapplication.ringout
import khttp.delete as httpDelete
class Authorize {
    var RINGCENTRAL_CLIENTID = "VG9QnUu8RVWvb_pHbedGYA"
    var RINGCENTRAL_CLIENTSECRET = "IRunQHctT7i3qyH1kct5tgBNTQGpLATqm3XdqcNiXkFA"
    var RINGCENTRAL_SERVER = "https://platform.devtest.ringcentral.com"

    var RINGCENTRAL_USERNAME = "+16509316886"
    var RINGCENTRAL_PASSWORD = "Kiodaija123"
    var RINGCENTRAL_EXTENSION = "101"

   fun authorize(){
       val response=khttp.post(
           url = "https://platform.ringcentral.com/restapi/oauth/authorize",
           data = mapOf("response_type" to "code", "redirect_uri" to "https://ringout1.example.com//callback`","client_id" to RINGCENTRAL_CLIENTID))

   }
}