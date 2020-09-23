package com.crazygaming.staysafe;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;

public class ConsumerOrderActivity extends SQLBActivity
{
    TypedArray categoryImages; // TypedArray declaration
    SQLConnection sqlConnection; // SQLConnection declaration

    protected TextView tvCategory; // TextView tvCategory

    protected String[] unique; // String Array declaration
    protected String action; // String declaration

    protected Button signOut, basket; // Button declaration
    protected String[] names, allergies; // Store Names and Allergies
    protected float[] prices; // Store Prices
    protected int[] calories; // Store Calories

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_order_category);

        categoryImages = getResources().obtainTypedArray(R.array.cat_imgs); // Stores image IDs in a array

        TextView tvCategory = findViewById(R.id.tvCategory); // Set tvCategory to id tvCategory
        tvCategory.setText("Categories"); // Set text to tvCategory

        signOut = findViewById(R.id.actSignOut); // Button to sign out
        basket = findViewById(R.id.actBasket); // Button to view basket

        signOut.setText("Sign Out"); // Set sign out text
        basket.setText("Basket"); // Set basket text

        AvailableCategories(); // Finds AvailableCategories
    }

    @Override
    protected void SaveRecords(String[] resultColArray, String[][] resultColsArray)
    {
        super.SaveRecords(resultColArray, resultColsArray); // References the virtual function stored in
        //SQLBActivity (SQL Base Activity)

        if(action == "Category")
        {
            Arrays.sort(resultColArray); // sorts resultColArray
            unique = new HashSet<String>(Arrays.asList(resultColArray)).toArray(new String[0]); // Finds unique items
        }

        if(action == "Products")
        {
            names = new String[resultColsArray[0].length];
            prices = new float[resultColsArray[0].length];
            calories = new int[resultColsArray[0].length];
            allergies = new String[resultColsArray[0].length];

            //col
            for(int i = 0; i < resultColsArray.length; i++)
                for(int j = 0; j < resultColsArray[0].length; j++) //row
                {
                    if(i == 0) names[j] = resultColsArray[i][j]; // store names
                    if(i == 1) prices[j] = Float.parseFloat(resultColsArray[i][j]); // store prices as an float
                    if(i == 2) calories[j] = Integer.parseInt(resultColsArray[i][j]); // store calories as an integer
                    if(i == 3) allergies[j] = resultColsArray[i][j]; // store allergies
                }

            for(int i = 0; i < resultColsArray[0].length; i ++)
            {
                System.out.println("Names: " + names[i]);
                System.out.println("Price: " + prices[i]);
                System.out.println("KCal: " + calories[i]);
                System.out.println("Allergies: " + allergies[i]);
            }
        }

        //Calls CreateLayout
        CreateLayout();
    }

    protected void AvailableCategories()
    {
        //Set action to Category (Will be used in CreateLayout function
        action = "Category";

        //SQL QUERY (SELECT category FROM products)
        sqlConnection = new SQLConnection(this, "SELECT category FROM products", "qRows", null);
    }

    protected void AvailableProducts()
    {
        //Set action to Category (Will be used in CreateLayout function
        action = "Products";

        //Search Query
        String querySearch = "WHERE category = '" + tvCategory.getText().toString() + "'";

        //SQL QUERY (SELECT category FROM products + querySearch)
        sqlConnection = new SQLConnection(this, "SELECT name, price, calories, allergies FROM products " + querySearch, "", null);
    }

    //UI DEVELOPMENT HERE
    protected void CreateLayout()
    {
        //Draws activity_con_order_category.xml
        if(action == "Category")
        {
            //COLUMN Layout
            LinearLayout layoutCategoryCol = findViewById(R.id.layoutCategoryCol);

            //Sets lpStandard to Width = "Match_Parent" and
            // Height = "Wrap_Content"
            LinearLayout.LayoutParams lpStandard = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500);
            lpStandard.weight = 0f; // Set's lpStandard's weight to 1.00 float

            //Set lpButton to Width = 100 and
            // Height = 100
            LinearLayout.LayoutParams lpButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 150);
            lpButton.weight = 1f;

            //Set lpImg to Width = 100 and
            // Height = 100
            LinearLayout.LayoutParams lpImg = new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.MATCH_PARENT);
            lpImg.weight = .5f; //Set lpImg to Weight = 1 float

            //ROW Layouts
            LinearLayout[] layoutCategoryRow = new LinearLayout[unique.length];

            //Button's to access the category.
            Button[] buttons = new Button[unique.length];

            //Image Views
            ImageView[] imgs = new ImageView[unique.length];

            //Dynamically Creates the Front Category Page
            for (int i = 0; i < unique.length; i++) {
                //Creates a new row
                layoutCategoryRow[i] = new LinearLayout(this);
                //Sets the layout params to lpStandard
                layoutCategoryRow[i].setLayoutParams(lpStandard);
                //creates a new button
                buttons[i] = new Button(this);
                //Sets the layout params to lpButton
                buttons[i].setLayoutParams(lpButton);
                buttons[i].setText(unique[i]); // set text to unique by index

                final int finalButtonI = i; // i is not declared in the nested object
                //If image is called, call LoadConsumerOrderContent
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { LoadConsumerOrderContent(v, unique[finalButtonI]); } });

                if(i < categoryImages.length())
                {
                    imgs[i] = new ImageView(this);
                    imgs[i].setImageResource(categoryImages.getResourceId(i, -1));
                    imgs[i].setLayoutParams(lpImg); // Sets the layout params to lpImg


                    final int finalImgI = i; // i is not declared in the nested object
                    //If image is called, call LoadConsumerOrderContent
                    imgs[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) { LoadConsumerOrderContent(v, unique[finalImgI]); } });

                    //Add current imgs to current layoutCategoryRow
                    layoutCategoryRow[i].addView(imgs[i]);
                }

                //Add current buttons to current layoutCategoryRow
                layoutCategoryRow[i].addView(buttons[i]);

                //Add current layoutCurrentRow to layoutCategoryCol
                layoutCategoryCol.addView(layoutCategoryRow[i]);
            }
        }

        //Draws activity_consumer_order.xml
        if(action == "Products")
        {
            //Column View (Found Under svProducts)
            LinearLayout liProducts = findViewById(R.id.liProducts);

        }
    }

    /* BUTTON FUNCTIONS */
    protected void LoadConsumerOrderContent(View view, String process)
    {
        setContentView(R.layout.activity_consumer_order);

        tvCategory = findViewById(R.id.tvCategory);
        tvCategory.setText(process);

        signOut = findViewById(R.id.actBack); //Button to go back (reusing signOut variable.)
        signOut.setText("Categories"); //Set signOut's text to Categories

        basket = findViewById(R.id.actBasket); // Button to go to basket on Basket
        basket.setText("Basket"); //Set basket's text to Basket

        AvailableProducts(); // Queries for available products
    }

    public void SignOut(View view)
    {
        Intent consumerOrder = new Intent(this, LoginActivity.class); // Create new intent
        finish(); // finish this activity
        startActivity(consumerOrder); // Load consumerOrder intent // (Opens LoginActivity)
    }
 
    public void Back(View view)
    {
        finish(); // finish this activity
        startActivity(getIntent()); // Reload this activity (Opens main content view)
    }
}