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

            JSONObject rootObject = new JSONObject(json);
            JSONObject subObj = rootObject.getJSONObject("name");
            String mainName = subObj.getString("mainName");
            JSONArray aka = subObj.getJSONArray("alsoKnownAs");
            String placeOfOrigin = rootObject.getString("placeOfOrigin");
            String description = rootObject.getString("description");
            String imagePath = rootObject.getString("image");

            JSONArray ingredientArray = rootObject.getJSONArray("ingredients");
            List<String> alsoKnownAsList = new ArrayList<>();

            for (int i = 0; i < aka.length(); i++) {
                String alsoKnownAs = aka.getString(i);
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