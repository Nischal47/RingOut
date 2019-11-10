package com.example.myapplication.calllogs

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class CallLogAdapter(val context:Context,val callrecord:List<CallLogRecords>): RecyclerView.Adapter<CallLogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallLogViewHolder {
       val view=LayoutInflater.from(context).inflate(R.layout.call_log_item,parent,false)
        return CallLogViewHolder(view,context)
    }

    override fun getItemCount(): Int {
       return callrecord.size
    }

    override fun onBindViewHolder(holder: CallLogViewHolder, position: Int) {
        val callLog=callrecord[position]
        holder.setData(callLog)


    }
}