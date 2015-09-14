package app.com.simplesliderdemo;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class SimpleSliderActivity extends FragmentActivity
{
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
        valueTV.setTextColor(Color.GREEN);
        valueTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        linearLayout.addView(valueTV);
    }
}
