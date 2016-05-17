package com.doperecipes.cs146.recipeapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Christopher on 4/18/2016.
 */
public class Recipe {
    public static final String TAG = "MyCookbook";

    public String titleText, summaryText, recipeImage, ingredientsText;




    public static class RecipeStep {
        /**
         * Constructor
         */
        RecipeStep() {}
        public String stepImage;
        public String stepText;

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_STEP_TEXT, stepText);
            bundle.putString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_STEP_IMAGE, stepImage);
            return bundle;
        }

        public static RecipeStep fromBundle(Bundle bundle) {
            RecipeStep recipeStep = new RecipeStep();
            recipeStep.stepText = bundle.getString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_STEP_TEXT);
            recipeStep.stepImage = bundle.getString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_STEP_IMAGE);
            return recipeStep;
        }
    }

    ArrayList<RecipeStep> recipeSteps;

    public Recipe() {
        recipeSteps = new ArrayList<RecipeStep>();
        this.titleText = "";
    }

    public static Recipe fromJson(Context context, JSONObject json) {
        Recipe recipe = new Recipe();
        try {
            recipe.titleText = json.getString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_TITLE);
            recipe.summaryText = json.getString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_SUMMARY);
            if (json.has(ConstantsMAYNOTBEUSED.RECIPE_FIELD_IMAGE)) {
                recipe.recipeImage = json.getString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_IMAGE);
            }
            JSONArray ingredients = json.getJSONArray(ConstantsMAYNOTBEUSED.RECIPE_FIELD_INGREDIENTS);
            recipe.ingredientsText = "";
            /*
            for (int i = 0; i < ingredientMAYNOTBEUSEDs.length(); i++) {
                recipe.ingredientsText += " - "
                        + ingredientMAYNOTBEUSEDs.getJSONObject(i).toString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_TEXT) + "\n";
            }
            */
            JSONArray steps = json.getJSONArray(ConstantsMAYNOTBEUSED.RECIPE_FIELD_STEPS);
            for (int i = 0; i < steps.length(); i++) {
                JSONObject step = steps.getJSONObject(i);
                RecipeStep recipeStep = new RecipeStep();
                recipeStep.stepText = step.getString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_TEXT);
                if (step.has(ConstantsMAYNOTBEUSED. RECIPE_FIELD_IMAGE)) {
                    recipeStep.stepImage = step.getString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_IMAGE);
                }
                recipe.recipeSteps.add(recipeStep);
            }
        }
        catch (JSONException e) {
            Log.e(TAG, "Error loading recipe: " + e);
            return null;
        }
        return recipe;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_TITLE, titleText);
        bundle.putString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_SUMMARY, summaryText);
        bundle.putString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_IMAGE, recipeImage);
        bundle.putString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_INGREDIENTS, ingredientsText);

        if (recipeSteps != null) {
            ArrayList<Parcelable> stepBundles = new ArrayList<Parcelable>(recipeSteps.size());
            for (RecipeStep recipeStep : recipeSteps) {
                stepBundles.add(recipeStep.toBundle());
            }
            bundle.putParcelableArrayList(ConstantsMAYNOTBEUSED.RECIPE_FIELD_STEPS, stepBundles);
        }
        return bundle;
    }

    public static Recipe fromBundle(Bundle bundle) {
        Recipe recipe = new Recipe();
        recipe.titleText = bundle.getString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_TITLE);
        recipe.summaryText = bundle.getString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_SUMMARY);
        recipe.recipeImage = bundle.getString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_IMAGE);
        recipe.ingredientsText = bundle.getString(ConstantsMAYNOTBEUSED.RECIPE_FIELD_INGREDIENTS);
        ArrayList<Parcelable> stepBundles =
                bundle.getParcelableArrayList(ConstantsMAYNOTBEUSED.RECIPE_FIELD_STEPS);
        if (stepBundles != null) {
            for (Parcelable stepBundle : stepBundles) {
                recipe.recipeSteps.add(RecipeStep.fromBundle((Bundle) stepBundle));
            }
        }
        return recipe;
    }
    public String getName() {
        return this.titleText;
    }
}
