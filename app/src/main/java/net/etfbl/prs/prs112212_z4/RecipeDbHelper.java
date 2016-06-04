package net.etfbl.prs.prs112212_z4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String TABLE_RECEPT_NAME = "RECEPT_TB";
    public static final String COL_ID = "ID";
    public static final String COL_DATE = "DATUM";
    public static final String COL_DESCRIPTION = "NAZIV";
    public static final String COL_INGREDIENTS = "SASTOJCI";
    public static final String COL_PREPARE = "PRIPREMA";
    public static final String COL_SDBM = "SDBM";
    private Context mContext;

    public RecipeDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_RECEPT_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY," + COL_DATE + " TEXT," + COL_DESCRIPTION + " TEXT, " + COL_INGREDIENTS + " TEXT," + COL_PREPARE + " TEXT," + COL_SDBM + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
