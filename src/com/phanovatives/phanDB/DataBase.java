/**
 * See the file "LICENSE" for the full license governing this code.
 */
package com.phanovatives.phanDB;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBase {
    /*********************************************************************/    
    public ArrayList<Table>     tables      = new ArrayList<Table>();
    public String name;
    public SQLiteDatabase database;
    public boolean debug=true;
    /*********************************************************************/    
    public DataBase(Context ctx,String strName){
        name=strName;
        database = ctx.openOrCreateDatabase(strName, 0,null);
    }
    /*********************************************************************/
    public Table create_table(String strName){
    	Table table=new Table(database,strName);
    	tables.add(table);    	    	
    	return table;
    }
    /*********************************************************************/
    public void save(){
    	    	
        for (int i=0;i<tables.size();i++){
        	// Get the current table
        	Table table=tables.get(i);
        	
        	// Init SQL string
        	String strSQL="create table if not exists "+table.name+"(";
        	
        	if (debug)
        		Log.d("<phanDB-Table>",table.name);
        	
        	// Create id col
        	strSQL=strSQL+"id integer primary key";
        	
        	// Loop thru all columns
        	for (int j=0;j<table.columns.size();j++){
        		// Get the current column
        		Column col=table.columns.get(j);
        		
        		// Add column creating string
        		strSQL=strSQL + "," + col.name + " " +col.type;
        		if (debug)
        			Log.d("<phanDB-Column>",col.name);
        	}
        	// Finalize the SQL string
        	strSQL=strSQL+");";
        	
        	//Execute the SQL String        	
        	database.execSQL(strSQL);
        	
        	if (debug)
        		Log.d("<phanDB-SQL>",strSQL);
        }    	
    }
    /*********************************************************************/                
}
