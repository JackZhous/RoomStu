package com.jz.room.room.dao;

import com.jz.room.room.table.MeiKe;
import com.jz.room.room.table.PerfectDiary;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * @author jackzhous
 * @package com.jz.room.room.dao
 * @filename MeiKeDao
 * date on 2020/10/26 4:23 PM
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
@Dao
public interface MeiKeDao {
    @Query("SELECT * FROM meike")
    List<MeiKe> queryAllDiary();

    @Query("SELECT * FROM meike where name like :name")
    List<MeiKe> queryDiary(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insetDiary(MeiKe diary);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insetDiary(List<MeiKe> list);


    /**
     * 删除原理是根据主键来删除的，所以千万要保证主键正确
     * @param user
     */
    @Delete
    void deleteDiary(MeiKe user);

    @Query("delete from meike")
    void deleteAll();
}
