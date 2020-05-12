package com.jz.room.room;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * @author jackzhous
 * @package com.jz.room.room
 * @filename MyRoom
 * date on 2020/5/12 2:07 PM
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
@Database(entities = {User.class, Stu.class}, version = 4)
public abstract class MyRoom extends RoomDatabase {
    public abstract UserDao userDao();


    private static MyRoom instance;


    public static MyRoom getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, MyRoom.class, "my_SQLite")
//                    .enableMultiInstanceInvalidation() 可以存着多个room实例，但是其中一个变动后，其他的也会同步
                    .allowMainThreadQueries()
                    .addMigrations(migration1_2, migration2_3, migration3_4)
                    .build();;
        }
        return instance;
    }




    /**
     * 数据库表结构变化，这里是注明表结构发生了怎么样的变化
     */
    static final Migration migration1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table user add column uid int not null default 0");
        }
    };

    static final Migration migration2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE stu (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `score` INTEGER NOT NULL)");
        }
    };

    static final Migration migration3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("insert into stu(name, score) values ('hhhh', 100)");
            database.execSQL("insert into stu(name, score) values ('mumu', 99)");
            database.execSQL("insert into stu(name, score) values ('aaaa', 10)");
            database.execSQL("insert into stu(name, score) values ('bbbb', 40)");
        }
    };
}
