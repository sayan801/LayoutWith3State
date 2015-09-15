package app.com.simplesliderdemo.AllCommonClass;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.androidplot.xy.XYPlot;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.simplesliderdemo.R;

public class AndroidPlotActivity extends Activity
{
    ExpandableListView elv_android;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_plot);

        elv_android = new ExpandableListView(this);
        //find TOP Linear Layout to add container
        ViewGroup inclusionViewGroup_top = (ViewGroup)findViewById(R.id.LL_top);
        //Load child/includable XML

        //  View child1 = LayoutInflater.from(this).inflate(R.layout.map_view, null);
        //Load child/includable XML -> sub controler
        View childLayout_map = LayoutInflater.from(this).inflate(R.layout.xy_plot,
                (ViewGroup) findViewById(R.id.mySimpleXYPlot));

        //add map child/includable view
        inclusionViewGroup_top.addView(childLayout_map);

        //BOTTOM view
        //find Bottom Linear Layout container
        ViewGroup inclusionViewGroup_bottom = (ViewGroup)findViewById(R.id.LL_bottom);

        elv_android.setId(R.id.EX_LV_plot);
        elv_android.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //add map child/includable view
        inclusionViewGroup_bottom.addView(elv_android);

        ExpandableCollection.key_value = new ArrayList<String>();
        ExpandableCollection.expandable_main_arr = new ArrayList<ExpandableCollection>();
        ExpandableCollection.expandable_hashmap = new HashMap<String, ArrayList<ExpandableCollection>>();

        for(int i=0; i<50; i++)
        {
            ExpandableCollection.key_value.add("Insect <> "+i);

            ArrayList<ExpandableCollection> arr_obj3 = new ArrayList<ExpandableCollection>();

            arr_obj3.add(new ExpandableCollection("Open Sky"+i, "Okkbbder", R.drawable.ic_cast_dark));

            ExpandableCollection.expandable_hashmap.put(ExpandableCollection.key_value.get(i), arr_obj3);

        }

        AndroidExpandableListAdapter adapter = new AndroidExpandableListAdapter(AndroidPlotActivity.this, AndroidPlotActivity.this,
                ExpandableCollection.key_value,
                ExpandableCollection.expandable_hashmap);
        elv_android.setAdapter(adapter);

        elv_android.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                if (ExpandableCollection.key_value != null) {
                    if (ExpandableCollection.key_value.size() > groupPosition) {
                        String key = ExpandableCollection.key_value.get(groupPosition);

                        if (ExpandableCollection.expandable_hashmap.size() > 0) {
                            ExpandableCollection obj_exp = ExpandableCollection.expandable_hashmap
                                    .get(key).get(childPosition);

                            Toast.makeText(AndroidPlotActivity.this, "Details :" + obj_exp.message,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
                return false;
            }
        });


	  /*
        * Below Functionality Holds When ListView Expand or Collapse
        *
        * */

        elv_android.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()

                                            {

                                                @Override
                                                public boolean onGroupClick(ExpandableListView parent, View v,
                                                                            int groupPosition, long id) {

                                                    if (parent.isGroupExpanded(groupPosition)) {

                    /*Lower_Horizontal_Line = (View) v.findViewById(R.id.Lower_Horizontal_Line);
                    Lower_Horizontal_Line.setVisibility(View.INVISIBLE);*/

			/*ImageView Image_Group_Indicator = (ImageView) v.findViewById(R.id.ListView_Group_Indicator);
			Image_Group_Indicator.setImageDrawable(getResources().getDrawable(R.drawable.openindicator));*/

                                                        Toast.makeText(getApplicationContext(),"+++++",Toast.LENGTH_SHORT).show();
                                                    } else {

                  /*TextView  TextView = (TextView) v.findViewById(R.id.textView2);
				 TextView.setVisibility(View.INVISIBLE);*/
			/*ImageView Image_Group_Indicator = (ImageView) v.findViewById(R.id.ListView_Group_Indicator);
			Image_Group_Indicator.setImageDrawable(getResources().getDrawable(R.drawable.collapsedindicator));
*/
                                                        Toast.makeText(getApplicationContext(),"-----------",Toast.LENGTH_SHORT).show();
                                                    }


                                                    return false;
                                                }
                                            }

        );

        elv_android.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()

                                             {
                                                 int previousGroup = -1;
                                                 @Override
                                                 public void onGroupExpand(int groupPosition) {

                                                     if(groupPosition != previousGroup)
                                                         elv_android.collapseGroup(previousGroup);
                                                     previousGroup = groupPosition;

                                                 }
                                             }

        );


        elv_android.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener()

                                               {
                                                   @Override
                                                   public void onGroupCollapse(int groupPosition) {

                                                   }
                                               }

        );


        elv_android.setOnChildClickListener(new ExpandableListView.OnChildClickListener()

        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Toast.makeText(getApplicationContext(), "++++", Toast.LENGTH_SHORT).show();

                return false;
            }
        });


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
