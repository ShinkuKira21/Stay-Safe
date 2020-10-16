/*
    Author: Edward Patch
 */


package com.crazygaming.staysafe.staffArea;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.crazygaming.staysafe.R;

import org.w3c.dom.Text;

public class StaffTabs extends Fragment
{
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

        //Sets lpStandard to Width = "Wrap_Content" and Height = "Wrap_Content"
        LinearLayout.LayoutParams lpInvert = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //Sets layoutProductRows to Width = "Wrap_Content" and Height = "Match_Parent"
        LinearLayout.LayoutParams lpWCMP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //Sets layoutProductRows to Width = "Match_Parent" and Height = "Wrap_Content"
        LinearLayout.LayoutParams lpMPWC = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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
                    Button[] olOrderID = new Button[orders[1].length];

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
                    final int finalI = i;
                    olOrderID[i].setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                if(activeMenu == finalI)
                                {
                                    olOrderInformation[activeMenu].setVisibility(View.GONE);
                                    activeMenu = -1;
                                    return;
                                }
                                else if(activeMenu != -1)
                                    olOrderInformation[activeMenu].setVisibility(View.GONE);

                                activeMenu = finalI;
                                olOrderInformation[activeMenu].setVisibility(View.VISIBLE);
                            }
                        });
                    //TODO - Add Functionality to button

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
                            // TODO - Create Price Query

                        //Initialises Order Actions Layout
                        oiOrderActions[i] = new LinearLayout(getContext());
                        oiOrderActions[i].setOrientation(LinearLayout.HORIZONTAL); // Sets Order Actions Layout to Horizontal.
                        oiOrderActions[i].setLayoutParams(lpStandard); //Sets oiOrderActions to lpStandard

                            //Initialises Accept Order Button
                            oaAcceptOrder[i] = new Button(getContext());
                            oaAcceptOrder[i].setText("Accept Order"); // Sets text to Accept Order
                            oaAcceptOrder[i].setLayoutParams(lpInvert); //Sets oaAcceptOrder to lpInvert

                            oaDeclineOrder[i] = new Button(getContext());
                            oaDeclineOrder[i].setText("Decline Order"); // Sets text to Decline Order
                            oaDeclineOrder[i].setLayoutParams(lpInvert); //Sets oaDeclineOrder to lpInvert

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
        else
        {

        }
    }
}