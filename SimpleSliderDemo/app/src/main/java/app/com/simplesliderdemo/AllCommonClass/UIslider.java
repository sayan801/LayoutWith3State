package app.com.simplesliderdemo.AllCommonClass;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import app.com.simplesliderdemo.R;

/**
 * Created by technicise4 on 11/9/15.
 * @author Amitabha
 */
public class UIslider extends LinearLayout
{
    //Globally Declare
    LinearLayout LL_top, LL_bottom, LL_middle;
    int DeviceTotalWidth, DeviceTotalHeight;
    int step = 33; // Initial value shoud be 33 for Default State
    int noOfsteate = 99; // used 99 for '3' step, usded 66 for '2' step

    public UIslider(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initLoad(context);
    }

    public UIslider(Context context)
    {
        super(context);
        initLoad(context);
    }

    public UIslider(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initLoad(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UIslider(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        initLoad(context);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

        //set Slider state Dynamically
        if(getTag().toString().matches(""))
        {
            noOfsteate = 99;
        }
        else if(getTag().toString().matches("2"))
        {
            noOfsteate = 66;
        }
        else if(getTag().toString().matches("3"))
        {
            noOfsteate = 99;
        }
        else
        {
            noOfsteate = 99;
        }

        //find the view Control
        LL_top = (LinearLayout) findViewById(R.id.LL_top);
        LL_middle = (LinearLayout) findViewById(R.id.LL_middle);
        LL_bottom = (LinearLayout) findViewById(R.id.LL_bottom);

        DisplayMetrics metrics = getResources().getDisplayMetrics();

        DeviceTotalWidth = metrics.widthPixels;
        DeviceTotalHeight = metrics.heightPixels;
        //Log.d("DeviceTotalHeight <> ", DeviceTotalHeight+" <> " +DeviceTotalHeight*10/100 + " <> "+DeviceTotalHeight*45/100);

        //During Launch  set default value
        LL_bottom.getLayoutParams().height= (int) (DeviceTotalHeight * 45/100);
        LL_middle.getLayoutParams().height= (int) (DeviceTotalHeight * 8/100);
        LL_top.getLayoutParams().height= (int) (DeviceTotalHeight * 45/100);

        LL_middle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //used as common method
                commonMethodForChangeState();
            }
        });

    }
    public void initLoad(Context context)
    {
//        Activity activity = (Activity) context;//casting context into activity
        LayoutInflater.from(context).inflate(R.layout.ui_slider_common_control, this, true);
        /** after Loading complete of this... then automatically call 'onFinishInflate()' method. **/
    }

    public void commonMethodForChangeState()
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
            step = 0; //moved to initial State
    }
}
