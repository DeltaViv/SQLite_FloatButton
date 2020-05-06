package com.example.maibenben.sqlitetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dpHelper.MyDbHelper;

public class MainActivity extends AppCompatActivity {
    public static StringBuilder result = new StringBuilder("程序的运行结果：");

    SQLiteDatabase db;
    Cursor cursor;
//    private static final String TAG = contactFragment.class.getSimpleName();

    private List<String> mList = new ArrayList<>();
    private List<GroupDataBean> mDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView;
        MyDbHelper myhelper = new MyDbHelper(this);
        db = myhelper.getWritableDatabase();

        cursor = db.rawQuery("select * from person", null);
        while (cursor.moveToNext()){
            GroupDataBean bean = new GroupDataBean();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String imgurl = cursor.getString(cursor.getColumnIndex("imgurl"));

            bean.setTeam(name);
            bean.setArea(type);
            bean.setimageid(getimages(imgurl));
            mDataList.add(bean);
            this.result.append(name);
        }
        Log.d("test",this.result.toString()+cursor.getCount());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_03);
        LinearLayoutManager manager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        GroupAdapter adapter = new GroupAdapter(mDataList);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RelativeLayout reLayout =(RelativeLayout) findViewById(R.id.recycler_view_03);

                final EditText nameV = (EditText)findViewById(R.id.name);
                final EditText typeV = (EditText) findViewById(R.id.type);
                final Button btnV = (Button) findViewById(R.id.button);
                recyclerView.setVisibility(View.GONE);
                nameV.setVisibility(View.VISIBLE);
                typeV.setVisibility(View.VISIBLE);
                btnV.setVisibility(View.VISIBLE);
                //监听button事件
                btnV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentValues values = new ContentValues();
                        values.put("name", String.valueOf(nameV.getText()));
                        values.put("type", String.valueOf(typeV.getText()));
                        values.put("imgurl", String.valueOf("l9"));
                        long rowid=db.insert("person", null, values);
                        if(rowid==-1)
                            Log.i("myDbDemo", "数据插入失败！");
                        else
                            Log.i("myDbDemo", "数据插入成功！"+rowid);
                        nameV.setVisibility(View.INVISIBLE);
                        typeV.setVisibility(View.INVISIBLE);
                        btnV.setVisibility(View.INVISIBLE);
                        mDataList.clear();
                        cursor = db.rawQuery("select * from person", null);
                        while (cursor.moveToNext()){
                            GroupDataBean bean = new GroupDataBean();
                            int id = cursor.getInt(0);
                            String name = cursor.getString(1);
                            String type = cursor.getString(cursor.getColumnIndex("type"));
                            String imgurl = cursor.getString(cursor.getColumnIndex("imgurl"));

                            bean.setTeam(name);
                            bean.setArea(type);
                            bean.setimageid(getimages(imgurl));
                            mDataList.add(bean);
                        }

                        LinearLayoutManager manager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
                        recyclerView.setLayoutManager(manager);
                        GroupAdapter adapter = new GroupAdapter(mDataList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setVisibility(View.VISIBLE);
//                        Toast tot = Toast.makeText(
//                                MainActivity.this,
//                                "匿名内部类实现button点击事件",
//                                Toast.LENGTH_LONG);
//                        tot.show();
                    }
                });
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    public static int getimages(String name){
        Class drawable = R.drawable.class;
        Field field = null;
        try {
            field =drawable.getField(name);
            int images = field.getInt(field.getName());
            return images;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
