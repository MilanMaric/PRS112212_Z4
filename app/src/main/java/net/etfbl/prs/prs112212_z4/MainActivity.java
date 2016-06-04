package net.etfbl.prs.prs112212_z4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.prs.prs112212_z4.MainActivity.java
 * \brief
 * This file contains a source code for class MainActivity
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
public class MainActivity extends AppCompatActivity {
    private RecipeAdapter adapter;
    private RecipeDbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new RecipeAdapter(this);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        helper = new RecipeDbHelper(this);
        updateList();
    }

    private void updateList() {
        adapter.setList(helper.getAll());
    }
}
