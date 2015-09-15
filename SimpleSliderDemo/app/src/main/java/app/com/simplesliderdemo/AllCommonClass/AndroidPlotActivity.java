package app.com.simplesliderdemo.AllCommonClass;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.androidplot.xy.XYPlot;

import app.com.simplesliderdemo.R;

public class AndroidPlotActivity extends Activity
{
    public XYPlot mySimpleXYPlot;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_plot);

        //find TOP Linear Layout to add container
        ViewGroup inclusionViewGroup_top = (ViewGroup)findViewById(R.id.LL_top);
        //Load child/includable XML

        //  View child1 = LayoutInflater.from(this).inflate(R.layout.map_view, null);
        //Load child/includable XML -> sub controler
        View childLayout_map = LayoutInflater.from(this).inflate(R.layout.xy_plot,
                (ViewGroup) findViewById(R.id.mySimpleXYPlot));

        //add map child/includable view
        inclusionViewGroup_top.addView(childLayout_map);
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
}
