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
        
        tTask.find_all(null, null, null, null, null);
        
        
        // Add some data
        tTask.set_column("name1", "lolo");
        tTask.set_column("created_at1", "1");
        tTask.set_column("updated_at1", "2");        
        tTask.create_record(true);
        
        
        tTask.find_all(null, null, null, null, null);
        
        db.print();
        db.close();
    }
    
    
    public class Table1{
    	public String name;
    }
}