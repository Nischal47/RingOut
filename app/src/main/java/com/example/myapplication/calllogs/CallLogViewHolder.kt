package com.example.myapplication.calllogs

import android.content.Context
import android.telecom.Call
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.call_log_item.view.*

class CallLogViewHolder(itemView:View,context:Context):RecyclerView.ViewHolder(itemView) {
    val context = context

    fun setData(CallRecord: CallLogRecords){

        itemView.textViewName.text=CallRecord.phone
        itemView.textViewPhoneNumber.text=CallRecord.startTime

    }

}