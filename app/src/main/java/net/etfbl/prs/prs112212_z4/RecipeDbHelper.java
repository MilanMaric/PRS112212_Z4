package net.etfbl.prs.prs112212_z4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
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

    public List<RecipeDTO> getAll() {
        List<RecipeDTO> list = new ArrayList<>();
        SQLiteDatabase db = getDb();
        Cursor cursor = db.query(TABLE_RECIPE, null, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            list.add(cursor2Recipe(cursor));
        }
        return list;
    }

    public RecipeDTO insert(RecipeDTO recipeDTO) {
        SQLiteDatabase db = getDb();
        ContentValues values = recipe2ContentValues(recipeDTO);
        Log.d(TAG, "Insert: " + recipeDTO);
        recipeDTO.setId((int) db.insert(TABLE_RECIPE, null, values));
        return recipeDTO;
    }

    public RecipeDTO update(RecipeDTO recipeDTO) {
        SQLiteDatabase db = getDb();
        ContentValues values = recipe2ContentValues(recipeDTO);
        db.update(TABLE_RECIPE, values, " id = ?", new String[]{Long.toString(recipeDTO.getId())});
        return recipeDTO;
    }


    private RecipeDTO cursor2Recipe(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
        String date = cursor.getString(cursor.getColumnIndex(COL_DATE));
        int duration = cursor.getInt(cursor.getColumnIndex(COL_DURATION));
        String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
        String preparation = cursor.getString(cursor.getColumnIndex(COL_PREPARE));
        String ingredients = cursor.getString(cursor.getColumnIndex(COL_INGREDIENTS));
        String sdbm = cursor.getString(cursor.getColumnIndex(COL_SDBM));
        RecipeDTO recipeDTO = new RecipeDTO(id, date, duration, name, preparation, ingredients, sdbm);
        Log.d(TAG, "cursor2Recipe: " + recipeDTO);
        return recipeDTO;
    }

    private ContentValues recipe2ContentValues(RecipeDTO recipeDTO) {
        ContentValues values = new ContentValues();
        values.put(COL_ID, recipeDTO.getId());
        values.put(COL_DATE, recipeDTO.getDate());
        values.put(COL_DURATION, recipeDTO.getDuration());
        values.put(COL_INGREDIENTS, recipeDTO.getIngredients());
        values.put(COL_PREPARE, recipeDTO.getPrepare());
        values.put(COL_SDBM, recipeDTO.getSdbm());
        return values;
    }


}
