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

    protected String[] unique; // String Array declaration
    protected String action; // String declaration

    protected Button signOut, basket; // Button declaration

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
    protected void SaveRecords(String[] resultSet)
    {
        super.SaveRecords(resultSet);

        Arrays.sort(resultSet); // sorts resultSet
        unique = new HashSet<String>(Arrays.asList(resultSet)).toArray(new String[0]); // Finds unique items

        CreateLayout();
    }

    protected void AvailableCategories()
    {
        action = "Category";

        //SQL QUERY (SELECT category FROM products)
        sqlConnection = new SQLConnection(this, "SELECT category FROM products", "qRows", null);
    }

    protected void CreateLayout()
    {
        if(action == "Category") {
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
                buttons[i].setText(unique[i]);

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

                    layoutCategoryRow[i].addView(imgs[i]);
                }
                layoutCategoryRow[i].addView(buttons[i]);
                layoutCategoryCol.addView(layoutCategoryRow[i]);
            }
        }
    }

    /* BUTTON FUNCTIONS */
    protected void LoadConsumerOrderContent(View view, String process)
    {
        System.out.println(process);
        setContentView(R.layout.activity_consumer_order);

        TextView tvCategory = findViewById(R.id.tvCategory);
        tvCategory.setText(process);

        signOut = findViewById(R.id.actBack); //Button to go back (reusing signOut variable.)
        signOut.setText("Categories"); //Set signOut's text to Categories

        basket = findViewById(R.id.actBasket); // Button to go to basket on Basket
        basket.setText("Basket"); //Set basket's text to Basket
    }

    public void SignOut(View view)
    {
        Intent consumerOrder = new Intent(this, LoginActivity.class);
        finish();
        startActivity(consumerOrder);
    }
 
    public void Back(View view)
    {
        finish();
        startActivity(getIntent());
    }
}