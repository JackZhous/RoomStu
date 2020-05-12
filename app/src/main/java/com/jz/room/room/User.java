package com.jz.room.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author jackzhous
 * @package com.jz.room.room
 * @filename User
 * date on 2020/5/12 2:24 PM
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
@Entity
public class User {
    @PrimaryKey(autoGenerate=true)
    int id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "sex")
    String sex;
    @ColumnInfo(name = "age")
    int age;
    @ColumnInfo(name = "lover")
    String lover;
    @ColumnInfo(name = "uid")
    int uid;

    public User(int uid, String name, String sex, int age, String lover) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.lover = lover;
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLover(String lover) {
        this.lover = lover;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", lover='" + lover + '\'' +
                ", uid=" + uid +
                '}';
    }
}
