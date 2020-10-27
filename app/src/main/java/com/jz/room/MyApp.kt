package com.jz.room

import android.app.Application
import com.tencent.mmkv.MMKV

/**
 * @package com.jz.room
 * @filename MyApp
 * date on 2020/10/26 4:27 PM
 * @author jackzhous
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }
}