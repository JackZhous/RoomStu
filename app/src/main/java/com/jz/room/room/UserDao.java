package com.jz.room.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * @author jackzhous
 * @package com.jz.room.room
 * @filename UserDao
 * date on 2020/5/12 2:28 PM
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> queryAllUser();

    @Query("SELECT * FROM user where name = :name")
    User queryUser(String name);

    @Query("SELECT * FROM user where age IN (:agx)")
    List<User> queryUserByAges(int[] agx);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insetUser(User user);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStu(Stu user);

    @Query("SELECT * FROM stu")
    List<Stu> queryAllStu();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUsers(List<User> users);

    @Query("select * from stu inner join user on stu.id = user.id order by score asc")
    List<InnerResult> innerQuery();

    /**
     * 删除原理是根据主键来删除的，所以千万要保证主键正确
     * @param user
     */
    @Delete
    void deleteUser(User user);


    @Update
    void updateUser(User user);
}
