package com.udacity.sandwichclub.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public JsonUtils() throws JSONException {


    }

    public static Sandwich parseSandwichJson(String json) {

        try {
        //creating a jason oblect for the imput sting  and parsing it
            JSONObject rObject = new JSONObject(json);
            JSONObject sObj = rObject.getJSONObject("name");
            String mainName = sObj.getString("mainName");
            JSONArray alsoknownas = sObj.getJSONArray("alsoKnownAs");
            String placeOfOrigin = rObject.getString("placeOfOrigin");
            String description = rObject.getString("description");
            String imagePath = rObject.getString("image");

            JSONArray ingredientArray = rObject.getJSONArray("ingredients");
            List<String> alsoKnownAsList = new ArrayList<>();

            for (int i = 0; i < alsoknownas.length(); i++) {
                String alsoKnownAs = alsoknownas.getString(i);
                alsoKnownAsList.add(alsoKnownAs);
            }

            List<String> ingredientList = new ArrayList<>();
            for (int i = 0; i < ingredientArray.length(); i++) {
                ingredientList.add(ingredientArray.getString(i));
            }

            Sandwich sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, imagePath, ingredientList);
            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}