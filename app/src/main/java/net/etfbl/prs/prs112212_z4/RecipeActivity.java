package net.etfbl.prs.prs112212_z4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity {

    private String action;
    private RecipeDbHelper helper;
    private RecipeDTO recipe;
    private EditText durationEditText;
    private EditText nameEditText;
    private EditText ingredientsEditText;
    private EditText prepartationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        getEditTextRefs();
        Intent intent = getIntent();
        action = intent.getAction();
        if (MainActivity.ACTION_NEW_RECIPE.equals(action)) {
            recipe = new RecipeDTO();
        } else {
            recipe = (RecipeDTO) intent.getSerializableExtra(MainActivity.EXTRA_RECIPE);
            recipe2View();
        }

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.newRecipe);
        helper = new RecipeDbHelper(this);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (action) {
                        case MainActivity.ACTION_NEW_RECIPE:
                            if (view2Recipe()) {
                                helper.insert(recipe);
                                Intent result = new Intent();
                                setResult(Activity.RESULT_OK, result);
                                Toast.makeText(RecipeActivity.this, R.string.created, Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            break;
                        default:
                            if (view2Recipe()) {
                                helper.update(recipe);
                                Intent result = new Intent();
                                setResult(Activity.RESULT_OK, result);
                                finish();
                                Toast.makeText(RecipeActivity.this, R.string.updated, Toast.LENGTH_SHORT).show();
                            }
                            break;

                    }
                }
            });
        }

    }

    private void getEditTextRefs() {
        durationEditText = (EditText) findViewById(R.id.r_duration);
        nameEditText = (EditText) findViewById(R.id.r_name);
        ingredientsEditText = (EditText) findViewById(R.id.r_ingredients);
        prepartationEditText = (EditText) findViewById(R.id.r_prepare);
    }

    private void recipe2View() {
        durationEditText.setText(Integer.toString(recipe.getDuration()));
        nameEditText.setText(recipe.getName());
        ingredientsEditText.setText(recipe.getIngredients());
        prepartationEditText.setText(recipe.getPrepare());
    }

    private boolean view2Recipe() {
        //TODO add validation
        if (valid()) {
            recipe.setDuration(Integer.parseInt(durationEditText.getText().toString()));
            recipe.setName(nameEditText.getText().toString());
            recipe.setIngredients(ingredientsEditText.getText().toString());
            recipe.setPrepare(prepartationEditText.getText().toString());
            return true;
        } else {
            Toast.makeText(this, R.string.not_valid, Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean valid() {

        try {
            if (!checkText(durationEditText.getText().toString()))
                return false;
            if (Integer.parseInt(durationEditText.getText().toString()) < 0)
                return false;
            if (!checkText(nameEditText.getText().toString()))
                return false;
            if (!checkText(ingredientsEditText.getText().toString()))
                return false;
            if (!checkText(prepartationEditText.getText().toString()))
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean checkText(String text) {
        return text != null && !"".equals(text);
    }


}
