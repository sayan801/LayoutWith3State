package app.com.simplesliderdemo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class SimpleSliderActivity extends FragmentActivity
{
    LinearLayout LL_top, LL_bottom, LL_middle;
    int DeviceTotalWidth, DeviceTotalHeight;
    int step = 33; // Initial value shoud be 33 for Default State
    int noOfsteate = 99; // used 99 for '3' step, usded 66 for '2' step

    //for Google map
    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_slider);

        LL_top = (LinearLayout) findViewById(R.id.LL_top);
        LL_middle = (LinearLayout) findViewById(R.id.LL_middle);
        LL_bottom = (LinearLayout) findViewById(R.id.LL_bottom);

        DisplayMetrics metrics = getResources().getDisplayMetrics();

         DeviceTotalWidth = metrics.widthPixels;
         DeviceTotalHeight = metrics.heightPixels;
        //Log.d("DeviceTotalHeight <> ", DeviceTotalHeight+" <> " +DeviceTotalHeight*10/100 + " <> "+DeviceTotalHeight*45/100);

        //During Launch  set default value
        LL_bottom.getLayoutParams().height= (int) (DeviceTotalHeight*45/100);
        LL_middle.getLayoutParams().height= (int) (DeviceTotalHeight*8/100);
        LL_top.getLayoutParams().height= (int) (DeviceTotalHeight*45/100);

         LL_middle.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View view)
             {
                 // onClick change the State..by increasing value programetically
                 step = step + 33;

                 if (step == 33)
                 {
                     //for Default view
                     LL_top.setVisibility(View.VISIBLE);
                     LL_bottom.setVisibility(View.VISIBLE);

                     LL_bottom.getLayoutParams().height = (int) (DeviceTotalHeight * 45 / 100);
                     LL_middle.getLayoutParams().height = (int) (DeviceTotalHeight * 8 / 100);
                     LL_top.getLayoutParams().height = (int) (DeviceTotalHeight * 45 / 100);
                 }
                 else if (step == 66)
                 {
                     //slider Top positon
                     LL_top.setVisibility(View.GONE);
                     LL_bottom.setVisibility(View.VISIBLE);

                     LL_bottom.getLayoutParams().height = (int) (DeviceTotalHeight * 90 / 100);
                     LL_middle.getLayoutParams().height = (int) (DeviceTotalHeight * 10 / 100);
                 }
                 else
                 {
                     //slider should be Buttom positon
                     LL_top.setVisibility(View.VISIBLE);
                     LL_bottom.setVisibility(View.GONE);

                     LL_top.getLayoutParams().height = (int) (DeviceTotalHeight * 90 / 100);
                     LL_middle.getLayoutParams().height = (int) (DeviceTotalHeight * 10 / 100);
                 }

                 //continue this Loop
                 if (step == noOfsteate)
                     step = 0; // moved to initial State
             }
         });

        ArrayList<String> stringArray = new ArrayList<String>();

        for (int i=0; i<50 ; i++)
        stringArray.add(i + " no Item");
        //Listview Code
        ArrayAdapter modeAdapter = new ArrayAdapter(this, R.layout.list_item_adapter, R.id.provider, stringArray);
        ListView LV_bottom = (ListView) findViewById(R.id.LV_bottom);
        LV_bottom.setAdapter(modeAdapter);
    }
}
