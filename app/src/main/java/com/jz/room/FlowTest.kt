package com.jz.room

import com.jz.room.xls.LogUtil
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @package com.jz.room
 * @filename FlowTest
 * date on 2020/10/27 10:12 AM
 * @author jackzhous
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
class FlowTest {

    private fun foo() : Flow<Int> = flow{
        for (i in 0..3){
            delay(100)
            LogUtil.i("send flow $i")
            emit(i)
        }
    }

    fun main() = runBlocking<Unit> {
        launch {
            for (i in 7..9){
                LogUtil.i("got the value $i")
            }
        }
//        foo().collect { LogUtil.i("got the value $it") }
//        foo().toList().forEach {
//            LogUtil.i("got the value $it")
//        }
        //single只会获取一次值，如果返回多个会报异常
        LogUtil.i("got the value " + foo().single())
//        LogUtil.i("got the value " + foo().first())
    }

}


