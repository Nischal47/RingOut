package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.calllogs.CallLogAdapter
import com.example.myapplication.calllogs.CallLogRecords

import com.example.myapplication.calllogs.DataBaseHandler
import com.example.myapplication.calllogs.ReadingActiveCall

import kotlinx.android.synthetic.main.activity_call_logs.*

class CallLogs : AppCompatActivity() {

    val phone: ArrayList<String> = ArrayList()
    var db = DataBaseHandler(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_logs)



    }

    override fun onStart() {
        super.onStart()
        call()


        var callLogs:MutableList<CallLogRecords> = db.readData()

        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = GridLayoutManager.VERTICAL
        recycView.layoutManager = layoutManager

        val adapter = CallLogAdapter(this, callLogs)
        recycView.adapter = adapter
    }





//    override fun onStop() {
//        super.onStop()
//        db.deleteData()
//    }

    fun call() {
        Test(this).execute()
    }

    class Test(context: Context) : AsyncTask<Void, Void, Unit>() {
        val mContext = context


        override fun doInBackground(vararg p0: Void?) {

            var readingActiveCall = ReadingActiveCall(mContext)
            readingActiveCall.Read_CallLog()


        }

    }
}
