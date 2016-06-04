package net.etfbl.prs.prs112212_z4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
    public static final String ACTION_NEW_RECIPE = "ACTION_NEW_RECIPE";
    public static final int REQUEST_CODE = 1;
    public static final String ACTION_UPDATE_RECIPE = "ACTION_UPDATE_RECIPE";
    public static final String EXTRA_RECIPE = "EXTRA_RECIPE";
    private static final int REQUEST_CODE_UPDATE = 2;
    private RecipeAdapter adapter;
    private RecipeDbHelper helper;
    private FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new RecipeAdapter(this);
        ListView listView = (ListView) findViewById(R.id.list);
        if (listView != null) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    RecipeDTO recipeDTO = (RecipeDTO) adapter.getItem(position);
                    Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                    intent.setAction(ACTION_UPDATE_RECIPE);
                    intent.putExtra(EXTRA_RECIPE, recipeDTO);
                    startActivityForResult(intent, REQUEST_CODE_UPDATE);

                }
            });
        }
        button = (FloatingActionButton) findViewById(R.id.newRecipe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                intent.setAction(ACTION_NEW_RECIPE);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        listView.setAdapter(adapter);
        helper = new RecipeDbHelper(this);
        updateList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateList();
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {

        }
    }

    private void updateList() {
        adapter.setList(helper.getAll());
    }
}
