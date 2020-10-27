package com.jz.room

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.jz.room.adapter.Result
import com.jz.room.adapter.ResultAdapter
import com.jz.room.room.MyRoom
import com.jz.room.room.dao.PerfectDiaryDao
import com.jz.room.room.table.CXC
import com.jz.room.room.table.MeiKe
import com.jz.room.room.table.PerfectDiary
import com.jz.room.xls.FileUtil.Companion.openFile
import com.jz.room.xls.XlsUtil
import java.lang.StringBuilder

/**
 * @package com.jz.room
 * @filename MainsActivity
 * date on 2020/10/26 11:48 AM
 * @author jackzhous
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
class MainsActivity : AppCompatActivity() {

    private val room : MyRoom by lazy(LazyThreadSafetyMode.NONE){
        MyRoom.getInstance(applicationContext)
    }
    private val xls : XlsUtil by lazy(LazyThreadSafetyMode.NONE){
        XlsUtil(this, room)
    }

    private val key : EditText by lazy(LazyThreadSafetyMode.NONE){
        findViewById<EditText>(R.id.key)
    }
    private val show : TextView by lazy(LazyThreadSafetyMode.NONE){
        findViewById<TextView>(R.id.show)
    }
    private val listView : ListView by lazy(LazyThreadSafetyMode.NONE){
        findViewById<ListView>(R.id.list)
    }
    private val adpter : ResultAdapter<Result> by lazy(LazyThreadSafetyMode.NONE){
        ResultAdapter<Result>(this)
    }

    private val TAG = "j_tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
//        printTable(room.perfectDiaryDao().queryAllDiary())
//        printTableM(room.meiKeDao().queryAllDiary())
//        printTableC(room.cxcDao().queryAllDiary())

    }

    private fun initView(){
        findViewById<Button>(R.id.check).setOnClickListener{
            val sKey = key.text.toString()
            val ret = xls.query("%$sKey%")
            show.text = "共查询到${ret.size}条数据"
            adpter.setList(ret)
        }
        xls.initData()
//        findViewById<Button>(R.id.add).setOnClickListener{
//           xls.initData()
//        }
//
//        findViewById<Button>(R.id.clear).setOnClickListener{
//            xls.clearAllData()
//        }
        listView.adapter = adpter
    }


    fun printTable(users: List<PerfectDiary>) {
        Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        for (u in users) {
            Log.i(TAG, u.toString())
        }
        Log.i(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n")
    }

    fun printTableM(users: List<MeiKe>) {
        Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        for (u in users) {
            Log.i(TAG, u.toString())
        }
        Log.i(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n")
    }

    fun printTableC(users: List<CXC>) {
        Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        for (u in users) {
            Log.i(TAG, u.toString())
        }
        Log.i(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n")
    }
}
