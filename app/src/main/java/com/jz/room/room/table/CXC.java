package com.jz.room.room.table;

import com.jz.room.adapter.Result;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author jackzhous
 * @package com.jz.room.room.table
 * @filename CXC
 * date on 2020/10/26 5:45 PM
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
@Entity
public class CXC implements Result {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String productName;
    @ColumnInfo(name = "code")
    public String barCode;
    @ColumnInfo(name = "unit")
    public String unit;
    @ColumnInfo(name ="price")
    public String price;
    @ColumnInfo(name = "discount")
    public String discount;
    @ColumnInfo(name = "disPrice")
    public String disPirce;
    @ColumnInfo(name = "unit_helper")
    public String unitHelper;
    @ColumnInfo(name = "remark")
    public String remark;

    public CXC(String productName, String barCode, String unit, String price, String discount, String disPirce, String unitHelper) {
        this.productName = productName;
        this.barCode = barCode;
        this.unit = unit;
        this.price = price;
        this.discount = discount;
        this.disPirce = disPirce;
        this.unitHelper = unitHelper;
    }

    @Override
    public String getName() {
        return productName;
    }

    @Override
    public String getDetail() {
        return "条形码 = " + barCode + "\n" +
                "价格 = " + price + "\n" +
                "折扣 = " + discount + "\n" +
                "折后价格 = " + disPirce + "\n" +
                "单位 = " + unit + "\n" +
                "辅助单位 = " + unitHelper + "\n" +
                (remark != null ? "备注 = " + remark : "");
    }

    @Override
    public String toString() {
        return "CXC{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", barCode='" + barCode + '\'' +
                ", unit='" + unit + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", disPirce='" + disPirce + '\'' +
                ", unitHelper='" + unitHelper + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
