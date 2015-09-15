package app.com.simplesliderdemo.AllCommonClass;

import java.util.ArrayList;
import java.util.HashMap;


public class ExpandableCollection {
	
	public String title="";
	public String message="";
	public int image=0;
	
	
	public static HashMap<String, ArrayList<ExpandableCollection>> expandable_hashmap;
	public static ArrayList<ExpandableCollection> expandable_main_arr=null;
    public static ArrayList<String> key_value=null;
    
	
public ExpandableCollection(String title, String message, int image){
	
	this.title=title;
	this.message=message;
	this.image=image;
	}


}
