package scottsalvador.pons.com.sqliteact2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {
    final static String DBName = "Employee.db";
    final static int VER = 1;
    final static String TableName = "scores";

    public Helper(Context context) {
        super(context, DBName, null, VER);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable= " CREATE TABLE scores(ID INTEGER PRIMARY KEY AUTOINCREMENT, Fname TEXT, Lname TEXT, Grade INTEGER) ";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable= " DROP TABLE IF EXISTS scores";
        db.execSQL(dropTable);

    }

    public boolean insert(String fname, String lname, int grade){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Fname",fname);
        cv.put("Lname",lname);
        cv.put("Grade",grade);
        long inserted = db.insert(TableName,null,cv);
        if(inserted == -1)
            return false;
        else
            return true;
    }

    public Cursor populateTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM scores", null);
    }
}
