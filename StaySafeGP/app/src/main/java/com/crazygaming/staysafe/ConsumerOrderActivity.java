/*
    Author: Edward Patch
 */


package com.crazygaming.staysafe;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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

    protected Button signOut, basket, atb; // Button declaration
    protected String[] names, allergies, images; // Store Names, Allergies, Images
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
            // Initialises the following variables
            names = new String[resultColsArray[0].length];
            prices = new float[resultColsArray[0].length];
            calories = new int[resultColsArray[0].length];
            allergies = new String[resultColsArray[0].length];
            images = new String[resultColsArray[0].length];

            //col
            for(int i = 0; i < resultColsArray.length; i++)
                for(int j = 0; j < resultColsArray[0].length; j++) //row
                {
                    if(i == 0) names[j] = resultColsArray[i][j]; // store names
                    if(i == 1) prices[j] = Float.parseFloat(resultColsArray[i][j]); // store prices as an float
                    if(i == 2) calories[j] = Integer.parseInt(resultColsArray[i][j]); // store calories as an integer
                    if(i == 3) allergies[j] = resultColsArray[i][j]; // store allergies
                    if(i == 4) images[j] = resultColsArray[i][j]; // store images
                }
        }

        if(action == "ATB" && atb.getText().toString().equals("Adding..."))
        {

            //ClassSelector(action, resultColsArray); // EXECUTES C++ CODE

            atb.setText("Added");

            ClassSelector(action, resultColsArray);
            System.out.println(GetData(action));

            return; //No need to create layout again
        }

        if (action == "ATB" && atb.getText().toString().equals("Removing..."))
        {
            atb.setText("Removed");

            return;
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
        sqlConnection = new SQLConnection(this, "SELECT name, price, calories, allergies, img FROM products " + querySearch, "", null);
    }

    //UI DEVELOPMENT HERE
    protected void CreateLayout()
    {
        //Draws activity_con_order_category.xml
        if(action == "Category")
        {
            /*\\ LAYOUT PARAMS //*/

            //Sets lpStandard to Width = "Match_Parent" and
            // Height = "Wrap_Content"
            LinearLayout.LayoutParams lpStandard = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500);
            lpStandard.weight = 0f; // Set's lpStandard's weight to 1.00 float

            //Set lpButton to Width = 100 and
            // Height = 100
            LinearLayout.LayoutParams lpButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 150);
            lpButton.weight = 1f;

            //Set lpImg to Width = 500 and
            // Height = "Wrap_Content
            LinearLayout.LayoutParams lpImg = new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.MATCH_PARENT);
            lpImg.weight = .5f; //Set lpImg to Weight = 1 float

            //Initialisation I tried keeping in relative order to XML theme instead of data type
            //alphabetically, to make it easier for the UI Dev to picture it.

            //COLUMN Layout
            LinearLayout layoutCategoryCol = findViewById(R.id.layoutCategoryCol);

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
            /*\\ LAYOUT PARAMS //*/

            //STANDARDS

            //Sets lpStandard to Width = "Match_Parent" and Height = "Match_Parent"
            LinearLayout.LayoutParams lpStandard = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            //Sets lpStandard to Width = "Wrap_Content" and Height = "Wrap_Content"
            LinearLayout.LayoutParams lpInvert = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            //Sets layoutProductRows to Width = "Wrap_Content" and Height = "Match_Parent"
            LinearLayout.LayoutParams lpWCMP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

            //Sets layoutProductRows to Width = "Match_Parent" and Height = "Wrap_Content"
            //Sets Bottom Margin to 10
            LinearLayout.LayoutParams lpMPWC = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            //CUSTOMS

            //Sets layoutProductRows to Width = "Match_Parent" and Height = "Match_Parent"
            //Sets Bottom Margin to 10
            LinearLayout.LayoutParams layoutProductRows = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutProductRows.bottomMargin = 10;

            //Sets layoutPRImages to Width = "Match_Parent" and Height = 359
            LinearLayout.LayoutParams layoutPRImages = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 350);

            //Sets layoutPActionATB to Width = "Match_Parent" and Height = "Wrap-Content"
            //Sets Bottom Margin to 2
            LinearLayout.LayoutParams layoutPActionATB = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutPActionATB.topMargin = 2;

            //Sets layoutPRowsInfo to Width = "Match_Parent" and Height = "Match_Parent"
            //Sets Bottom Margin to 20
            LinearLayout.LayoutParams layoutPRowsInfo = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutPRowsInfo.leftMargin = 20;

            //Sets layoutProductRows to Width = "Match_Parent" and Height = "Match_Parent"
            //Sets Bottom Margin to 10
            //Sets Right Margin to 10
            LinearLayout.LayoutParams layoutPRInfoText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutPRInfoText.leftMargin = 50;
            layoutPRowsInfo.rightMargin = 50;

            /*\\ INITIALISATION //*/

            //Initialisation I tried keeping in relative order to XML theme instead of data type
            //alphabetically, to make it easier for the UI Dev to picture it.
            //Tabbing to demonstrate the parenting.

            //Column View (Found Under svProducts)
            LinearLayout liProducts = findViewById(R.id.liProducts);

            //Declares and Initialises the liProductRows array
            LinearLayout[] liProductsRows = new LinearLayout[names.length];

                //Declares and Initialises the prTitle array
                TextView[] prTitle = new TextView[names.length];

                //Declares and Initialises the prProductActions array
                LinearLayout[] prProductActions = new LinearLayout[names.length]; //length is rows...

                    //Declares and Initialises the paImageView array
                    ImageView[] paImageView = new ImageView[names.length];

                    //Declares and Initialises the paActionList array
                    LinearLayout[] paActionList = new LinearLayout[names.length];
                        //Declares and Initialises the alPurchase array
                        Button[] alPurchase = new Button[names.length];

                        //Declares and Initialises the alAddToBasket array
                        final Button[] alAddToBasket = new Button[names.length];

                //Declares and Initialises the liProductInfo array
                LinearLayout[] prProductInfo = new LinearLayout[names.length];

                    //Declares and Initialises the piPrice array.
                    TextView[] piPrice = new TextView[names.length];

                    TextView[] piCalories = new TextView[names.length];

                    TextView[] piAllergies = new TextView[names.length];


            /*\\ DRAWS CONTENT PAGE //*/
            for (int i = 0; i < names.length; i++)
            {
                // Creates a new Product Row Layout
                liProductsRows[i] = new LinearLayout(this);
                //Sets List Item Product Row's Orientation to Vertical
                liProductsRows[i].setOrientation(LinearLayout.VERTICAL);
                liProductsRows[i].setLayoutParams(layoutProductRows); //Sets LayoutParams to layoutProductRows

                    // Creates a new Product Row Title TextView
                    prTitle[i] = new TextView(this);
                    prTitle[i].setGravity(Gravity.CENTER); //Sets prTitle's gravity to center and bottom
                    prTitle[i].setText(names[i]); // Sets prTitle's text to names[i]

                    // Creates a new Product Row Action Layout
                    prProductActions[i] = new LinearLayout(this);
                    //Sets Product Rows Actions's Orientation to Horizontal
                    prProductActions[i].setOrientation(LinearLayout.HORIZONTAL);
                    prProductActions[i].setLayoutParams(lpStandard); //Sets LayoutParams to lpStandard

                        // Creates a new Product Action List Layout
                        paActionList[i] = new LinearLayout(this);
                        //Sets Product Action List Orientation to Vertical
                        paActionList[i].setOrientation(LinearLayout.VERTICAL);
                        paActionList[i].setLayoutParams(lpMPWC); //Sets LayoutParams to lpMPWC

                            // Creates a new Action List Purchase Button
                            alPurchase[i] = new Button(this);
                            alPurchase[i].setText("Purchase"); //Sets alPurchase button text to Purchase
                            alPurchase[i].setLayoutParams(lpStandard); //Sets LayoutParams to lpStandard

                            // Creates a new Action List Add to Basket Button.
                            alAddToBasket[i] = new Button(this);
                            alAddToBasket[i].setText("Add To Basket"); // Sets alAddToBasket button text to Add To Basket
                            alAddToBasket[i].setLayoutParams(layoutPActionATB); // Sets LayoutParams to layoutPActionATB
                            // Wires alAddToBack[i] to AddToBasket function
                            final int finalBasketI = i; // copies i to finalBasketI for nested function to use
                            alAddToBasket[i].setOnClickListener(
                                    new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    atb = alAddToBasket[finalBasketI];
                                    if(alAddToBasket[finalBasketI].getText().toString().equals("Add To Basket")
                                    || alAddToBasket[finalBasketI].getText().toString().equals("Removed"))
                                        alAddToBasket[finalBasketI].setText("Adding...");

                                    else alAddToBasket[finalBasketI].setText("Removing...");

                                    AddToBasket(v, names[finalBasketI]); }
                            });

                    // Creates a new Product Row Info Layout.
                    prProductInfo[i] = new LinearLayout(this);
                    //Sets Product Rows Info Orientation to Horizontal
                    prProductInfo[i].setOrientation(LinearLayout.HORIZONTAL);
                    prProductInfo[i].setLayoutParams(layoutPRowsInfo); // Sets LayoutParams to layoutPRowsInfo

                        //Creates a new Product Info Price TextView.
                        piPrice[i] = new TextView(this);
                        piPrice[i].setText(String.valueOf("£" + prices[i])); //Sets piPrice text to prices[i]
                        piPrice[i].setLayoutParams(layoutPRInfoText); //Sets LayoutParams to layoutPRInfoText

                        //Creates a new Product Info Calories TextView.
                        piCalories[i] = new TextView(this);
                        piCalories[i].setText(String.valueOf(calories[i] + "KCal")); //Sets piCalories to calories[i]
                        piCalories[i].setLayoutParams(layoutPRInfoText); //Sets LayoutParams to layoutPRInfoText

                        //Creates a new Product Info Allergies TextView.
                        piAllergies[i] = new TextView(this);
                        piAllergies[i].setText(allergies[i]); //Sets piAllergies to allergies[i]
                        piAllergies[i].setLayoutParams(layoutPRInfoText); //Sets LayoutParams to layoutPRInfoText


                //FIND AVAILABLE IMAGES
                for(int j = 0; j < categoryImages.length(); j++)
                {
                    //Creates a new ImageView
                    paImageView[i] = new ImageView(this);
                    //Sets paImageView's image resource to categoryImage's image by index.
                    //paImageView[i].setImageResource(SET TO MYSQL LOCATION);
                    paImageView[i].setLayoutParams(layoutPRImages); //Set LayoutParams to layoutPRImages

                    //Adds ImageView to prProductAction (only if available)
                    prProductActions[i].addView(paImageView[i]);
                }

                /* ADD TO VIEWPORT */

                //Adds alPurchase to paActionList
                paActionList[i].addView(alPurchase[i]);
                //Adds alAddToBasket to paActionList
                paActionList[i].addView(alAddToBasket[i]);

                //Adds paActionList to prProductActions
                prProductActions[i].addView(paActionList[i]);

                //Adds piPrice to prProductInfo
                prProductInfo[i].addView(piPrice[i]);
                //Adds piCalories to prProductInfo
                prProductInfo[i].addView(piCalories[i]);
                //Adds piAllergies to prProductInfo
                prProductInfo[i].addView(piAllergies[i]);

                //Adds prTitle to liProductRows
                liProductsRows[i].addView(prTitle[i]);
                //Adds prProductActions to liProductRows
                liProductsRows[i].addView(prProductActions[i]);
                //Adds prProductInfo to liProductRows
                liProductsRows[i].addView(prProductInfo[i]);

                //Adds liProductsRows to liProducts
                liProducts.addView(liProductsRows[i]);
            }
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

    public void AddToBasket(View view, String productName)
    {
        action = "ATB"; //Action: Add to Basket
        //Search Query - Expectation:
        //WHERE name = 'Cappuccino (Medio)'
        String querySearch = "WHERE name = '" + productName + "'";

        sqlConnection = new SQLConnection(this, "SELECT * FROM products " + querySearch, "", null);
    }

    // Open Basket Activity
    public void Basket(View view)
    {
        CloseForm("", this, ConsumerBasketActivity.class); // Uses CloseForm to open ConsumerBasket
    }

    // Sign Out
    public void SignOut(View view)
    {
        CloseForm("", this, LoginActivity.class); // Uses CloseForm to open LoginActivity
    }

    //Back
    public void Back(View view)
    {
        finish(); // finish this activity
        startActivity(getIntent()); // Reload this activity (Opens main content view)
    }
}