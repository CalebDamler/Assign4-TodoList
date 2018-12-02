package edu.niu.cs.caleb.assign4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Caleb on 5/2/2017.
 */
public class DataBaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "toDodb";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_TODO = "toDo", ID = "_id", NAME = "name";

    public DataBaseManager(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_TODO + "( " + ID + " integer primary key autoincrement, " + NAME + " text " + " )";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_TODO);
        onCreate(db);
    }

    public void insert(ToDo toDo){
        SQLiteDatabase database = getWritableDatabase();

        String sqlInsert = "insert into " + TABLE_TODO + " values( null, '" + toDo.getName() + "' )";
        database.execSQL(sqlInsert);
        database.close();

    }

    public void deleteByID(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_TODO + " where " + ID + " = " + id;
        database.execSQL(sqlDelete);
        database.close();
    }

    public void updateByID(int id, String name){
        String sqlUpdate = "update " + TABLE_TODO + " set " + NAME + " = " + name + " where " + ID + " = " + id;
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sqlUpdate);
        database.close();
    }

    public ArrayList<ToDo> selectAll (){
        String sqlSelect = "select * from " + TABLE_TODO;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(sqlSelect, null);
        ArrayList<ToDo> toDos = new ArrayList<>();
        while (cursor.moveToNext()){
            ToDo currentToDo = new ToDo(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
            toDos.add(currentToDo);
        }
        database.close();
        return toDos;
    }

    public ToDo selectById(int id){
        String sqlSelect = "select * from " + TABLE_TODO + " where " + ID + " = " + id;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(sqlSelect, null);
        ToDo toDo = null;
        if (cursor.moveToFirst()){
            toDo = new ToDo(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        }
        database.close();
        return toDo;
    }


}
