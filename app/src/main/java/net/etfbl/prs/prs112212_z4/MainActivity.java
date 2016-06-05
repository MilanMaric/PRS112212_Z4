package net.etfbl.prs.prs112212_z4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
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
        final ListView listView = (ListView) findViewById(R.id.list);
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
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                    final int checkedCount = listView.getCheckedItemCount();
                    mode.setTitle(checkedCount + " " + getString(R.string.selected));
                    adapter.toggleSelection(position);
                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.main, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.delete:
                            // Calls getSelectedIds method from ListViewAdapter Class
                            SparseBooleanArray selected = adapter
                                    .getSelectedIds();
                            // Captures all selected ids with a loop
                            for (int i = (selected.size() - 1); i >= 0; i--) {
                                if (selected.valueAt(i)) {
                                    RecipeDTO selecteditem = (RecipeDTO) adapter.getItem(selected.keyAt(i));
                                    // Remove selected items following the ids
                                    helper.delete(selecteditem);
                                }
                            }
                            // Close CAB
                            updateList();
                            adapter.notifyDataSetChanged();
                            mode.finish();
                            return true;
                        default:
                            return false;
                    }
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    adapter.removeSelection();
                }
            });

            listView.setAdapter(adapter);
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
