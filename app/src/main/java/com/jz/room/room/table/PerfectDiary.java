package com.jz.room.room.table;

import com.jz.room.adapter.Result;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author jackzhous
 * @package com.jz.room.room.table
 * @filename PerfectDiary
 * date on 2020/10/26 9:56 AM
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
@Entity
public class PerfectDiary implements Result {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String productName;
    @ColumnInfo(name = "code")
    public String barCode;
    @ColumnInfo(name ="price")
    public String price;
    @ColumnInfo(name = "dis_price")
    public String discount;
    @ColumnInfo(name = "vip_price")
    public String vipP;
    @ColumnInfo(name = "sv_dis_price")
    public String sDiscount;
    @ColumnInfo(name = "svip_price")
    public String svipP;
    @ColumnInfo(name = "remark")
    public String remark;


    public PerfectDiary(String productName, String barCode, String price, String discount, String vipP, String sDiscount, String svipP) {
        this.productName = productName;
        this.barCode = barCode;
        this.price = price;
        this.discount = discount;
        this.vipP = vipP;
        this.sDiscount = sDiscount;
        this.svipP = svipP;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
                "VIP价格 = " + vipP + "\n" +
                "SVIP折扣 = " + sDiscount + "\n" +
                "SVIP价格 = " + svipP + "\n" +
                (remark != null ? "备注 = " + remark : "");
    }

    @Override
    public String toString() {
        return  "商品名称 = " + productName + "\n" +
                "条形码 = " + barCode + "\n" +
                "价格 = " + price + "\n" +
                "折扣 = " + discount + "\n" +
                "VIP价格 = " + vipP + "\n" +
                "SVIP折扣 = " + sDiscount + "\n" +
                "SVIP价格 =" + svipP + "\n" +
                "备注 =" + remark + "\n";
    }
}
