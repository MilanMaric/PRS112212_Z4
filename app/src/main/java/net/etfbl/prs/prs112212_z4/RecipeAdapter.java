package net.etfbl.prs.prs112212_z4;

import android.content.Context;
import android.util.SparseBooleanArray;
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
    private SparseBooleanArray selected;

    public RecipeAdapter(Context context) {
        this.context = context;
        selected = new SparseBooleanArray();
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
        TextView sdbm = (TextView) view.findViewById(R.id.sdbm);

        title.setText(recipe.getName());
        ingredients.setText(recipe.getIngredients());
        preparation.setText(recipe.getPrepare());
        date.setText(recipe.getDate());
        sdbm.setText(recipe.getSdbm());
        return view;
    }

    public void setList(List<RecipeDTO> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !selected.get(position));
    }

    public void removeSelection() {
        selected = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            selected.put(position, value);
        else
            selected.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return selected.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return selected;
    }
}
