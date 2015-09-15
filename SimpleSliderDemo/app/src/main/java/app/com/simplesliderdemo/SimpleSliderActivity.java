package app.com.simplesliderdemo;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.simplesliderdemo.AllCommonClass.UIslider;

public class SimpleSliderActivity extends FragmentActivity
{
    String jsonData = "{\"npidata\":[{\"id\":\"1831388404\",\"fullname\":\"AMERICAN CURRENT CARE PA\",\"type\":\"emergency\",\"latitude\":\"41.837642\",\"longitude\":\"-87.665741\",\"fulladdress\":\"3145 S ASHLAND AVE,SUITE 110,CHICAGO,IL,US,60608\",\"rating\":3,\"reviewCount\":9,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care,Clinic\\/Center\",\"distance\":2.0955347533003},{\"id\":\"1316388002\",\"fullname\":\"AMERICAN CURRENT CARE OF ILLINOIS PC\",\"type\":\"emergency\",\"latitude\":\"41.837642\",\"longitude\":\"-87.665741\",\"fulladdress\":\"3145 S ASHLAND AVE,SUITE 110,CHICAGO,IL,US,60608\",\"rating\":5,\"reviewCount\":44,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":2.0955347533003},{\"id\":\"1285823856\",\"fullname\":\"AMERICAN CURRENT CARE PA\",\"type\":\"emergency\",\"latitude\":\"41.827173\",\"longitude\":\"-87.729449\",\"fulladdress\":\"4201 WEST 36TH STREET,CHICAGO,IL,US,60632\",\"rating\":5,\"reviewCount\":49,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":2.4116189665392},{\"id\":\"1790030385\",\"fullname\":\"HOMETOWN URGENT CARE\",\"type\":\"emergency\",\"latitude\":\"41.850000\",\"longitude\":\"-87.650000\",\"fulladdress\":\"7483 SOLUTIONS CTR,CHICAGO,IL,US,60677\",\"rating\":2,\"reviewCount\":32,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":3.2576195954206},{\"id\":\"1649551938\",\"fullname\":\"OAK FOREST URGENT\\/IMMEDIATE CARE CLINIC\",\"type\":\"emergency\",\"latitude\":\"41.871516\",\"longitude\":\"-87.673764\",\"fulladdress\":\"1900 W POLK ST,SUITE 1348,CHICAGO,IL,US,60612\",\"rating\":5,\"reviewCount\":33,\"primaryspecialty\":\"Clinic\\/Center\",\"allspecialty\":\"Clinic\\/Center,Urgent Care\",\"distance\":4.1760342831924},{\"id\":\"1003084112\",\"fullname\":\"SOUTH LOOP URGENT CARE\",\"type\":\"emergency\",\"latitude\":\"41.861879\",\"longitude\":\"-87.624027\",\"fulladdress\":\"1430 S MICHIGAN AVE,C2,CHICAGO,IL,US,60605\",\"rating\":5,\"reviewCount\":2,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":4.7465122267332}],\"status\":1}";
    public String[] firstName, providerNpiID, lat, longg, address;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_slider);

        ArrayList<String> stringArray = new ArrayList<String>();

        // ListView Item
        for (int i=0; i<50 ; i++)
            stringArray.add(i + " no Item");

        //Listview Code
        ArrayAdapter modeAdapter = new ArrayAdapter(this, R.layout.list_item_adapter, R.id.provider, stringArray);
        ListView LV_bottom = (ListView) findViewById(R.id.LV_bottom);
        LV_bottom.setAdapter(modeAdapter);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LL_middle);

        TextView valueTV = new TextView(this);
        valueTV.setText("Hallo hallo");
        valueTV.setId(R.id.layout1);
        valueTV.setTextColor(Color.GREEN);
        valueTV.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        linearLayout.addView(valueTV);

        try 
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("npidata");
            firstName = new String[jsonArray.length()];
            providerNpiID = new String[jsonArray.length()];
            lat = new String[jsonArray.length()];
            longg = new String[jsonArray.length()];
            address = new String[jsonArray.length()];

            JSONObject jsonObject1;
            for (int i = 0; i < jsonArray.length(); i++)
            {
                try
                {
                    jsonObject1 = jsonArray.getJSONObject(i);

                    // getting  provider Name
                    if (jsonObject1.has("fullname"))
                    {
                        firstName[i] = jsonObject1.getString("fullname");
                    }
                    else
                    {
                        firstName[i] = "Unknown Name";
                    }
                    if (jsonObject1.has("fulladdress"))
                    {
                        address[i] = jsonObject1.getString("fulladdress");
                    }
                    else
                    {
                        address[i] = "Unknown Address";
                    }

                    // for Lat Common code for all like NAME & ALSO HOSPITAL
                    if (jsonObject1.has("latitude"))
                    {
                        if(String.valueOf(jsonObject1.getDouble("latitude")).equals("Unknown") ||
                                String.valueOf(jsonObject1.getDouble("latitude")).equals("OVER_QUERY_LIMIT"))
                        {
                            lat[i] = String.valueOf(0.0);
                        }
                        else
                        {
                            lat[i] = String.valueOf(jsonObject1.getDouble("latitude"));
                        }
                    }
                    else
                    {
                        lat[i] = String.valueOf(0.0);
                    }
                    // for Long
                    if (jsonObject1.has("longitude"))
                    {
                        if(String.valueOf(jsonObject1.getDouble("longitude")).equals("Unknown") ||
                                String.valueOf(jsonObject1.getDouble("longitude")).equals("OVER_QUERY_LIMIT"))
                        {
                            longg[i] = String.valueOf(0.0);
                        }
                        else
                        {
                            longg[i] = String.valueOf(jsonObject1.getDouble("longitude"));
                        }
                    }
                    else
                    {
                        longg[i] = String.valueOf(0.0);
                    }
                }
                catch (Exception e)
                {
                    
                }
            }
        }
        catch (Exception e)
        {
            //
        }
    }
}
