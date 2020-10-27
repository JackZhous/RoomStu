package com.jz.room.room.dao;

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
 * @filename PerfectDiaryDao
 * date on 2020/10/26 10:04 AM
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
@Dao
public interface PerfectDiaryDao {
    @Query("SELECT * FROM perfectdiary")
    List<PerfectDiary> queryAllDiary();

    @Query("SELECT * FROM perfectdiary where name like :name")
    List<PerfectDiary> queryDiary(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insetDiary(PerfectDiary diary);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insetDiary(List<PerfectDiary> list);


    /**
     * 删除原理是根据主键来删除的，所以千万要保证主键正确
     * @param user
     */
    @Delete
    void deleteDiary(PerfectDiary user);

    @Query("delete from perfectdiary")
    void deleteAll();
}
