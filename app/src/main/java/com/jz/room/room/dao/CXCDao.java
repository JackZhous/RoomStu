package com.jz.room.room.dao;

import com.jz.room.room.table.CXC;
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
 * @filename CXCDao
 * date on 2020/10/26 5:47 PM
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
@Dao
public interface CXCDao {
    @Query("SELECT * FROM cxc")
    List<CXC> queryAllDiary();

    @Query("SELECT * FROM cxc where name like :name")
    List<CXC> queryDiary(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insetDiary(CXC diary);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insetDiary(List<CXC> list);


    /**
     * 删除原理是根据主键来删除的，所以千万要保证主键正确
     * @param user
     */
    @Delete
    void deleteDiary(CXC user);

    @Query("delete from cxc")
    void deleteAll();
}
