package net.etfbl.prs.prs112212_z4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.prs.prs112212_z4.RecipeDbHelper.java
 * \brief
 * This file contains a source code for class RecipeDbHelper
 * <p/>
 * Created on 04.06.2016
 *
 * @Author Milan Maric
 * <p/>
 * \notes
 * <p/>
 * <p/>
 * \history
 * <p/>
 **********************************************************************/
public class RecipeDbHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "RECEPTI_DB";
    public static final String TABLE_RECIPE = "RECEPT_TB";
    public static final String COL_ID = "ID";
    public static final String COL_DATE = "DATUM";
    public static final String COL_INGREDIENTS = "SASTOJCI";
    public static final String COL_PREPARE = "PRIPREMA";
    public static final String COL_SDBM = "SDBM";
    public static final String COL_DURATION = "VRIJEME_PRIPREME";
    public static final String COL_NAME = "NAME";
    public static final String TAG = "RecipeDBHelper";
    private Context mContext;
    private SQLiteDatabase mDb;

    public RecipeDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_RECIPE + " ("
                + COL_ID + " INTEGER PRIMARY KEY,"
                + COL_DATE + " TEXT, "
                + COL_NAME + " TEXT,"
                + COL_DURATION + " INTEGER,"
                + COL_INGREDIENTS + " TEXT,"
                + COL_PREPARE + " TEXT,"
                + COL_SDBM + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new RuntimeException();
    }

    private SQLiteDatabase getDb() {
        if (mDb == null) {
            mDb = getWritableDatabase();
        }
        return mDb;
    }

    /**
     * This is a wrapper method to get all the recipes
     *
     * @return list of recpies from the database
     */
    public List<RecipeDTO> getAll() {
        List<RecipeDTO> list = new ArrayList<>();
        SQLiteDatabase db = getDb();
        Cursor cursor = db.query(TABLE_RECIPE, null, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            list.add(cursor2Recipe(cursor));
        }
        return list;
    }

    /**
     * This method is wrapper for the db.insert method.
     *
     * @param recipeDTO object that should be inserted into database
     * @return same object with set id
     */
    public RecipeDTO insert(RecipeDTO recipeDTO) {
        SQLiteDatabase db = getDb();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.mm.yyyy hh:mm:ss");
        recipeDTO.setDate(simpleDateFormat.format(new Date()));
        ContentValues values = recipe2ContentValues(recipeDTO, false);
        Log.d(TAG, "Insert: " + recipeDTO);
        recipeDTO.setId((int) db.insert(TABLE_RECIPE, null, values));
        return recipeDTO;
    }

    /**
     * This is a wrapper for db.update
     *
     * @param recipeDTO object
     * @return same object
     */
    public RecipeDTO update(RecipeDTO recipeDTO) {
        SQLiteDatabase db = getDb();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.mm.yyyy hh:mm:ss");
        recipeDTO.setDate(simpleDateFormat.format(new Date()));
        ContentValues values = recipe2ContentValues(recipeDTO, false);
        Log.d(TAG, "Update: " + recipeDTO);
        db.update(TABLE_RECIPE, values, " id = ?", new String[]{Long.toString(recipeDTO.getId())});
        return recipeDTO;
    }

    /**
     * Wrapper for the db.delete
     *
     * @param recipeDTO recipe object
     * @return number of the rows affected
     */
    public int delete(RecipeDTO recipeDTO) {
        SQLiteDatabase db = getDb();
        Log.d(TAG, "Delete: " + recipeDTO);
        return db.delete(TABLE_RECIPE, " id=?", new String[]{Integer.toString(recipeDTO.getId())});
    }

    /**
     * This is a helper method used to convert cursor to recipe
     *
     * @param cursor cursor
     * @return recipe
     */
    private RecipeDTO cursor2Recipe(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
        String date = cursor.getString(cursor.getColumnIndex(COL_DATE));
        int duration = cursor.getInt(cursor.getColumnIndex(COL_DURATION));
        String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
        String preparation = cursor.getString(cursor.getColumnIndex(COL_PREPARE));
        String ingredients = cursor.getString(cursor.getColumnIndex(COL_INGREDIENTS));
        String sdbm = cursor.getString(cursor.getColumnIndex(COL_SDBM));
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(id);
        recipeDTO.setDate(date);
        recipeDTO.setDuration(duration);
        recipeDTO.setName(name);
        recipeDTO.setPrepare(preparation);
        recipeDTO.setIngredients(ingredients);
        recipeDTO.setSdbm(sdbm);
        Log.d(TAG, "cursor2Recipe: " + recipeDTO);
        return recipeDTO;
    }

    /**
     * This method is used to convert recipe to contentValues
     *
     * @param recipeDTO recipe
     * @param update    is it updating, or not
     * @return values
     */
    private ContentValues recipe2ContentValues(RecipeDTO recipeDTO, boolean update) {
        ContentValues values = new ContentValues();
        if (update)
            values.put(COL_ID, recipeDTO.getId());
        values.put(COL_NAME, recipeDTO.getName());
        values.put(COL_DATE, recipeDTO.getDate());
        values.put(COL_DURATION, recipeDTO.getDuration());
        values.put(COL_INGREDIENTS, recipeDTO.getIngredients());
        values.put(COL_PREPARE, recipeDTO.getPrepare());
        String concat = recipeDTO.getIngredients() + recipeDTO.getPrepare();
        Hash hash = new Hash();
        long h = (long) hash.getHash(concat);
        recipeDTO.setSdbm(String.format("%x", h));
        values.put(COL_SDBM, recipeDTO.getSdbm());
        return values;
    }


}
