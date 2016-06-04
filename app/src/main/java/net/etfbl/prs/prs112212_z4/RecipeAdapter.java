package net.etfbl.prs.prs112212_z4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.prs.prs112212_z4 RecipeAdapter
 * \brief
 * This file contains a source code for class RecipeAdapter
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
public class RecipeAdapter extends BaseAdapter {
    private final Context context;
    private List<RecipeDTO> list = new ArrayList<>();

    public RecipeAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.recipe_layout, null);
        }
        RecipeDTO recipe = (RecipeDTO) getItem(position);
        TextView title = (TextView) view.findViewById(R.id.name);
        TextView ingredients = (TextView) view.findViewById(R.id.ingridients);
        TextView preparation = (TextView) view.findViewById(R.id.prepare);
        TextView date = (TextView) view.findViewById(R.id.date);

        title.setText(recipe.getName());
        ingredients.setText(recipe.getIngredients());
        preparation.setText(recipe.getPrepare());
        date.setText(recipe.getDate());
        return view;
    }

    public void setList(List<RecipeDTO> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
