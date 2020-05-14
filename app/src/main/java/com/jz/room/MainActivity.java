package com.jz.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import xc.com.apt.annotation.BindeView;
import xc.com.apt.api.XcInject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jz.room.room.InnerResult;
import com.jz.room.room.MyRoom;
import com.jz.room.room.Stu;
import com.jz.room.room.User;
import com.jz.room.room.UserDao;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserDao dao;
    private static final String TAG = "j_tag";
    @BindeView(R.id.display)
    TextView dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XcInject.bind(this);
        Log.i(TAG, "display is null " + (dis == null));
        dao = MyRoom.getInstance(getApplicationContext()).userDao();
        printInnerResult();
    }


    private void printTable(){
        Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<User> users = dao.queryAllUser();
        for(User u : users){
            Log.i(TAG, u.toString());
        }
        Log.i(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");

    }

    private void printStu(){
        Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<Stu> users = dao.queryAllStu();
        for(Stu u : users){
            Log.i(TAG, u.toString());
        }
        Log.i(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
    }


    private void printInnerResult(){
        Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<InnerResult> users = dao.leftQuery();
        for(InnerResult u : users){
            Log.i(TAG, u.toString());
        }
        Log.i(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");

    }

    private void insertUser(){
        dao.insetUser(new User(1, "jjjj", "woman", 44, "jackzhous"));
    }



    private void deleteUser(User user){
        dao.deleteUser(user);
    }


    private List<User> initUser(){
        List<User> list = new ArrayList<>();
        list.add(new User(2, "aaaa", "woman", 10, "bbbb"));
        list.add(new User(3, "bbbb", "man", 22, "cccc"));
        list.add(new User(4, "dddd", "man", 40, "bbbb"));
        list.add(new User(5, "eeee", "woman", 33, "iiii"));
        list.add(new User(6, "ffff", "unknown", 57, "xxxx"));
        list.add(new User(7, "gggg", "woman", 80, "jackzhous"));
        list.add(new User(8, "hhhh", "man", 100, "ffff"));
        list.add(new User(9, "iiii", "woman", 18, "iiii"));

        return list;
    }
}
