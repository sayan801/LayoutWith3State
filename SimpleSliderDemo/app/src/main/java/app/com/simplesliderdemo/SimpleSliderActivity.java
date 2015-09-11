package app.com.simplesliderdemo;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class SimpleSliderActivity extends FragmentActivity
{

//    TextView middle;
    LinearLayout top, bottom, middle;
    int DeviceTotalWidth, DeviceTotalHeight;
    int i = 33; // Initial value shoud be 33 for Default State

    //for Google map
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_slider);

        top = (LinearLayout) findViewById(R.id.LL_top);
        middle = (LinearLayout) findViewById(R.id.LL_middle);
        bottom = (LinearLayout) findViewById(R.id.LL_bottom);

        DisplayMetrics metrics = getResources().getDisplayMetrics();

         DeviceTotalWidth = metrics.widthPixels;
         DeviceTotalHeight = metrics.heightPixels;
        //Log.d("DeviceTotalHeight <> ", DeviceTotalHeight+" <> " +DeviceTotalHeight*10/100 + " <> "+DeviceTotalHeight*45/100);

        //During Launch  set default value
        bottom.getLayoutParams().height= (int) (DeviceTotalHeight*45/100);
        middle.getLayoutParams().height= (int) (DeviceTotalHeight*8/100);
        top.getLayoutParams().height= (int) (DeviceTotalHeight*45/100);

         middle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // onClick change the State..by increasing value programetically
                i = i+33;

                if(i == 33)
                {
                    //for Default view
                    top.setVisibility(View.VISIBLE);
                    bottom.setVisibility(View.VISIBLE);

                    bottom.getLayoutParams().height= (int) (DeviceTotalHeight*45/100);
                    middle.getLayoutParams().height= (int) (DeviceTotalHeight*8/100);
                    top.getLayoutParams().height= (int) (DeviceTotalHeight*45/100);
                }
                else if(i == 66)
                {
                    //slider Top positon
                    top.setVisibility(View.GONE);
                    bottom.setVisibility(View.VISIBLE);

                    bottom.getLayoutParams().height= (int) (DeviceTotalHeight*90/100);
                    middle.getLayoutParams().height= (int) (DeviceTotalHeight*10/100);
                }
                else
                {
                    //slider should be Buttom positon
                    top.setVisibility(View.VISIBLE);
                    bottom.setVisibility(View.GONE);

                    top.getLayoutParams().height= (int) (DeviceTotalHeight*90/100);
                    middle.getLayoutParams().height= (int) (DeviceTotalHeight*10/100);
                }

                //continue this Loop
                if(i == 99)
                    i=0;
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
