package app.com.simplesliderdemo.AllCommonClass;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import app.com.simplesliderdemo.R;

/**
 * Created by technicise4 on 11/9/15.
 */
public class UIslider extends LinearLayout
{
    public UIslider(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }
    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

    }
    public void init(Context context)
    {
        Activity activity = (Activity) context;//casting context into activity
        LayoutInflater.from(context).inflate(R.layout.ui_slider_common_control, this, true);
    }
}
