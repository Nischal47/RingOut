package com.example.myapplication.calllogs

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

val DATABASE_NAME="MYDB"
val TABLE_NAME="Users"
val COL_PHONE="phone"
val COL_NAME="name"
val COL_TIME="startTime"
val COL_ID="id"

class DataBaseHandler(context:Context):SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
       val createTable="CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
              "$COL_PHONE VARCHAR(50)," +
               "$COL_TIME VARCHAR(50)" +
               ",UNIQUE($COL_PHONE, $COL_TIME)" +
               ")"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun insertData(callRecords: CallLogRecords){
        val db=this.writableDatabase
        val query="SELECT * FROM $TABLE_NAME WHERE $COL_PHONE=${callRecords.phone}"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            //PID Found
        } else {
            var cv=ContentValues()
            cv.put(COL_PHONE,callRecords.phone)
            cv.put(COL_TIME,callRecords.startTime)
            var result= db.insert(TABLE_NAME,null,cv)
            if (result==-1.toLong()) {
                Log.d("Handler","Failed")
            }else{
                Log.d("Handler","Success")
            }
        }
        cursor.close()

    }
    fun readData():MutableList<CallLogRecords>{
        var list:MutableList<CallLogRecords> = ArrayList()
        val db=this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var callRecords=CallLogRecords()
                callRecords.id=result.getString(result.getColumnIndex(COL_ID)).toInt()
                callRecords.phone=result.getString(result.getColumnIndex(COL_PHONE))
                callRecords.startTime=result.getString(result.getColumnIndex(COL_TIME))
                list.add(callRecords)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
    fun deleteData(){
        val db=this.readableDatabase
        db.execSQL("delete from $TABLE_NAME")


        db.close()
    }
}