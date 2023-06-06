package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.apache.commons.dbutils.DbUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MySQlite extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;
    public MySQlite(@Nullable Context context) {
        super(context, "NoteBook", null, 1);
        sqLiteDatabase=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE NoteBook ("
                + "id  INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "content text,"
                + "time text)");

    }
    public boolean insertData(String userContent,String userTime){
        ContentValues contentValues=new ContentValues();
        contentValues.put("content",userContent);
        contentValues.put("time",userTime);
        return sqLiteDatabase.insert("NoteBook",null,contentValues)>0;
    }


    // 修改数据
    public boolean updateData(String id,String content,String userYear){
        ContentValues contentValues=new ContentValues();
        contentValues.put("content",content);
        contentValues.put("time",userYear);
        String sql= "id=?";
        String[] strings=new String[]{id};
        return sqLiteDatabase.update("NoteBook",contentValues,sql,strings)>0;
    }
///删除数据
    public boolean deleteData(String id){
        String sql="id=?";
        String[] contentValuesArray=new String[]{String.valueOf(id)};
        return sqLiteDatabase.delete("NoteBook",sql,contentValuesArray)>0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



//查询数据
    @SuppressLint("Range")
    public List<NotepadBean> query(){
        List<NotepadBean> list=new ArrayList<NotepadBean>();
        Cursor cursor=sqLiteDatabase.query("NoteBook",null,null,
                null,null,null,"id desc");
        if (cursor!=null){
            while (cursor.moveToNext()){
                NotepadBean noteInfo=new NotepadBean();
                String id=String.valueOf(cursor.getInt(cursor.getColumnIndex("id")));
                String content=cursor.getString(cursor.getColumnIndex("content"));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                noteInfo.setId(id);
                noteInfo.setNotepadContent(content);
                noteInfo.setNotepadTime(time);
                list.add(noteInfo);
            }
            cursor.close();
        }
        return list;
    }

    // 返回时间
    public String get_time() {
        Date date = new Date(); SimpleDateFormat dateFormat= new  SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(date).toString();
    }
}
