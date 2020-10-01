/* Author Edward Patch */

package com.crazygaming.staysafe;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//Set SQLBActivity as Base Class
public class ConsumerBasketActivity extends SQLBActivity
{
    SQLConnection sqlConnection;
    protected TextView tvTitle, tvStatus;
    protected Button actBack, actSignOut;

    protected String action;
    protected int[] sizes = new int [2];
    protected String[][] currentProducts, RTB;
    protected double subtotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_basket);

        InitialSetup(); // Sets XML activity

        FetchBasketCount(); // fetches basket count
        FetchBasketProducts(); // fetches basket products


        //If productCount is 0
        if(sizes[0] == 0) tvStatus.setVisibility(View.VISIBLE); // Set's tvStatus to visible.
        else CreateLayout(); // draw layout
    }

    //Fetches product count
    protected void FetchBasketCount()
    {
        //Row and Col
        for(int i = 0; i < 2; i++)
            sizes[i] = GetSizes(i); // Sets the sizes

        currentProducts = new String[sizes[1]][sizes[0]]; // Allocated the currentProducts
        // 2D array.. (Java arrays are nice sometimes over C++ Pointers...)
    }

    //Fetches products stored in basket
    protected void FetchBasketProducts()
    {
        //View Basket
        action = "VB";

        for(int i = 0; i < sizes[0]; i++)
            for(int j = 0; j < sizes[1]; j++)
                currentProducts[j][i] = GetData(action, i, j); /* Sets currentProducts to
                products that was recorded with C++ (ATB/RFB) actions */
    }

    protected void InitialSetup()
    {
        tvTitle = findViewById(R.id.tvTitle); // Title Text
        tvStatus = findViewById(R.id.tvStatus); // Product Status
        actBack = findViewById(R.id.actBack); // back button
        actSignOut = findViewById(R.id.actSignOut); // sign out button

        tvTitle.setText("Basket"); // sets tvTitle to Basket
        tvStatus.setVisibility(View.GONE); // sets Status to gone
        tvStatus.setText("Basket Empty"); // Sets tvStatus to Basket Empty
        actBack.setText("Back"); // Sets actBack text to Back
        actSignOut.setText("Sign Out"); // Sets actSignOut text to Sign Out.
    }

    @Override
    protected void SaveRecords(String[] resultColArray, String[][] resultColsArray)
    {
        super.SaveRecords(resultColArray, resultColsArray);

        if(action == "RFB")
        {
            System.out.println(resultColsArray[1][0]);
            ClassSelector(action, resultColsArray);

            finish();
            startActivity(getIntent());
        }
    }

    protected void CreateLayout()
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

        //Sets layoutBasketRows to Width = "Match_Parent" and Height = "Match_Parent"
        //Sets Bottom Margin to 10
        LinearLayout.LayoutParams layoutBasketRows = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutBasketRows.bottomMargin = 10;

        //Sets layoutPRImages to Width = "Match_Parent" and Height = 359
        LinearLayout.LayoutParams layoutBRImages = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 350);

        //Sets layoutBActionRFB to Width = "Match_Parent" and Height = "Wrap-Content"
        //Sets Bottom Margin to 2
        LinearLayout.LayoutParams layoutBActionRFB = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutBActionRFB.topMargin = 2;

        //Sets layoutPRowsInfo to Width = "Match_Parent" and Height = "Match_Parent"
        //Sets Bottom Margin to 20
        LinearLayout.LayoutParams layoutBRowsInfo = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutBRowsInfo.leftMargin = 20;

        //Sets layoutProductRows to Width = "Match_Parent" and Height = "Match_Parent"
        //Sets Bottom Margin to 10
        //Sets Right Margin to 10
        LinearLayout.LayoutParams layoutBRInfoText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutBRInfoText.leftMargin = 50;
        layoutBRowsInfo.rightMargin = 50;

        /*\\ INITIALISATION //*/

        //Initialisation I tried keeping in relative order to XML theme instead of data type
        //alphabetically, to make it easier for the UI Dev to picture it.
        //Tabbing to demonstrate the parenting.

        //Column View (Found Under svBasket)
        LinearLayout liBasket = findViewById(R.id.liBasket);

        //Declares and Initialises the liBasketRows array
        LinearLayout[] liBasketRows = new LinearLayout[sizes[0]];

            //Declares and Initialises the brTitle array
            TextView[] brTitle = new TextView[sizes[0]];

            //Declares and Initialises the brProductActions array
            LinearLayout[] brProductActions = new LinearLayout[sizes[0]]; //length is rows...

                //Declares and Initialises the paImageView array
                ImageView[] paImageView = new ImageView[sizes[0]];

                //Declares and Initialises the paActionList array
                LinearLayout[] paActionList = new LinearLayout[sizes[0]];

                    //Declares and Initialises the alRFB button array
                    final Button[] alRFB = new Button[sizes[0]];

            //Declares and Initialises the liProductInfo array
            LinearLayout[] brProductInfo = new LinearLayout[sizes[0]];

                //Declares and Initialises the piPrice array.
                TextView[] piPrice = new TextView[sizes[0]];

                TextView[] piCalories = new TextView[sizes[0]];

                TextView[] piAllergies = new TextView[sizes[0]];

            LinearLayout brProductCheckout; // Only needs 1 layout
                TextView pcTag; // Only needs 1 tag
                TextView pcSubTotal; // Only needs 1 subtotal

            LinearLayout brProductCheckoutActions; // Only needs 1 layout
                Button pcaPurchase; // Only needs 1 button

        for(int i = 0; i < sizes[0]; i++)
        {
            //Creates a new basket row layout
            liBasketRows[i] = new LinearLayout(this);
            //Sets List Item Basket Row's Orientation to Vertical
            liBasketRows[i].setOrientation(LinearLayout.VERTICAL);
            liBasketRows[i].setLayoutParams(layoutBasketRows);

                //Creates a new Basket Row Title TextView
                brTitle[i] = new TextView(this);
                brTitle[i].setGravity(Gravity.CENTER);
                brTitle[i].setText(currentProducts[1][i]); //Selects names col / i is products

                //Creates a new Basket Row Action Layout
                brProductActions[i] = new LinearLayout(this);
                //Sets Basket Rows Actions's Orientation to Horizontal
                brProductActions[i].setOrientation(LinearLayout.HORIZONTAL);
                brProductActions[i].setLayoutParams(lpStandard); // Sets LayoutParams to lpStandard

                    // Creates a new Product Action List layout
                    paActionList[i] = new LinearLayout(this);
                    //Sets Product Action List Orientation to Vertical
                    paActionList[i].setOrientation(LinearLayout.VERTICAL);
                    paActionList[i].setLayoutParams(lpMPWC); //Sets LayoutParams to lpMPWC

                        // Creates a new Action List Add to Basket Button.
                        alRFB[i] = new Button(this);
                        alRFB[i].setText("Remove From Basket"); // Sets alRFB button text to Remove from Basket
                        alRFB[i].setLayoutParams(layoutBActionRFB); // Sets LayoutParams to layoutBActionRFB

                        //NEEDS A ONCLICK LISTENER
                        final int finalI = i;
                        alRFB[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                RemoveFromBasket(currentProducts[1][finalI]);
                            }
                        });

                // Creates a new Product Row Info Layout.
                brProductInfo[i] = new LinearLayout(this);
                //Sets Product Rows Info Orientation to Horizontal
                brProductInfo[i].setOrientation(LinearLayout.HORIZONTAL);
                brProductInfo[i].setLayoutParams(layoutBRowsInfo); // Sets LayoutParams to layoutPRowsInfo

                    //Creates a new Product Info Price TextView.
                    piPrice[i] = new TextView(this);
                    piPrice[i].setText("£" + currentProducts[3][i]); //Sets piPrice text to currentProducts[3][i]
                    subtotal += Double.parseDouble(currentProducts[3][i]);
                    piPrice[i].setLayoutParams(layoutBRInfoText); //Sets LayoutParams to layoutBRInfoText

                    //Creates a new Product Info Calories TextView.
                    piCalories[i] = new TextView(this);
                    piCalories[i].setText(currentProducts[4][i] + "KCal"); //Sets piCalories to currentProducts[4][i]
                    piCalories[i].setLayoutParams(layoutBRInfoText); //Sets LayoutParams to layoutPRInfoText

                    //Creates a new Product Info Allergies TextView.
                    piAllergies[i] = new TextView(this);
                    piAllergies[i].setText(currentProducts[5][i]); //Sets piAllergies to currentProducts[5][i]
                    piAllergies[i].setLayoutParams(layoutBRInfoText); //Sets LayoutParams to layoutPRInfoText

            //FIND AVAILABLE IMAGES
            for(int j = 0; j < 0; j++)
            {
                //Creates a new ImageView
                paImageView[i] = new ImageView(this);
                //Sets paImageView's image resource to categoryImage's image by index.
                //paImageView[i].setImageResource(SET TO MYSQL LOCATION);
                paImageView[i].setLayoutParams(layoutBRImages); //Set LayoutParams to layoutPRImages

                //Adds ImageView to brProductAction (only if available)
                brProductActions[i].addView(paImageView[i]);
            }

            /* ADD TO VIEWPORT */
            //Adds alRFB to paActionList
            paActionList[i].addView(alRFB[i]);

            //Adds paActionList to brProductActions
            brProductActions[i].addView(paActionList[i]);

            //Adds piPrice brProductInfo
            brProductInfo[i].addView(piPrice[i]);
            //Adds piCalories to brProductInfo
            brProductInfo[i].addView(piCalories[i]);
            //Adds piAllergies to brProductInfo
            brProductInfo[i].addView(piAllergies[i]);

            //Adds brTitle to liBasketRows
            liBasketRows[i].addView(brTitle[i]);
            //Adds brProductActions to liBasketRows
            liBasketRows[i].addView(brProductActions[i]);
            //Adds brProductInfo to liBasketRows
            liBasketRows[i].addView(brProductInfo[i]);

            //Adds liBasketRows to liBasket
            liBasket.addView(liBasketRows[i]);
        }

        //Creates the brProductCheckout Layout
        brProductCheckout = new LinearLayout(this);
        brProductCheckout.setOrientation(LinearLayout.HORIZONTAL); // Sets Orientation to Horizontal
        brProductCheckout.setLayoutParams(lpStandard); // Sets layout to lpStandard

            pcTag = new TextView(this);
            pcTag.setLayoutParams(lpInvert); // Sets layout param to lpInvert.
            pcTag.setText("Sub-Total: "); // Sets text to Sub-Total

            pcSubTotal = new TextView(this);
            pcSubTotal.setLayoutParams(lpInvert); // Sets layout params to lpInvert
            pcSubTotal.setText("£" + subtotal); // set text to sub total (£3.50)

        //Creates the brProductCheckoutActions Layout
        brProductCheckoutActions = new LinearLayout(this);
        brProductCheckoutActions.setOrientation(LinearLayout.HORIZONTAL); // Sets orientation to Horizontal
        brProductCheckoutActions.setLayoutParams(lpStandard); // Sets layout to lpStandard

            // Creates the purchase button
            pcaPurchase = new Button(this);
            pcaPurchase.setText("Purchase"); // Sets button text to Purchase
            pcaPurchase.setLayoutParams(lpInvert); // Sets layout to lpInvert

        brProductCheckout.addView(pcTag); // adds pcTag to brProductCheckout
        brProductCheckout.addView(pcSubTotal); // adds pcSubTotal to brProductCheckout

        brProductCheckoutActions.addView(pcaPurchase); // adds pcaPurchase to brProductCheckoutActions

        liBasket.addView(brProductCheckout); // adds brProductCheckout to liBasket
        liBasket.addView(brProductCheckoutActions); // adds brProductCheckoutActions to liBasket
    }



    /* Button Functions*/
    public void RemoveFromBasket(String productName)
    {
        action = "RFB"; //Action: Add to Basket
        //Search Query - Expectation:
        //WHERE name = 'Cappuccino (Medio)'
        String querySearch = "WHERE name = '" + productName + "'";

        sqlConnection = new SQLConnection(this, "SELECT * FROM products " + querySearch, "", null);
    }

    public void Basket(View view)
    {
        //Closes this activity, opens ConsumerOrderActivity
        CloseForm("", this, ConsumerOrderActivity.class);
    }

    //Closes this activity, opens LoginActivity
    public void SignOut(View view)
    {
        CloseForm("", this, LoginActivity.class);
    }
}