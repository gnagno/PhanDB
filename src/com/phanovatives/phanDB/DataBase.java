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
    public void close() {       
        database.close();
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
        	table.columns.add(0, new Column("id","integer primary key"));
        	
        	
        	// Init SQL string
        	String strSQL="create table if not exists "+table.name+"(";
        	
        	if (debug)
        		Log.d("<phanDB-Table>",table.name);
        	
        	// Loop thru all columns
        	for (int j=0;j<table.columns.size();j++){
        		// Get the current column
        		Column col=table.columns.get(j);
        		
        		// Add column creating string
        		strSQL=strSQL + col.name + " " +col.type;
        		
        		// If not the last column
        		if (j<(table.columns.size()-1)){
        			strSQL=strSQL + ",";
        		}
        		
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
    public void print(){
        for (Table table:tables){
        	Log.d("<Table>",table.name);        	
        	for (Record record:table.records){        		
        		Log.d("======<Record>",record.toString());
        		for (Column col:record.columns){
        			Log.d("======<Column NAME>",		col.name);
        			Log.d("======<Column VALUE>",	col.value);
        		}
        	}        	
        }
    	
    }
    /*********************************************************************/
}
