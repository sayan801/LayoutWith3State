package app.com.simplesliderdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.simplesliderdemo.AllCommonClass.UIslider;

public class SimpleSliderActivity extends FragmentActivity
{
//    String jsonData = "{\"npidata\":[{\"id\":\"1831388404\",\"fullname\":\"AMERICAN CURRENT CARE PA\",\"type\":\"emergency\",\"latitude\":\"41.837642\",\"longitude\":\"-87.665741\",\"fulladdress\":\"3145 S ASHLAND AVE,SUITE 110,CHICAGO,IL,US,60608\",\"rating\":3,\"reviewCount\":9,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care,Clinic\\/Center\",\"distance\":2.0955347533003},{\"id\":\"1316388002\",\"fullname\":\"AMERICAN CURRENT CARE OF ILLINOIS PC\",\"type\":\"emergency\",\"latitude\":\"41.837642\",\"longitude\":\"-87.665741\",\"fulladdress\":\"3145 S ASHLAND AVE,SUITE 110,CHICAGO,IL,US,60608\",\"rating\":5,\"reviewCount\":44,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":2.0955347533003},{\"id\":\"1285823856\",\"fullname\":\"AMERICAN CURRENT CARE PA\",\"type\":\"emergency\",\"latitude\":\"41.827173\",\"longitude\":\"-87.729449\",\"fulladdress\":\"4201 WEST 36TH STREET,CHICAGO,IL,US,60632\",\"rating\":5,\"reviewCount\":49,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":2.4116189665392},{\"id\":\"1790030385\",\"fullname\":\"HOMETOWN URGENT CARE\",\"type\":\"emergency\",\"latitude\":\"41.850000\",\"longitude\":\"-87.650000\",\"fulladdress\":\"7483 SOLUTIONS CTR,CHICAGO,IL,US,60677\",\"rating\":2,\"reviewCount\":32,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":3.2576195954206},{\"id\":\"1649551938\",\"fullname\":\"OAK FOREST URGENT\\/IMMEDIATE CARE CLINIC\",\"type\":\"emergency\",\"latitude\":\"41.871516\",\"longitude\":\"-87.673764\",\"fulladdress\":\"1900 W POLK ST,SUITE 1348,CHICAGO,IL,US,60612\",\"rating\":5,\"reviewCount\":33,\"primaryspecialty\":\"Clinic\\/Center\",\"allspecialty\":\"Clinic\\/Center,Urgent Care\",\"distance\":4.1760342831924},{\"id\":\"1003084112\",\"fullname\":\"SOUTH LOOP URGENT CARE\",\"type\":\"emergency\",\"latitude\":\"41.861879\",\"longitude\":\"-87.624027\",\"fulladdress\":\"1430 S MICHIGAN AVE,C2,CHICAGO,IL,US,60605\",\"rating\":5,\"reviewCount\":2,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":4.7465122267332}],\"status\":1}";
    String jsonData = "{\"npidata\":[{\"id\":\"1316388002\",\"fullname\":\"AMERICAN CURRENT CARE OF ILLINOIS PC\",\"type\":\"emergency\",\"latitude\":\"41.837642\",\"longitude\":\"-87.665741\",\"fulladdress\":\"3145 S ASHLAND AVE,SUITE 110,CHICAGO,IL,US,60608\",\"rating\":5,\"reviewCount\":23,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":2.0955347533003},{\"id\":\"1831388404\",\"fullname\":\"AMERICAN CURRENT CARE PA\",\"type\":\"emergency\",\"latitude\":\"41.837642\",\"longitude\":\"-87.665741\",\"fulladdress\":\"3145 S ASHLAND AVE,SUITE 110,CHICAGO,IL,US,60608\",\"rating\":2,\"reviewCount\":32,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care,Clinic\\/Center\",\"distance\":2.0955347533003},{\"id\":\"1285823856\",\"fullname\":\"AMERICAN CURRENT CARE PA\",\"type\":\"emergency\",\"latitude\":\"41.827173\",\"longitude\":\"-87.729449\",\"fulladdress\":\"4201 WEST 36TH STREET,CHICAGO,IL,US,60632\",\"rating\":1,\"reviewCount\":48,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":2.4116189665392},{\"id\":\"1790030385\",\"fullname\":\"HOMETOWN URGENT CARE\",\"type\":\"emergency\",\"latitude\":\"41.850000\",\"longitude\":\"-87.650000\",\"fulladdress\":\"7483 SOLUTIONS CTR,CHICAGO,IL,US,60677\",\"rating\":1,\"reviewCount\":11,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":3.2576195954206},{\"id\":\"1649551938\",\"fullname\":\"OAK FOREST URGENT\\/IMMEDIATE CARE CLINIC\",\"type\":\"emergency\",\"latitude\":\"41.871516\",\"longitude\":\"-87.673764\",\"fulladdress\":\"1900 W POLK ST,SUITE 1348,CHICAGO,IL,US,60612\",\"rating\":5,\"reviewCount\":5,\"primaryspecialty\":\"Clinic\\/Center\",\"allspecialty\":\"Clinic\\/Center,Urgent Care\",\"distance\":4.1760342831924},{\"id\":\"1003084112\",\"fullname\":\"SOUTH LOOP URGENT CARE\",\"type\":\"emergency\",\"latitude\":\"41.861879\",\"longitude\":\"-87.624027\",\"fulladdress\":\"1430 S MICHIGAN AVE,C2,CHICAGO,IL,US,60605\",\"rating\":5,\"reviewCount\":31,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":4.7465122267332},{\"id\":\"1316136955\",\"fullname\":\"AMERICAN CURRENT CARE PA\",\"type\":\"emergency\",\"latitude\":\"41.885493\",\"longitude\":\"-87.657431\",\"fulladdress\":\"1230 WEST LAKE STREET,CHICAGO,IL,US,60607\",\"rating\":3,\"reviewCount\":25,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":5.3108017758955},{\"id\":\"1992146286\",\"fullname\":\"AMERICAN CURRENT CARE OF ILLINOIS PC\",\"type\":\"emergency\",\"latitude\":\"41.885493\",\"longitude\":\"-87.657431\",\"fulladdress\":\"1230 W LAKE ST,CHICAGO,IL,US,60607\",\"rating\":2,\"reviewCount\":43,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":5.3108017758955},{\"id\":\"1902098742\",\"fullname\":\"AMERICAN CURRENT CARE PA\",\"type\":\"emergency\",\"latitude\":\"41.896287\",\"longitude\":\"-87.653970\",\"fulladdress\":\"1030 W CHICAGO AVE,CHICAGO,IL,US,60642\",\"rating\":1,\"reviewCount\":21,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":6.0763963142577},{\"id\":\"1952742645\",\"fullname\":\"AMERICAN CURRENT CARE OF ILLINOIS PC\",\"type\":\"emergency\",\"latitude\":\"41.896287\",\"longitude\":\"-87.653970\",\"fulladdress\":\"1030 W CHICAGO AVE,CHICAGO,IL,US,60642\",\"rating\":5,\"reviewCount\":15,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":6.0763963142577},{\"id\":\"1942690672\",\"fullname\":\"NORTHWESTERN MEMORIAL HEALTHCARE\",\"type\":\"emergency\",\"latitude\":\"41.900148\",\"longitude\":\"-87.674728\",\"fulladdress\":\"1030 N WOLCOTT AVE,CHICAGO,IL,US,60622\",\"rating\":2,\"reviewCount\":8,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":6.1300712966187},{\"id\":\"1902009186\",\"fullname\":\"HUMBOLDT PARK EMERGENCY SERVICES SC\",\"type\":\"emergency\",\"latitude\":\"41.900520\",\"longitude\":\"-87.699287\",\"fulladdress\":\"1044 N FRANCISCO AVE,CHICAGO,IL,US,60622\",\"rating\":3,\"reviewCount\":21,\"primaryspecialty\":\"Emergency Room\\/Emergency Care Clinic\",\"allspecialty\":\"Emergency Room\\/Emergency Care Clinic\",\"distance\":6.1523287613496},{\"id\":\"1376941849\",\"fullname\":\"MEDSPRING OF TEXAS, PA\",\"type\":\"emergency\",\"latitude\":\"41.896591\",\"longitude\":\"-87.634489\",\"fulladdress\":\"219 W CHICAGO AVE,CHICAGO,IL,US,60654\",\"rating\":1,\"reviewCount\":16,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":6.4509709567726},{\"id\":\"1386047397\",\"fullname\":\"ULTIMATE MEDICAL CARE P.C.\",\"type\":\"emergency\",\"latitude\":\"41.734185\",\"longitude\":\"-87.760559\",\"fulladdress\":\"5600 W 87TH ST,BURBANK,IL,US,60459\",\"rating\":1,\"reviewCount\":46,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":6.56520399928},{\"id\":\"1497060024\",\"fullname\":\"OAK LAWN IMMEDIATE CARE LLC\",\"type\":\"emergency\",\"latitude\":\"41.720364\",\"longitude\":\"-87.726173\",\"fulladdress\":\"4201 W 95TH ST,OAK LAWN,IL,US,60453\",\"rating\":1,\"reviewCount\":28,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":6.6362275427383},{\"id\":\"1861881831\",\"fullname\":\"MEDSPRING OF TEXAS, PA\",\"type\":\"emergency\",\"latitude\":\"41.908842\",\"longitude\":\"-87.677418\",\"fulladdress\":\"1520 N DAMEN AVE,CHICAGO,IL,US,60622\",\"rating\":1,\"reviewCount\":11,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":6.7158147804127},{\"id\":\"1033162086\",\"fullname\":\"LITTLE CO OF MARY CARE STATION\",\"type\":\"emergency\",\"latitude\":\"41.719723\",\"longitude\":\"-87.762079\",\"fulladdress\":\"5660 W 95TH ST,SUITE 1,OAK LAWN,IL,US,60453\",\"rating\":4,\"reviewCount\":6,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":7.4456312137839},{\"id\":\"1811256043\",\"fullname\":\"IMMEDIATE M.D., LLC\",\"type\":\"emergency\",\"latitude\":\"41.918361\",\"longitude\":\"-87.659890\",\"fulladdress\":\"2037 N CLYBOURN AVE,CHICAGO,IL,US,60614\",\"rating\":5,\"reviewCount\":35,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":7.4884242174144},{\"id\":\"1144516535\",\"fullname\":\"URGENT CARE MEDICAL SERVICE CORPORATION\",\"type\":\"emergency\",\"latitude\":\"41.917318\",\"longitude\":\"-87.726564\",\"fulladdress\":\"2004 N PULASKI RD,CHICAGO,IL,US,60639\",\"rating\":4,\"reviewCount\":1,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":7.5569498756929},{\"id\":\"1104237114\",\"fullname\":\"INNOVATIVE EXPRESS CARE, SC\",\"type\":\"emergency\",\"latitude\":\"41.925152\",\"longitude\":\"-87.668146\",\"fulladdress\":\"2400 N ASHLAND AVE,CHICAGO,IL,US,60614\",\"rating\":1,\"reviewCount\":32,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":7.885400508195},{\"id\":\"1740304757\",\"fullname\":\"FULLERTON OCCUPATIONAL MEDICINE & IMMEDIATE CARE SC\",\"type\":\"emergency\",\"latitude\":\"41.924658\",\"longitude\":\"-87.712407\",\"fulladdress\":\"3409 W FULLERTON AVE,CHICAGO,IL,US,60647\",\"rating\":4,\"reviewCount\":12,\"primaryspecialty\":\"Occupational Medicine\",\"allspecialty\":\"Occupational Medicine,Physical Therapy Clinic,Urgent Care\",\"distance\":7.8955404581902},{\"id\":\"1053752741\",\"fullname\":\"AMERICAN CURRENT CARE OF ILLINOIS PC\",\"type\":\"emergency\",\"latitude\":\"41.732249\",\"longitude\":\"-87.799053\",\"fulladdress\":\"8755 S HARLEM AVE,BRIDGEVIEW,IL,US,60455\",\"rating\":4,\"reviewCount\":2,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":7.9665878929493},{\"id\":\"1659560282\",\"fullname\":\"AMERICAN CURRENT CARE PA\",\"type\":\"emergency\",\"latitude\":\"41.732249\",\"longitude\":\"-87.799053\",\"fulladdress\":\"8755 SOUTH HARLEM AVENUE,BRIDGEVIEW,IL,US,60455\",\"rating\":3,\"reviewCount\":1,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":7.9665878929493},{\"id\":\"1790974368\",\"fullname\":\"AMERICAN CURRENT CARE PA\",\"type\":\"emergency\",\"latitude\":\"41.707528\",\"longitude\":\"-87.601415\",\"fulladdress\":\"900 EAST 103RD STREET,CHICAGO,IL,US,60628\",\"rating\":2,\"reviewCount\":7,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":8.4643428402393},{\"id\":\"1568803922\",\"fullname\":\"AMERICAN CURRENT CARE OF ILLINOIS PC\",\"type\":\"emergency\",\"latitude\":\"41.707528\",\"longitude\":\"-87.601415\",\"fulladdress\":\"900 E 103RD ST,CHICAGO,IL,US,60628\",\"rating\":5,\"reviewCount\":10,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":8.4643428402393},{\"id\":\"1861730590\",\"fullname\":\"MEDSPRING\",\"type\":\"emergency\",\"latitude\":\"41.934069\",\"longitude\":\"-87.644371\",\"fulladdress\":\"2868 N BROADWAY ST,CHICAGO,IL,US,60657\",\"rating\":2,\"reviewCount\":39,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":8.7240892007659},{\"id\":\"1255722120\",\"fullname\":\"MEDSPRING OF TEXAS, PA\",\"type\":\"emergency\",\"latitude\":\"41.941523\",\"longitude\":\"-87.668712\",\"fulladdress\":\"3301 N ASHLAND AVE,CHICAGO,IL,US,60657\",\"rating\":3,\"reviewCount\":31,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":9.0056637804731},{\"id\":\"1225098825\",\"fullname\":\"CONNERSVILLE IMMEDIATE CARE\",\"type\":\"emergency\",\"latitude\":\"41.945702\",\"longitude\":\"-87.688229\",\"fulladdress\":\"3542 N WESTERN AVE,CONNERSVILLE,IN,US,47331\",\"rating\":1,\"reviewCount\":35,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":9.2435095501882},{\"id\":\"1194062026\",\"fullname\":\"FRANCISCAN EXPRESSCARE\",\"type\":\"emergency\",\"latitude\":\"41.786666\",\"longitude\":\"-87.868519\",\"fulladdress\":\"20180 S LAGRANGE RD,FRANKFORT,IL,US,60423\",\"rating\":3,\"reviewCount\":2,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":9.4955982864158},{\"id\":\"1477845279\",\"fullname\":\"DOCTORS EXPRESS\",\"type\":\"emergency\",\"latitude\":\"41.954201\",\"longitude\":\"-87.675634\",\"fulladdress\":\"1843 W IRVING PARK RD,SCHAUMBURG,IL,US,60193\",\"rating\":5,\"reviewCount\":47,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":9.849016111244},{\"id\":\"1073718748\",\"fullname\":\"RESURRECTION HEALTH CARE\",\"type\":\"emergency\",\"latitude\":\"41.892501\",\"longitude\":\"-87.847072\",\"fulladdress\":\"1111 SUPERIOR ST,#207,MELROSE PARK,IL,US,60160\",\"rating\":1,\"reviewCount\":16,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":9.9297469657927},{\"id\":\"1962838771\",\"fullname\":\"MELROSE PARK IMMEDIATE CARE\",\"type\":\"emergency\",\"latitude\":\"41.891184\",\"longitude\":\"-87.856547\",\"fulladdress\":\"106 N 19TH AVE,MELROSE PARK,IL,US,60160\",\"rating\":2,\"reviewCount\":41,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":10.288519076164},{\"id\":\"1881763449\",\"fullname\":\"NORTHERN ILLINOIS EMERGENCY & OCCP MED SPEC\",\"type\":\"emergency\",\"latitude\":\"41.908286\",\"longitude\":\"-87.843677\",\"fulladdress\":\"701 W NORTH AVE,MELROSE PARK,IL,US,60160\",\"rating\":2,\"reviewCount\":12,\"primaryspecialty\":\"Emergency Room\\/Emergency Care Clinic\",\"allspecialty\":\"Emergency Room\\/Emergency Care Clinic\",\"distance\":10.443909958244},{\"id\":\"1811261290\",\"fullname\":\"MOHANA URGENT CARE LTD\",\"type\":\"emergency\",\"latitude\":\"41.699102\",\"longitude\":\"-87.535102\",\"fulladdress\":\"3641 E 108TH ST,STE B,CHICAGO,IL,US,60617\",\"rating\":4,\"reviewCount\":22,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":11.060867100976},{\"id\":\"1235570920\",\"fullname\":\"AMERICAN CURRENT CARE OF ILLINOIS PC\",\"type\":\"emergency\",\"latitude\":\"41.930123\",\"longitude\":\"-87.878780\",\"fulladdress\":\"10137 GRAND AVE,FRANKLIN PARK,IL,US,60131\",\"rating\":3,\"reviewCount\":26,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":12.796332612395},{\"id\":\"1104015726\",\"fullname\":\"AMERICAN CURRENT CARE PA\",\"type\":\"emergency\",\"latitude\":\"41.930123\",\"longitude\":\"-87.878780\",\"fulladdress\":\"10137 GRAND AVE,FRANKLIN PARK,IL,US,60131\",\"rating\":1,\"reviewCount\":16,\"primaryspecialty\":\"Urgent Care\",\"allspecialty\":\"Urgent Care\",\"distance\":12.796332612395}],\"status\":1}";

    public String[] firstName, providerNpiID, lat, longg, address;
    GoogleMap map;
    TextView middle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_slider);
        middle = (TextView) findViewById(R.id.TV_middle);
        ArrayList<String> stringArray = new ArrayList<String>();

        // ListView Item
        for (int i=0; i<50 ; i++)
            stringArray.add(i + " no Item");

        //find top Linear Layout container
        ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.LL_top);
        //Load child/includable XML
        View child1 = LayoutInflater.from(this).inflate(R.layout.map_view, null);
        //add map child/includable view
        inclusionViewGroup.addView(child1);

        Double providerLatitude = 0.0, providerLongitude = 0.0;
        //map
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        try 
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("npidata");

            //set Array Size;
            firstName = new String[jsonArray.length()];
            providerNpiID = new String[jsonArray.length()];
            lat = new String[jsonArray.length()];
            longg = new String[jsonArray.length()];
            address = new String[jsonArray.length()];

            //set counted search result
            middle.setText("  " + jsonArray.length() + " Data found");

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
                    Log.d("CRASH 141 ", e.toString());
                }

                providerLatitude = Double.parseDouble(lat[i]);
                providerLongitude = Double.parseDouble(longg[i]);
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(providerLatitude, providerLongitude))
                                .title(firstName[i])
                                .snippet(address[i])
                        /*.icon(IconMarkerplot)*/);
            }

            //1st marker as Center position
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(Double.parseDouble(lat[0]), Double.parseDouble(longg[0])), 9));
        }
        catch (Exception e)
        {
            Log.d("CRASH 161 ", e.toString());
        }

        //Listview Code
        ListView LV_bottom = (ListView) findViewById(R.id.LV_bottom);
        LV_bottom.setAdapter(new ProviderSearchResultAdapter(getApplicationContext()));
    }
    /* ****** Custom BaseAdapter ****** */
    public class ProviderSearchResultAdapter extends BaseAdapter
    {
        Context context;

        public ProviderSearchResultAdapter(Context context)
        {
            this.context = context;
        }

        @Override
        public int getCount()
        {
            return firstName.length;
        }

        @Override
        public Object getItem(int position)
        {
            return position;
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public int getViewTypeCount()
        {
            return getCount();
        }

        @Override
        public int getItemViewType(int position)
        {
            return position;
        }
        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.list_item_adapter, null);


            TextView FirstName = (TextView) convertView.findViewById(R.id.provider);
            TextView BusinessAddress = (TextView) convertView.findViewById(R.id.provider_address);

            FirstName.setText(firstName[position]+"");
            BusinessAddress.setText(address[position]+"");
            return convertView;
        }
    }
}
