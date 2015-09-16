package app.com.simplesliderdemo.AllCommonClass;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.simplesliderdemo.R;

public class AndroidExpandableListAdapter extends BaseExpandableListAdapter {

	Context ctx;
	Activity act;
	ArrayList<String> headerCollectionArr;
	HashMap<String, ArrayList<ExpandableCollection>> holderHash;
//	ArrayList<ProgressItem> progressItemList;
//	ProgressItem mProgressItem;
	
	
	public AndroidExpandableListAdapter(Context ctx, Activity act,
										ArrayList<String> headerCollectionArr,
										HashMap<String, ArrayList<ExpandableCollection>> holderHash) {
		this.ctx = ctx;
		this.headerCollectionArr = headerCollectionArr;
		this.holderHash = holderHash;
		this.act=act;
	}
	

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		String st=headerCollectionArr.get(groupPosition);
	
		Object obj=null;
		if (holderHash.size()>0) {
			obj	=holderHash.get(st).get(childPosititon);
		}
		
		return obj;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ExpandableCollection exp_obj =  (ExpandableCollection) getChild(groupPosition, childPosition);

//		final String childText = (String) getChild(groupPosition, childPosition);
		
		ChildViewHolder viewHolder=new ChildViewHolder();
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.class_booking_item, null);
			viewHolder.tv_title= (TextView) convertView
					.findViewById(R.id.tv_inner_list);
			
			viewHolder.iv_image=(ImageView) convertView
					.findViewById(R.id.iv_image);
			
				convertView.setTag(viewHolder);
			
			
		}else
		{
			viewHolder = (ChildViewHolder) convertView.getTag();
		}


		viewHolder.tv_title.setText(exp_obj.title);	
		viewHolder.iv_image.setImageResource(exp_obj.image);

//		CustomSeekBar seekBar = (CustomSeekBar) convertView.findViewById(R.id.SeekBar_Vital_Sections);
//
//		initDataToSeekbar(seekBar);
//		//int pro = Integer.parseInt(childText);
//		seekBar.setProgress(groupPosition*2);


		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		String st=headerCollectionArr.get(groupPosition);
		int i = 0;
		if (holderHash.size()>0) {
			ArrayList<ExpandableCollection> ll=holderHash.get(st);
		if (ll!=null) {
			i=ll.size();	
		}else{
			
			i=0;
		}
			
		}
		return i;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return headerCollectionArr.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return headerCollectionArr.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String)getGroup(groupPosition);
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater)ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.expended_header, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.tv_header);
		ImageView image = (ImageView) convertView.findViewById(R.id.iv_exp);
		
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);


		
			int imageResourceId = isExpanded ? R.drawable.ic_plusone_medium_off_client : R.drawable.ic_plusone_medium_off_client;
			image.setImageResource(imageResourceId);
			
			image.setVisibility(View.VISIBLE);
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	
	public class ChildViewHolder {

		TextView tv_title;
		ImageView iv_image;


	}

//	private void initDataToSeekbar(CustomSeekBar seekBar)
//	{
//
//		float TotalSpan = 60;
//		float BlueSpan = 20;
//		float GreenSpan = 20;
//		float OrangeSpan = 20;
//
//		progressItemList = new ArrayList<ProgressItem>();
//
//		mProgressItem = new ProgressItem();
//		mProgressItem.progressItemPercentage = ((BlueSpan / TotalSpan) * 100);
//		mProgressItem.color = R.color.red;
//		progressItemList.add(mProgressItem);
//
//		mProgressItem = new ProgressItem();
//		mProgressItem.progressItemPercentage = (GreenSpan / TotalSpan) * 100;
//		mProgressItem.color = R.color.yellow;
//		progressItemList.add(mProgressItem);
//
//		mProgressItem = new ProgressItem();
//		mProgressItem.progressItemPercentage = (OrangeSpan / TotalSpan) * 100;
//		mProgressItem.color = R.color.green;
//		progressItemList.add(mProgressItem);
//
//		seekBar.initData(progressItemList);
//		seekBar.invalidate();
//
//	}
	
}
