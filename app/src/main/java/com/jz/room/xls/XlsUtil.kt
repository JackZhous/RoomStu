package com.jz.room.xls

import android.content.Context
import android.util.Log
import com.jz.room.adapter.Result
import com.jz.room.room.MyRoom
import com.jz.room.room.table.CXC
import com.jz.room.room.table.MeiKe
import com.jz.room.room.table.PerfectDiary
import com.tencent.mmkv.MMKV
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream

/**
 * @package com.jz.room.xls
 * @filename XlsUtil
 * date on 2020/10/26 10:47 AM
 * @author jackzhous
 * @describe XLS文件操作助手
 * @email jackzhouyu@foxmail.com
 **/
class XlsUtil(val ctx : Context, val room : MyRoom) {

    //对应在xlsx中的列数
    private val perfectDiary = arrayOf(2, 3, 4, 5, 6, 7, 8, 9)
    private val mieke = arrayOf(0,1,2,3,4,5)
    private val cxc = arrayOf(0,1,2,3,4,5,6,7)

    private val mmkv : MMKV by lazy(LazyThreadSafetyMode.NONE){
        MMKV.defaultMMKV()
    }

    private val labelPerfect = "perfect"
    private val labelMeike = "meike"
    private val labelCXC = "cxc"

    fun clearAllData(){
        room.perfectDiaryDao().deleteAll()
        room.meiKeDao().deleteAll()
        room.cxcDao().deleteAll()
        mmkv.putBoolean(labelPerfect, false)
        mmkv.putBoolean(labelMeike, false)
        mmkv.putBoolean(labelCXC, false)
    }

    fun initData() {
        val perfect = mmkv.getBoolean(labelPerfect, false)
        if(!perfect){
            LogUtil.i("load PerfectDiary")
            val list = readExcel(FileUtil.openFile(ctx, "performD.xlsx"), this::parsePerformDiaryItem)
            room.perfectDiaryDao().insetDiary(list)
            mmkv.putBoolean(labelPerfect, true)
        }
        val meike = mmkv.getBoolean(labelMeike, false)
        if(!meike){
            LogUtil.i("load meike")
            val list = readExcel(FileUtil.openFile(ctx, "meike.xlsx"), this::parseMeikeItem)
            room.meiKeDao().insetDiary(list)
            mmkv.putBoolean(labelMeike, true)
        }

        val cxc = mmkv.getBoolean(labelCXC, false)
        if(!cxc){
            LogUtil.i("load cxc")
            val list = readExcel(FileUtil.openFile(ctx, "chenxiaochen.xlsx"), this::parseCXCItem)
            room.cxcDao().insetDiary(list)
            mmkv.putBoolean(labelCXC, true)
        }

    }

    private fun <T> readExcel(file: InputStream, parse: (row: XSSFRow?, formulaEvaluator: XSSFFormulaEvaluator?) -> T?) : List<T> {
        try {
            val workbook = XSSFWorkbook(file)
            val sheet = workbook.getSheetAt(0)
            val rowsCount = sheet.physicalNumberOfRows
            val formulaEvaluator = workbook.creationHelper.createFormulaEvaluator()
            val list : MutableList<T> = ArrayList()
            for (r in 0..rowsCount) {
                val row = sheet.getRow(r)
                parse(row, formulaEvaluator)?.let {
                    list.add(it)
                }
            }
            return list
        }catch (e : NullPointerException){
            e.printStackTrace()
        }
        return emptyList()
    }


    private fun parsePerformDiaryItem(row: XSSFRow?, formulaEvaluator: XSSFFormulaEvaluator?) : PerfectDiary?{
        val code = getCellAsString(row, perfectDiary[0], formulaEvaluator)
        val name = getCellAsString(row, perfectDiary[1], formulaEvaluator)
        if(code.isNullOrEmpty() && name.isNullOrEmpty()){
            return null
        }
        val price = getCellAsString(row, perfectDiary[2], formulaEvaluator)
        val discount = getCellAsString(row, perfectDiary[3], formulaEvaluator)
        val vPrice = getCellAsString(row, perfectDiary[4], formulaEvaluator)
        val svDisc = getCellAsString(row, perfectDiary[5], formulaEvaluator)
        val svPrice = getCellAsString(row, perfectDiary[6], formulaEvaluator)



        val ret = PerfectDiary(name, code, price, discount, vPrice, svDisc, svPrice)
        getCellAsString(row, perfectDiary[7], formulaEvaluator)?.apply {
            ret.remark = this
        }
        return ret
    }

    private fun parseCXCItem(row: XSSFRow?, formulaEvaluator: XSSFFormulaEvaluator?) : CXC?{
        val code = getCellAsString(row, cxc[0], formulaEvaluator)
        val name = getCellAsString(row, cxc[1], formulaEvaluator)
        if(code.isNullOrEmpty() || name.isNullOrEmpty()){
            return null
        }
        val unit = getCellAsString(row, cxc[2], formulaEvaluator)
        val price = getCellAsString(row, cxc[3], formulaEvaluator)
        val discount = getCellAsString(row, cxc[4], formulaEvaluator)
        val disPrice = getCellAsString(row, cxc[5], formulaEvaluator)
        val unitHelper = getCellAsString(row, cxc[6], formulaEvaluator)

        val ret = CXC(name, code, unit, price, discount, disPrice, unitHelper)
        getCellAsString(row, cxc[7], formulaEvaluator)?.apply {
            ret.remark = this
        }
        return ret
    }

    private fun parseMeikeItem(row: XSSFRow?, formulaEvaluator: XSSFFormulaEvaluator?) : MeiKe?{
        val name = getCellAsString(row, mieke[0], formulaEvaluator)
        val unit = getCellAsString(row, mieke[1], formulaEvaluator)
        if(unit.isNullOrEmpty() && name.isNullOrEmpty()){
            return null
        }
        val patchNo = getCellAsString(row, mieke[2], formulaEvaluator)
        val price = getCellAsString(row, mieke[3], formulaEvaluator)
        val discount = getCellAsString(row, mieke[4], formulaEvaluator)
        val outPrice = getCellAsString(row, mieke[5], formulaEvaluator)

        return MeiKe(name, unit, patchNo, price, discount, outPrice)
    }

    private fun getCellAsString(row: XSSFRow?, c: Int, formulaEvaluator: XSSFFormulaEvaluator?): String? {

        try {
            val cell = row?.getCell(c)
            val cellValue = formulaEvaluator?.evaluate(cell);
            return when (cellValue?.cellType) {
                Cell.CELL_TYPE_BOOLEAN ->
                    "" + cellValue.booleanValue

                Cell.CELL_TYPE_NUMERIC ->
                    "" + cellValue.numberValue

                Cell.CELL_TYPE_STRING ->
                    "" + cellValue.stringValue;
                else ->
                    return null
            }
        } catch (e: NullPointerException) {
            /* proper error handling should be here */
            e.printStackTrace()
        }
        return null
    }


    fun query(key : String) : List<Result>{
        val ret : MutableList<Result> = ArrayList()
        ret.addAll(room.perfectDiaryDao().queryDiary(key))
        ret.addAll(room.meiKeDao().queryDiary(key))
        ret.addAll(room.cxcDao().queryDiary(key))
        return ret
    }


}



