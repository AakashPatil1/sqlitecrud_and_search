package com.aakash.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class myDataBase extends SQLiteOpenHelper {

    private static final String DataBase_Name="Student_info.db";
    private static final int DataBase_Version=2;

    private static final String Table_Student = "tblStudent";
    public static String Key_RowID="key_id";
    public static final String Key_First_Name="firstName";
    public static final String Key_Last_Name="lastName";
    public static final String Key_Roll_No="rollNo";
    SQLiteDatabase sqLiteDatabase;

    public myDataBase( Context context) {
        super(context,DataBase_Name,null,DataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Create_Student_Table=" Create TABLE " + Table_Student + "(" + Key_RowID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                Key_Roll_No + " TEXT," + Key_First_Name + " TEXT," + Key_Last_Name + " TEXT); ";
        sqLiteDatabase.execSQL(Create_Student_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" Drop TABLE IF EXISTS " + Table_Student);
        onCreate(sqLiteDatabase);
    }

    public long SaveData(String roll,String first, String last) {
        sqLiteDatabase =this.getWritableDatabase();//writeable
        ContentValues cv = new ContentValues();
        cv.put(Key_Roll_No,roll);
        cv.put(Key_First_Name,first);
        cv.put(Key_Last_Name,last);

        return sqLiteDatabase.insert(Table_Student,null,cv);
    }

    public String getData() {
        sqLiteDatabase =this.getReadableDatabase();
        String columnArray[]=new String[]{Key_RowID,Key_Roll_No,Key_First_Name,Key_Last_Name};
        Cursor cursor = sqLiteDatabase.query(Table_Student,columnArray,null,null,null,null,null);
        String result = "";
        int iRow=cursor.getColumnIndex(Key_RowID);
        int iRoll=cursor.getColumnIndex(Key_Roll_No);
        int iFirstName=cursor.getColumnIndex(Key_First_Name);
        int iLastName=cursor.getColumnIndex(Key_Last_Name);
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            result = result + cursor.getString(iRow) + " - " + cursor.getString(iRoll) + " - " + cursor.getString(iFirstName) + " " + cursor.getString(iLastName) + "\n";
        }
        sqLiteDatabase.close();
        return result;
    }

    public String getRollNo(long id) {
        sqLiteDatabase =this.getReadableDatabase();
        String columnArray[]=new String[]{Key_RowID,Key_Roll_No,Key_First_Name,Key_Last_Name};
        Cursor cursor = sqLiteDatabase.query(Table_Student,columnArray,Key_RowID + " = " + id ,null,null,null,null);
        if (cursor!=null)
        {
            cursor.moveToNext();
            String name= cursor.getString(1);
            return name;
        }
        return null;
    }

    public String getFirstName(long id) {
        sqLiteDatabase =this.getReadableDatabase();
        String columnArray[]=new String[]{Key_RowID,Key_Roll_No,Key_First_Name,Key_Last_Name};
        Cursor cursor = sqLiteDatabase.query(Table_Student,columnArray,Key_RowID + " = " + id ,null,null,null,null);
        if (cursor!=null)
        {
            cursor.moveToNext();
            String name= cursor.getString(2);
            return name;
        }
        return null;
    }

    public String getLastName(long id) {
        sqLiteDatabase =this.getReadableDatabase();
        String columnArray[]=new String[]{Key_RowID,Key_Roll_No,Key_First_Name,Key_Last_Name};
        Cursor cursor = sqLiteDatabase.query(Table_Student,columnArray,Key_RowID + " = " + id ,null,null,null,null);
        if (cursor!=null)
        {
            cursor.moveToNext();
            String name= cursor.getString(3);
            return name;
        }
        return null;
    }

    public void UpdateDetail(long id,String roll, String first, String last) {
        sqLiteDatabase =this.getWritableDatabase();//writeable
        ContentValues cv = new ContentValues();
        cv.put(Key_Roll_No,roll);
        cv.put(Key_First_Name,first);
        cv.put(Key_Last_Name,last);
        sqLiteDatabase.update(Table_Student,cv,Key_RowID + " = " + id,null);
        sqLiteDatabase.close();
    }
    public Integer deleteData(Integer id){
        sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(Table_Student,Key_RowID + " = " + id,null );
    }
}
