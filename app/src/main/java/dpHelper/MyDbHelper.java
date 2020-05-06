package dpHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maibenben.sqlitetest.MainActivity;

/**
 * Created by MAIBENBEN on 2020/5/6.
 */
public class MyDbHelper extends SQLiteOpenHelper {
    public MyDbHelper(Context context) {
        super(context, "test5.db", null, 1);
        MainActivity.result.append("创建打开库");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists person");
        db.execSQL("CREATE TABLE person(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),type VARCHAR(20),imgurl VARCHAR(20))");
        db.execSQL("insert into person values(null,'lxw的爸爸','家人','fish')");
        db.execSQL("insert into person values(null,'lxw的妈妈','家人','queen')");
        db.execSQL("insert into person values(null,'Delta','同学','l4')");
        db.execSQL("insert into person values(null,'DongDong','同学','l5')");
        db.execSQL("insert into person values(null,'栗子','同学','l1')");
        db.execSQL("insert into person values(null,'小明','朋友','l2')");
        MainActivity.result.append("创建打开表");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
