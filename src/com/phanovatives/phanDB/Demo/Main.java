package com.phanovatives.phanDB.Demo;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.phanovatives.phanDB.*;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        DataBase db = new DataBase(this,"Database1");
        
        
        // Create table
        Table tTask = db.create_table("tasks");        
        tTask.create_column("name1",		"varchar(255)");
        tTask.create_column("created_at1",	"varchar(255)");
        tTask.create_column("updated_at1",	"varchar(255)");                
                

        // Create table
        Table tTags = db.create_table("tags");        
        tTags.create_column("name2",		"varchar(255)");
        tTags.create_column("created_at2",	"varchar(255)");
        tTags.create_column("updated_at2",	"varchar(255)");                

        db.save();
        
        
        // Add some data
        tTask.columns.get(0).value="blah";
        tTask.columns.get(1).value="01";
        tTask.columns.get(2).value="02";        
        tTask.create_record();
        tTask.save();
        
        // Add some data
        tTags.columns.get(0).value="blah";
        tTags.columns.get(1).value="11";
        tTags.columns.get(2).value="12";        
        tTags.create_record();
        tTags.save();
        

        
        /***********************************************************************/
        /*for (int i=0;i<db.tables.size();i++){
        	Table t=db.tables.get(i);
        	Log.d("<Table>",t.name);
        	
        	for (int j=0;j<t.columns.size();j++){
        		Column col=t.columns.get(j);
        		Log.d("======<Column>",col.name);
        	}
        	
        	
        	for (int j=0;j<t.records.size();j++){
        		Record rec=t.records.get(j);        		
        		Log.d("======<Record>",rec.toString());
        		for (int k=0;k<rec.columns.size();k++){
        			Log.d("======<Column NAME>",rec.columns.get(k).name);
        			Log.d("======<Column VALUE>" ,rec.columns.get(k).value);
        		}
        	}        	
        }*/
        /***********************************************************************/
    }
    
    
    public class Table1{
    	public String name;
    }
}