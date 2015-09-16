package app.com.simplesliderdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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

import app.com.simplesliderdemo.AllCommonClass.AndroidPlotActivity;
import app.com.simplesliderdemo.AllCommonClass.ServiceHandler;
import app.com.simplesliderdemo.AllCommonClass.UIslider;

public class SimpleSliderActivity extends FragmentActivity implements View.OnClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener
{
    Double providerLatitude = 0.0, providerLongitude = 0.0;
    String jsonResponse, url1;

    public String[] firstName, providerNpiID, lat, longg, address;
    GoogleMap map;
    TextView middle;
    ListView listView;// = new ListView(this);
    Button btn400Data, btn10kData, btnGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_slider);
        middle = (TextView) findViewById(R.id.TV_middle);

        btn400Data = (Button) findViewById(R.id.BTN_400_data);
        btn10kData = (Button) findViewById(R.id.BTN_10k_data);
        btnGraph = (Button) findViewById(R.id.BTN_graph);

        //find TOP Linear Layout to add container
        ViewGroup inclusionViewGroup_top = (ViewGroup)findViewById(R.id.LL_top);
        //Load child/includable XML

        //  View child1 = LayoutInflater.from(this).inflate(R.layout.map_view, null);
        //Load child/includable XML -> sub controler
        View childLayout_map = LayoutInflater.from(this).inflate(R.layout.map_view,
                (ViewGroup) findViewById(R.id.map));

        //add map child/includable view
        inclusionViewGroup_top.addView(childLayout_map);

        //BOTTOM view
        //find Bottom Linear Layout container
        ViewGroup inclusionViewGroup_bottom = (ViewGroup)findViewById(R.id.LL_bottom);

        listView = new ListView(this);
        listView.setId(R.id.layout1);
        listView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        //add map child/includable view
        inclusionViewGroup_bottom.addView(listView);


        //map
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if(map != null)
        {
            map.setOnMarkerClickListener(this);
            map.setOnInfoWindowClickListener(this);
        }
    }

    @Override
    public void onClick(View v)
    {

    }

    @Override
    public void onInfoWindowClick(Marker marker)
    {

    }

    @Override
    public boolean onMarkerClick(Marker marker)
    {
        try
        {
            marker.showInfoWindow();
            String m = marker.getId();
            m = m.replace("m","");
            final int alfaValue =  Integer.valueOf(m);
//            alfaValue = i ; //assign value

            //auto-Scroll the ListView position
            listView.smoothScrollToPositionFromTop(alfaValue, alfaValue);
            //programatically click on ListView row Item for corresponding marker Click
            listView.performItemClick(listView.getAdapter().getView(alfaValue, null, null), alfaValue, listView.getItemIdAtPosition(alfaValue));

        }
        catch (Exception error)
        {
            Log.d("CRASH","CRASH 485 > "+error.toString());
            error.printStackTrace();
        }
        return false;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_android_plot, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** * Private Class for Parsing the JSON over the Internet.  */
    private class ProviderSearchPareJSONdataAsyntask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {

        }

        @Override
        protected Void doInBackground(Void... params)
        {
            ServiceHandler serviceHandler = new ServiceHandler();

            // Making a request to url and getting the response
            jsonResponse = serviceHandler.makeServiceCall(url1, ServiceHandler.GET);

            Log.d("jsonResponse", jsonResponse + " <>");


            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            try
            {
                JSONObject jsonObject = new JSONObject(jsonResponse);
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
                    if(map != null)
                    {
                        map.addMarker(new MarkerOptions()
                                        .position(new LatLng(providerLatitude, providerLongitude))
                                        .title(firstName[i])
                                        .snippet(address[i])
                        /*.icon(IconMarkerplot)*/);
                    }


                }

                if(map != null)
                {
                    //1st marker as Center position
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                            new LatLng(Double.parseDouble(lat[0]), Double.parseDouble(longg[0])), 9));
                }

            }
            catch (Exception e)
            {
                Log.d("CRASH 161 ", e.toString());
            }



            //Listview Code
            listView = (ListView) findViewById(R.id.layout1);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> argo, View view, int i, long l)
                {
                    try
                    {
                        Toast.makeText(getApplicationContext(),"Click on " + i,Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception excp)
                    {
                        //
                    }
                }
            });
            listView.setAdapter(new ProviderSearchResultAdapter(getApplicationContext()));

            //set Default Button text
            btn400Data.setText("400Data");
            btn10kData.setText("10kData");

            btn400Data.setEnabled(true);
            btn10kData.setEnabled(true);
            btnGraph.setEnabled(true);
        }
    }
    public void goto_android_plot(View view)
    {
        Intent intent = new Intent(getApplicationContext(), AndroidPlotActivity.class);
        startActivity(intent);
    }
    public void goto_400_data(View view)
    {
        btn400Data.setText("Wait..");

        btn400Data.setEnabled(false);
        btn10kData.setEnabled(false);
        btnGraph.setEnabled(false);
        url1 =
                "http://webservice.mycuratio.com/webservice/code/index.php?/ProviderNew/getProviderByPartialNameZipDistance/john/60601/10"; //400
        new ProviderSearchPareJSONdataAsyntask().execute();
    }
    public void goto_10k_data(View view)
    {
        btn10kData.setText("Wait..");

        btn400Data.setEnabled(false);
        btn10kData.setEnabled(false);
        btnGraph.setEnabled(false);

        url1 =
                "http://webservice.mycuratio.com/webservice/code/index.php?/ProviderNew/getProviderByPartialNameZipDistance/-1/60601/5"; //10k
        new ProviderSearchPareJSONdataAsyntask().execute();
    }
}
