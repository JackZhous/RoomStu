package com.jz.room.room;
import android.content.Context;

import com.jz.room.room.dao.CXCDao;
import com.jz.room.room.dao.MeiKeDao;
import com.jz.room.room.dao.PerfectDiaryDao;
import com.jz.room.room.table.CXC;
import com.jz.room.room.table.MeiKe;
import com.jz.room.room.table.PerfectDiary;

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
@Database(entities = {PerfectDiary.class, MeiKe.class, CXC.class}, version = 4)
public abstract class MyRoom extends RoomDatabase {
    public abstract PerfectDiaryDao perfectDiaryDao();

    public abstract MeiKeDao meiKeDao();

    public abstract CXCDao cxcDao();


    private static MyRoom instance;

    public static MyRoom getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, MyRoom.class, "priceSQLite")
//                    .enableMultiInstanceInvalidation() 可以存着多个room实例，但是其中一个变动后，其他的也会同步
                    .allowMainThreadQueries()
                    .addMigrations(migration1_2, migration2_3, migration3_4)
                    .build();
        }
        return instance;
    }




    /**
     * 数据库表结构变化，这里是注明表结构发生了怎么样的变化
     */
    static final Migration migration1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table PerfectDiary add column remark varchar(50)");
        }
    };

    static final Migration migration2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS meike (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `unit` TEXT, `patchDate` TEXT, `price` TEXT, `discount` TEXT, `outPrice` TEXT)");
        }
    };

    static final Migration migration3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS cxc (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `code` TEXT, `unit` TEXT, `price` TEXT, `discount` TEXT, `disPrice` TEXT, `unit_helper` TEXT , `remark` TEXT)");
        }
    };
}
