package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;


public class DetailActivity extends AppCompatActivity {

    private TextView textViewKnown;
    private TextView textViewIngredients;
    private TextView textViewOrigin;
    private TextView textViewDescription;
    private TextView textViewKnownTitle;
    private TextView textViewOriginTile;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        ImageView ingredientsIv = findViewById(R.id.image_iv);
        textViewKnown = (TextView) findViewById(R.id.textView_known);
        textViewIngredients = (TextView) findViewById(R.id.textView_ingredients);
        textViewOrigin = (TextView) findViewById(R.id.textView_origin);
        textViewOriginTile =(TextView) findViewById(R.id.textView_origin_Title);
        textViewDescription = (TextView) findViewById(R.id.textView_description);
        textViewKnownTitle = (TextView) findViewById(R.id.textView_knownT);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        if(sandwich.getPlaceOfOrigin().isEmpty())
        {
            //data for place of origin not avilable  then it will remove the views
            textViewOrigin.setVisibility(View.GONE);
            textViewOriginTile.setVisibility(View.GONE);

        }
        else
        {
            textViewOrigin.setText(sandwich.getPlaceOfOrigin());
        }
        textViewDescription.setText(sandwich.getDescription());

        if (sandwich.getAlsoKnownAs().size() > 0) {

            for (int i = 0; i < sandwich.getAlsoKnownAs().size(); i++) {
                textViewKnown.append(sandwich.getAlsoKnownAs().get(i) + "\n");
            }

        } else {
            //data for also known as  not avilable  then it will remove the views
            textViewKnown.setVisibility(View.GONE);
            textViewKnownTitle.setVisibility(View.GONE);

        }

        if (sandwich.getIngredients().size() > 0) {

            for (int i = 0; i < sandwich.getIngredients().size(); i++) {
                textViewIngredients.append(sandwich.getIngredients().get(i) + "\n");
            }

        } else {
            textViewIngredients.setText("...");
        }
    }
}
