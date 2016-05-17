package com.doperecipes.cs146.recipeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.io.Serializable;


public class MainActivity extends AppCompatActivity {
    private RecipeDataSource myDs;
    //private ListView recipesListView;


    String[] items = new String[4];
    //ArrayList<String> listItems;
    //ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    TextView ingredientsView, title, ingredient, recipe;
    Button mButton;
    String Ing1, Ing2, Ing3, Ing4;

    /* STEVEN */
    Button ingSearch;
    Button titleSearch;
    Button displayAll;
    Button upload;
    RecipeDatabase model = new RecipeDatabase();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /* STEVEN */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* VIDUSHI */
        /**
        ingredientsView = (TextView) findViewById(R.id.txtitem);
        //title = (TextView) findViewById(R.id.textView1);
        //ingredient = (TextView) findViewById(R.id.textView2);
        //recipe = (TextView) findViewById(R.id.textView3);
        if (editText1 != null && editText2!=null && editText3 != null && editText4 != null) {
            Ing1 = editText1.getText().toString();
            Ing2 = editText2.getText().toString();
            Ing3 = editText3.getText().toString();
            Ing4 = editText4.getText().toString();
            items[0] = Ing1;
            items[1] = Ing2;
            items[2] = Ing3;
            items[3] = Ing4;
        }

        //title.setText("1");
        //ingredient.setText("2");
        //recipe.setText("3");



        /* VIDUSHI */
        /*
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, com.killer.recipes.allrecipekillerapp.IngredientSearchActivity.class);
                startActivity(i);
            }
        });
        */
        myDs = new RecipeDataSource();


        /* STEVEN */

        ingSearch =  (Button) findViewById(R.id.button);
        titleSearch = (Button) findViewById(R.id.button4);
        upload = (Button) findViewById(R.id.button2);
        displayAll = (Button) findViewById(R.id.button3);

        ingSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,IngredientSearchActivity.class)); //to be implemented
            }
        });

        titleSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this,IngredientSearch.class));
            }
        });
/*
        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ///startActivity(new Intent(MainActivity.this,IngredientSearch.class));
            }
        });
        */

        displayAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent ls = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(ls);
            }
        });



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        /* STEVEN */

    }


    public void callRecipeView(View view) {
        Intent intent = new Intent(getApplicationContext(), ActivityDisplayRecipe.class);
        Bundle bun = new Bundle();
        bun.putString("title", model.getTitle());
        bun.putString("description", model.getDescription());
        intent.putExtras(bun);
        startActivity(intent);
    }

    /* DEPRECATED METHOD NOT NEEDED ANYMORE
    public void setTextViewMainDatabase(View view) {
        RecipeServiceMAYNOTBEUSED serv = new RecipeServiceMAYNOTBEUSED();
        for (int i = 0; i < 9999; i++) {
            serv.addRecipe(new RecipeServiceNodeMAYNOTBEUSED(new Recipe()));
        }
        //String numberRecipes = String.valueOf((new RecipeServiceMAYNOTBEUSED()).count());

        //TextView tv = (TextView) findViewById(R.id.textView);
        //tv.setText(String.valueOf(serv.getNumRecipes()));
    }*/

    public void setTextViewMainUpload(View view) {
        //TextView tv = (TextView) findViewById(R.id.textView);
        //tv.setText("Upload to database GUI to be implemented.");
    }

    public void listView(View view) {

        Intent intent = new Intent(this, ActivityDisplayRecipe.class);
        startActivity(intent);
    }


    /* STEVEN */
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.killer.recipes.allrecipekillerapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.killer.recipes.allrecipekillerapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
        /* STEVEN */
    }


    /* VIDUSHI */
    public void buttonOnCLick(View v) {
        if (v.getId() == R.id.button) {
            EditText editText1 = (EditText) findViewById(R.id.editText1);
            EditText editText2 = (EditText) findViewById(R.id.editText2);
            EditText editText3 = (EditText) findViewById(R.id.editText3);
            EditText editText4 = (EditText) findViewById(R.id.editText4);

            String IngA = null;
            if (editText1 != null) {
                IngA = editText1.getText().toString();
            }
            String IngB = null;
            if (editText2 != null) {
                IngB = editText2.getText().toString();
            }
            String IngC = null;
            if (editText3 != null) {
                IngC = editText3.getText().toString();
            }
            String IngD = null;
            if (editText4 != null) {
                IngD = editText4.getText().toString();
            }
            Intent i = new Intent(MainActivity.this, com.killer.recipes.allrecipekillerapp.ActivityDisplayRecipe.class);
            i.putExtra("Ingredient1", IngA);
            i.putExtra("Ingredient2", IngB);
            i.putExtra("Ingredient3", IngC);
            i.putExtra("Ingredient4", IngD);
            startActivity(i);
        }

    }
    /* VIDUSHI */

}

/**
 * Vidushi commented this portion on her files out. Including it in here just in case they're needed.
 */
      /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
*/