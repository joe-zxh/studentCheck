package com.example.studentcheck;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CHECK_LISTINFO_EXISTS = "SELECT count(*) FROM sqlite_master WHERE type=\"table\" AND name = \"ListInfo\"";

    public static final String CREATE_LISTINFO = "Create table ListInfo("
            + "id integer primary key autoincrement,"
            + "listName text)";

    public static final String CREATE_STUDENT = "Create table Student("
            + "id integer primary key autoincrement,"
            + "studentName text,"
            + "listID integer)";

    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (!sqlTableIsExist(db, "ListInfo")) {
            db.execSQL(CREATE_LISTINFO);
            db.execSQL(CREATE_STUDENT);
            // insertTestData(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void insertTestData(SQLiteDatabase db) {
        String INSERT_LIST = "INSERT INTO ListInfo (id, listName)" +
                "VALUES (1, '初一1班')";

        String INSERT_STUDENT1 = "INSERT INTO Student (studentName, listID)" +
                "VALUES ('邹秀鸿', 1)";
        String INSERT_STUDENT2 = "INSERT INTO Student (listName)" +
                "VALUES ('钟绮晴', 1)";
        String INSERT_STUDENT3 = "INSERT INTO Student (listName)" +
                "VALUES ('罗结老师', 1)";

        db.execSQL(INSERT_LIST);
        db.execSQL(INSERT_STUDENT1);
        db.execSQL(INSERT_STUDENT2);
        db.execSQL(INSERT_STUDENT3);
    }

    private boolean sqlTableIsExist(SQLiteDatabase db, String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='" + tableName.trim() + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }
}
