/*
    Author: Edward Patch
 */


package com.crazygaming.staysafe.staffArea;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import com.crazygaming.staysafe.R;
import com.crazygaming.staysafe.SQLConnection;

public class StaffTabs extends Fragment
{
    SQLConnection sqlConnection;

    protected WebView ccControlPanel; // Staff Product Control
    protected WebSettings ccWebSettings; // Staff Product Control Web Setting
    protected View view;
    protected FrameLayout flLayout;
    protected String action;

    protected static String[][] orders;
    protected int activeMenu;

    public StaffTabs() { }

    public static void Set2DArray(String action, String[][] arrayObject)
    {
        if(action.equals("Orders")) orders = arrayObject;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Initialise View
        view = inflater.inflate(R.layout.fragment_staff_tabs, container, false);
        flLayout = view.findViewById(R.id.flFrameLayout);

        //Initialise Active Menu
        activeMenu = -1;

        //Get the Action
        action = getArguments().getString("action");

        //Creates the Layout on the view
        CreateLayout();

        //return view
        return view;
    }

    protected void CreateLayout()
    {
        /* LAYOUT PARAMS */
        //Sets lpStandard to Width = "Match_Parent" and Height = "Match_Parent"
        LinearLayout.LayoutParams lpStandard = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //Sets lpInvert to Width = "Wrap_Content" and Height = "Wrap_Content"
        LinearLayout.LayoutParams lpInvert = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //Sets lpWCMP to Width = "Wrap_Content" and Height = "Match_Parent"
        LinearLayout.LayoutParams lpWCMP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //Sets lpMPWC to Width = "Match_Parent" and Height = "Wrap_Content"
        LinearLayout.LayoutParams lpMPWC = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //Sets lpMPWCW5 to Width = "Match_Parent" and Height = "Wrap_Content" and Weight to 50%
        LinearLayout.LayoutParams lpMPWCW5 = lpMPWC;
        lpMPWCW5.weight = .5f;

        if(action.equals("Products"))
        {
            //LAYOUT CUSTOMS
            //Prefix Space (W: 60, H: WC, Weight: 50%)
            LinearLayout.LayoutParams lpPrefixSpace = new LinearLayout.LayoutParams(60, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpPrefixSpace.weight = .5f;

            //Suffix Space (W: 20, H: WC, Weight: 40%)
            LinearLayout.LayoutParams lpSuffixSpace = new LinearLayout.LayoutParams(60, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpSuffixSpace.weight = .4f;

            //Control Panel Layout (W: MP, H: 0, Weight: 40%)
            LinearLayout.LayoutParams lpControlPanel = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
            lpControlPanel.weight = .4f;

            //REMAKE OF GITHUB, PRODUCTS-DESIGN2-JAVA-WEBVIEW design plan.
            //Designed by Edward Patch

            //Web View is the Staff Control Panel
            ccControlPanel = new WebView(getContext());

            ccWebSettings = ccControlPanel.getSettings(); //Get's settings of ccControlPanel
            ccWebSettings.setJavaScriptEnabled(true); // sets ccControlPanel to display JS
            ccControlPanel.setWebViewClient(new WebViewClient());
            ccControlPanel.loadUrl("http://" + sqlConnection.GetServer() + "/sk21/staff");

            LinearLayout layProductControls = new LinearLayout(getContext());
            layProductControls.setOrientation(LinearLayout.VERTICAL); //Set orientation to Vertical
            layProductControls.setLayoutParams(lpStandard); // Set layout to lpStandard

                LinearLayout pcControlList = new LinearLayout(getContext());
                pcControlList.setOrientation(LinearLayout.HORIZONTAL); //Set orientation to Horizontal
                pcControlList.setLayoutParams(lpInvert);

                    Space prefixSpace = new Space(getContext());
                    prefixSpace.setLayoutParams(lpPrefixSpace); // Set layout to lpPrefixSpace

                    final Button clViewProducts = new Button(getContext());
                    clViewProducts.setLayoutParams(lpMPWCW5); // Set layout to lpMPWCW5
                    clViewProducts.setText("Login/Logout"); // Set button text to View Products
                    clViewProducts.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ccControlPanel.setWebViewClient(new WebViewClient());
                            ccControlPanel.loadUrl("http://" + sqlConnection.GetServer() + "/sk21/staff/logout.php");
                        }
                    });

                    Space suffixSpace = new Space(getContext());
                    suffixSpace.setLayoutParams(lpSuffixSpace); // Set layout to lpSuffixSpace

                    Button clAddProducts = new Button(getContext());
                    clAddProducts.setLayoutParams(lpStandard); // Set layout to lpStandard
                    clAddProducts.setText("Product Control"); // Set button text to Add Products
                    clAddProducts.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Fix WebView to reload new URL
                            //REFERENCE: https://stackoverflow.com/questions/7746409/android-webview-launches-browser-when-calling-loadurl
                            ccControlPanel.setWebViewClient(new WebViewClient());

                            ccControlPanel.loadUrl("http://" + sqlConnection.GetServer() + "/sk21/staff/staffarea");
                        }
                    });

                LinearLayout pcControlCentre = new LinearLayout(getContext());
                pcControlCentre.setOrientation(LinearLayout.VERTICAL); // Set orientation to Vertical
                pcControlCentre.setLayoutParams(lpControlPanel); // Set layout to lpControlPanel

            pcControlCentre.addView(ccControlPanel);

            pcControlList.addView(prefixSpace);
            pcControlList.addView(clViewProducts);

            pcControlList.addView(suffixSpace);
            pcControlList.addView(clAddProducts);

            layProductControls.addView(pcControlList);
            layProductControls.addView(pcControlCentre);

            flLayout.addView(layProductControls);
        }

        if(action.equals("Orders") && orders != null)
        {
            /* CUSTOM LAYOUT PARAMS */
            //OrderView margins
            LinearLayout.LayoutParams lpOrderView = lpStandard;
            lpOrderView.leftMargin = 8;
            lpOrderView.topMargin = 16;
            lpOrderView.rightMargin = 8;
            lpOrderView.bottomMargin = 16;

            //OrderInformation Layout
            LinearLayout.LayoutParams lpOrderInformation = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lpOrderInformation.leftMargin = 30;
            lpOrderInformation.rightMargin = 30;
            lpOrderInformation.gravity = Gravity.CENTER;

            //lpInvert Override
            lpInvert.weight = 1;

            /*
             *  Tabbing corresponds with parenting.
             */

            //Order Scroll View
            ScrollView orderView = new ScrollView(getContext());
            orderView.setLayoutParams(lpOrderView); // Sets OrderView Layout Params to lpOrderView

                //Creates Order Navigation Layout
                LinearLayout ovOrderNavigation = new LinearLayout(getContext());
                ovOrderNavigation.setOrientation(LinearLayout.VERTICAL); //Sets ovOrderNavigation to Vertical
                ovOrderNavigation.setLayoutParams(lpStandard); //Sets ovOrderNavigation to lpStandard

                //Order List Layout
                LinearLayout[] onOrderList = new LinearLayout[orders[1].length];

                    //Creates Order ID Buttons (Collapsible Menu)
                    final Button[] olOrderID = new Button[orders[1].length];

                    //Creates Order Information Layout
                    final LinearLayout[] olOrderInformation = new LinearLayout[orders[1].length];

                        LinearLayout[] oiOrderText = new LinearLayout[orders[1].length];

                            //Creates Order Text Views
                            TextView[] otCustomerID = new TextView[orders[1].length];
                            TextView[] otCustomerName = new TextView[orders[1].length];
                            TextView[] otProductName = new TextView[orders[1].length];
                            TextView[] otOrderPrice = new TextView[orders[1].length];

                        //Creates Order Actions Layout
                        LinearLayout[] oiOrderActions = new LinearLayout[orders[1].length];

                            //Creates Order Action Buttons
                            Button[] oaAcceptOrder = new Button[orders[1].length];
                            Button[] oaDeclineOrder = new Button[orders[1].length];


            for(int i = 0; i < orders[1].length; i++)
            {
                //Initialises Order List Layout
                onOrderList[i] = new LinearLayout(getContext());
                onOrderList[i].setOrientation(LinearLayout.VERTICAL); //Sets Orientation to Vertical
                onOrderList[i].setLayoutParams(lpStandard); //Sets onOrderList to lpStandard

                    //Initialises OrderID Button
                    olOrderID[i] = new Button(getContext());
                    olOrderID[i].setText(orders[0][i]); //Sets text to ID (col) and order (rows)
                    olOrderID[i].setLayoutParams(lpMPWC); //Sets olOrderID to lpMPWC
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && orders[11][i].equals("1"))
                        olOrderID[i].setBackgroundTintList(getResources().getColorStateList(R.color.colorBTNAccept));

                    final int finalI = i;
                    olOrderID[i].setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                if(activeMenu == finalI)
                                {
                                    //Reference: https://stackoverflow.com/questions/54012320/how-to-set-backgroundtintlist-of-a-button-to-default
                                    //How to Set BackgroundTintList of a button to default?
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && orders[11][finalI].equals("0"))
                                        olOrderID[finalI].setBackgroundTintList(getResources().getColorStateList(R.color.colorBTNDefault));

                                    olOrderInformation[activeMenu].setVisibility(View.GONE);
                                    activeMenu = -1;
                                    return;
                                }
                                else if(activeMenu != -1)
                                {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && orders[11][activeMenu].equals("0"))
                                        olOrderID[activeMenu].setBackgroundTintList(getResources().getColorStateList(R.color.colorBTNDefault));
                                    olOrderInformation[activeMenu].setVisibility(View.GONE);
                                }

                                activeMenu = finalI;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && orders[11][finalI].equals("0"))
                                    olOrderID[finalI].setBackgroundTintList(getResources().getColorStateList(R.color.colorBTNActive));

                                olOrderInformation[activeMenu].setVisibility(View.VISIBLE);
                            }
                        });

                    //Initialises Order Information Layout
                    olOrderInformation[i] = new LinearLayout(getContext());
                    olOrderInformation[i].setOrientation(LinearLayout.VERTICAL); //Sets orientation to Vertical
                    olOrderInformation[i].setLayoutParams(lpOrderInformation); //Sets olOrderInformation to lpOrderInformation
                    olOrderInformation[i].setVisibility(View.GONE);

                        //Initialises Order Text Layout
                        oiOrderText[i] = new LinearLayout(getContext());
                        oiOrderText[i].setOrientation(LinearLayout.HORIZONTAL); // Sets Order Text Layout to Horizontal

                            //Initialises Order Text Customer ID
                            otCustomerID[i] = new TextView(getContext());
                            otCustomerID[i].setText(orders[2][i]); // Sets text to Customer ID
                            otCustomerID[i].setLayoutParams(lpInvert); //Sets otCustomerID to lpInvert

                            //Initialises Order Text Customer Name
                            otCustomerName[i] = new TextView(getContext());
                            otCustomerName[i].setText(orders[4][i]); // Sets text to Customer Name
                            otCustomerName[i].setLayoutParams(lpInvert); //Sets otCustomerName to lpInvert

                            //Initialises Order Text Product Name
                            otProductName[i] = new TextView(getContext());
                            otProductName[i].setText(orders[6][i]);
                            otProductName[i].setLayoutParams(lpInvert); //Sets otProductName to lpInvert

                            //Initialises Order Text Product Price
                            otOrderPrice[i] = new TextView(getContext());
                            otOrderPrice[i].setText("Price: " + orders[10][i]); // Sets text to Price
                            otOrderPrice[i].setLayoutParams(lpInvert); //Sets otOrderPrice to lpInvert

                        //Initialises Order Actions Layout
                        oiOrderActions[i] = new LinearLayout(getContext());
                        oiOrderActions[i].setOrientation(LinearLayout.HORIZONTAL); // Sets Order Actions Layout to Horizontal.
                        oiOrderActions[i].setLayoutParams(lpStandard); //Sets oiOrderActions to lpStandard

                            //Initialises Accept Order Button
                            oaAcceptOrder[i] = new Button(getContext());
                            oaAcceptOrder[i].setText("Accept Order"); // Sets text to Accept Order
                            oaAcceptOrder[i].setLayoutParams(lpInvert); //Sets oaAcceptOrder to lpInvert
                            oaAcceptOrder[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v)
                                {
                                    String query = "UPDATE orders SET active = 1 WHERE id = '" +
                                            orders[0][finalI] + "'";

                                    sqlConnection = new SQLConnection(null, query, "Update", null);

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        olOrderID[finalI].setBackgroundTintList(getResources().getColorStateList(R.color.colorBTNAccept));
                                    }
                                }
                            });

                            oaDeclineOrder[i] = new Button(getContext());
                            oaDeclineOrder[i].setText("Decline Order"); // Sets text to Decline Order
                            oaDeclineOrder[i].setLayoutParams(lpInvert); //Sets oaDeclineOrder to lpInvert
                            oaDeclineOrder[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v)
                                {
                                    String query = "DELETE FROM orders WHERE id = '" +
                                            orders[0][finalI] + "'";
                                    sqlConnection = new SQLConnection(null, query, "Delete", null);
                                }
                            });


                oiOrderActions[i].addView(oaAcceptOrder[i]);
                oiOrderActions[i].addView(oaDeclineOrder[i]);

                oiOrderText[i].addView(otCustomerID[i]);
                oiOrderText[i].addView(otCustomerName[i]);
                oiOrderText[i].addView(otProductName[i]);
                oiOrderText[i].addView(otOrderPrice[i]);

                olOrderInformation[i].addView(oiOrderText[i]);
                olOrderInformation[i].addView(oiOrderActions[i]);

                onOrderList[i].addView(olOrderID[i]);
                onOrderList[i].addView(olOrderInformation[i]);

                ovOrderNavigation.addView(onOrderList[i]);
            }

            orderView.addView(ovOrderNavigation);
            flLayout.addView(orderView);
        }
    }
}