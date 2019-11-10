package com.example.myapplication.calllogs


import android.content.Context

import android.util.Log

import com.ringcentral.RestClient
import com.ringcentral.RestException
import com.ringcentral.definitions.*
import java.io.IOException
import com.example.myapplication.MainActivity
import com.example.myapplication.R


class ReadingActiveCall(val Context:Context) {


    var RINGCENTRAL_CLIENTID = "VG9QnUu8RVWvb_pHbedGYA"
    var RINGCENTRAL_CLIENTSECRET = "IRunQHctT7i3qyH1kct5tgBNTQGpLATqm3XdqcNiXkFA"
    var RINGCENTRAL_SERVER = "https://platform.devtest.ringcentral.com"

    var RINGCENTRAL_USERNAME = "+16509316886"
    var RINGCENTRAL_PASSWORD = "Kiodaija123"
    var RINGCENTRAL_EXTENSION = "101"
    var restClient: RestClient? = null

    fun Read_CallLog(){
        var list:ArrayList<CallLogRecords> = ArrayList<CallLogRecords>()
        try {
           restClient =
                RestClient(RINGCENTRAL_CLIENTID, RINGCENTRAL_CLIENTSECRET, RINGCENTRAL_SERVER)
            restClient!!.authorize(
                RINGCENTRAL_USERNAME,
                RINGCENTRAL_EXTENSION,
                RINGCENTRAL_PASSWORD
            )
            readCallLog()


        } catch (e: RestException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


    @Throws(RestException::class, IOException::class)

    fun readCallLog() {
        val parameters: ReadUserCallLogParameters = ReadUserCallLogParameters()
        parameters.view = "Detailed"

        var response = restClient!!.restapi().account().extension().calllog().list(parameters)

        var callLogs:ArrayList<CallLogRecords> = ArrayList<CallLogRecords>()
        for (record: CallLogRecord in response!!.records) {





            var callRecords=CallLogRecords(record.to.phoneNumber,record.startTime)
            var db=DataBaseHandler(Context)
            db.insertData(callRecords)


            Log.d("CallLogs","Caller Name"+record.from.name)
            Log.d("CallLogs","Caller"+record.from.phoneNumber)
            Log.d("CallLogs","Callee"+record.to.phoneNumber)
            Log.d("CallLogs","tiem"+record.startTime)
        }



    }
}