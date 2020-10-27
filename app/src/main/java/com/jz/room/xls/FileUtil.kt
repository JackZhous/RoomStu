package com.jz.room.xls

import android.content.Context
import java.io.InputStream

/**
 * @package com.jz.room.xls
 * @filename FileUtil
 * date on 2020/10/26 11:37 AM
 * @author jackzhous
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
class FileUtil {
    companion object{
        fun openFile(ctx : Context, file : String) : InputStream{
            return ctx.assets.open(file)
        }
    }

}