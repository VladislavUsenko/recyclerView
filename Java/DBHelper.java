package example.om.testrecyctwo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class DBHelper extends SQLiteOpenHelper {

    //DataBase value
    private static final String DB_NAME = "myDB";
    private static final int DB_VERSION = 1;

    //Table value
    private static final String TABLE_NAME = "mytable";
    private static final String COLUMN_1 = "id";
    private static final String COLUMN_2 = "name";
    private static final String COLUMN_3 = "email";

    // constructor
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //create table and insert data
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " ("
                + COLUMN_1 + " integer primary key autoincrement,"
                + COLUMN_2 +" text,"
                + COLUMN_3 +" text" + ");");

        insertData("test1", "test1@mail.com");
        insertData("test2", "test2@mail.com");
        insertData("test3", "test3@mail.com");
        insertData("test4", "test4@mail.com");
        insertData("test5", "test5@mail.com");

    }
    //if your update version database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }

    //method for insert data
    public boolean insertData(String name, String email){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("email", email);

        long rowID = db.insert(TABLE_NAME, null, cv);
        db.close();
        return !(rowID == -1);
    }


    //method select row or table
    public String selectRow(int i){

        String str = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM mytable WHERE id = ?",
                                new String[] {Integer.toString(i)});
        if (cursor.moveToFirst()){
            str = cursor.getString(1);
        }
        return str;
    }

    //method select number of lines
    public int countLines(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                null, null, null, null, null, null);

        return cursor.getCount();
    }

    //method delete data
    public int deleteAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        int clearCount = db.delete(TABLE_NAME, null, null);
        db.close();

        return  clearCount;
    }
}
