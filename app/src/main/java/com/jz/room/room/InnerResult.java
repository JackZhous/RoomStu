package com.jz.room.room;

import androidx.room.ColumnInfo;

/**
 * @author jackzhous
 * @package com.jz.room.room
 * @filename InnerResult
 * date on 2020/5/12 5:45 PM
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
public class InnerResult {
    @ColumnInfo(name = "id")
    int id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "score")
    int score;
    @ColumnInfo(name = "lover")
    String lover;

    @Override
    public String toString() {
        return "InnerResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", lover='" + lover + '\'' +
                '}';
    }
}
