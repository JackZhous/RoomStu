package com.jz.room.xls

import android.util.Log

/**
 * @package com.jz.room.xls
 * @filename LogUtil
 * date on 2020/10/26 11:41 AM
 * @author jackzhous
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
class LogUtil {

    companion object{
        const val TAG = "j_tag"

        fun i(msg : String){
            Log.i(TAG, msg)
        }

        fun i(msg : Int){
            Log.i(TAG, ""+msg)
        }

    }
}