package net.etfbl.prs.prs112212_z4;

import java.io.Serializable;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.prs.prs112212_z4 RecipeDTO
 * \brief
 * This file contains a source code for class TaskAdapter
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
public class RecipeDTO implements Serializable {
    private int id;
    private String date;
    private int duration;
    private String name;
    private String ingredients;
    private String prepare;
    private String sdbm;

    public RecipeDTO() {

    }

    public RecipeDTO(int duration, String name, String ingredients, String prepare) {
        this.duration = duration;
        this.name = name;
        this.ingredients = ingredients;
        this.prepare = prepare;
    }

    public RecipeDTO(String date, int duration, String name, String ingredients, String prepare, String sdbm) {
        this.date = date;
        this.duration = duration;
        this.name = name;
        this.ingredients = ingredients;
        this.prepare = prepare;
        this.sdbm = sdbm;
    }

    public RecipeDTO(int id, String date, int duration, String name, String ingredients, String prepare, String sdbm) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.name = name;
        this.ingredients = ingredients;
        this.prepare = prepare;
        this.sdbm = sdbm;
    }

    public String getSdbm() {
        return sdbm;
    }

    public void setSdbm(String sdbm) {
        this.sdbm = sdbm;
    }

    public String getPrepare() {
        return prepare;
    }

    public void setPrepare(String prepare) {
        this.prepare = prepare;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{id: " + id
                + ", date: " + date
                + ", duration:" + duration
                + ", name:" + name
                + ", ingredients:" + ingredients
                + ", preparation:" + prepare
                + ", sdbm" + sdbm + "}";
    }
}
