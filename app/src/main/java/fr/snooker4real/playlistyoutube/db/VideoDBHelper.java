package fr.snooker4real.playlistyoutube.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VideoDBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "Videos.db";

    public static final String VIDEO_KEY = "id";
    public static final String VIDEO_TITLE = "title";
    public static final String VIDEO_DESCRIPTION = "description";
    public static final String VIDEO_URL = "url";
    public static final String VIDEO_CATEGORY = "category";

    public static final String VIDEO_TABLE_NAME = "Video";


    public static final int VIDEO_KEY_COLUMN_INDEX = 0;
    public static final int VIDEO_TITLE_COLUMN_INDEX = 1;
    public static final int VIDEO_DESCRIPTION_COLUMN_INDEX = 2;
    public static final int VIDEO_URL_COLUMN_INDEX = 3;
    public static final int VIDEO_CATEGORY_COLUMN_INDEX = 4;

    private static final String TODO_TABLE_CREATE =
            "CREATE TABLE " + VIDEO_TABLE_NAME + " (" +
                    VIDEO_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VIDEO_TITLE + " TEXT, " +
                    VIDEO_DESCRIPTION + " TEXT, " +
                    VIDEO_URL + " TEXT, " +
                    VIDEO_CATEGORY + " TEXT);";

    private static final String TODO_TABLE_DROP =
            "DROP TABLE IF EXISTS " + VIDEO_TABLE_NAME + ";";

    public VideoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TODO_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TODO_TABLE_DROP);
        onCreate(db);
    }
}
