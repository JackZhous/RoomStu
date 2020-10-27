package com.jz.room.room.table;

import com.jz.room.adapter.Result;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author jackzhous
 * @package com.jz.room.room.table
 * @filename MeiKe
 * date on 2020/10/26 4:17 PM
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
@Entity
public class MeiKe implements Result {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String productName;
    @ColumnInfo(name = "unit")
    public String unit;
    @ColumnInfo(name ="patchDate")
    public String patchDate;
    @ColumnInfo(name = "price")
    public String price;
    @ColumnInfo(name = "discount")
    public String discount;
    @ColumnInfo(name = "outPrice")
    public String outPrice;


    public MeiKe(String productName, String unit, String patchDate, String price, String discount, String outPrice) {
        this.productName = productName;
        this.unit = unit;
        this.patchDate = patchDate;
        this.price = price;
        this.discount = discount;
        this.outPrice = outPrice;
    }

    @Override
    public String getName() {
        return productName;
    }

    @Override
    public String getDetail() {
        return "单位 = " + unit + "\n" +
                "批号日期 = " + patchDate + "\n" +
                "专柜价 = " + price + "\n" +
                "折扣 = " + discount + "\n" +
                "出货价 = " + outPrice;
    }

    @Override
    public String toString() {
        return "MeiKe{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", unit='" + unit + '\'' +
                ", patchDate='" + patchDate + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", outPrice='" + outPrice + '\'' +
                '}';
    }
}
