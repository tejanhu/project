package hussein.com.swype.db;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hussein.com.swype.pojo.SwypeImage;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "swype.db";
    // Swype table name
    private static final String TABLE_IMAGES = "swype_images";
    // Swype Images Table Columns names:liked and image_uri
    private static final String KEY_LIKED = "liked";
    private static final String KEY_SH_URI = "image_uri";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_IMAGES + "("
                + KEY_SH_URI + " TEXT PRIMARY KEY ,"
                + KEY_LIKED + " BOOL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);
        // Creating tables again
        onCreate(db);
    }


    public void addSwypeImages(List<SwypeImage> swypeImages) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for(SwypeImage swypeImage : swypeImages) {
            values.put(KEY_SH_URI, swypeImage.getImageUri().toString());
            values.put(KEY_LIKED, swypeImage.getStatus());
            // Inserting Row
            db.insert(TABLE_IMAGES, null, values);
        }
        db.close(); // Closing database connection
    }


    public void addSwypeImage(SwypeImage swypeImage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SH_URI, swypeImage.getImageUri().toString());
        values.put(KEY_LIKED, swypeImage.getStatus());
        // Inserting Row
        db.insert(TABLE_IMAGES, null, values);
        db.close(); // Closing database connection
    }

    // Getting All swypeImages
    public List<SwypeImage> getAllswypeImages() {
        List<SwypeImage> swypeImageList = new ArrayList<SwypeImage>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_IMAGES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SwypeImage swypeImage = new SwypeImage(Uri.parse(Uri.decode(cursor.getString(0))), cursor.getInt(1) == 1);
                // Adding contact to list
                swypeImageList.add(swypeImage);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return swypeImageList;
    }

    //Clean the current storage
    public void cleanAllSwypeImages() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_IMAGES);
        db.close();
    }
}