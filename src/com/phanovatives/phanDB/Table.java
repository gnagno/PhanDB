package com.phanovatives.phanDB;
import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Table{
    /*********************************************************************/    
    public ArrayList<Record>     records      = new ArrayList<Record>();
    public ArrayList<Column>     columns      = new ArrayList<Column>();
    
    public 	String 			name;
    public 	boolean 		debug=true;
    private SQLiteDatabase 	database;
    /*********************************************************************/    
    public Table(SQLiteDatabase db,String strName){
        name=strName;
        database=db;
    }
    /*********************************************************************/
	public Record create_record(){
    	ArrayList<Column> cols  = new ArrayList<Column>();
    	
    	for(int i=0;i<columns.size();i++){
    		Column col=new Column(columns.get(i).name,columns.get(i).type);
    		col.value=columns.get(i).value;    		
    		cols.add(col);
    	}
    	    	
        Record record = new Record(cols);
        records.add(record);
        return record;
    }
    /*********************************************************************/
    public Column create_column(String strName,String strType){
    	Column column= new Column(strName,strType);
    	columns.add(column);
    	return column;
    }
    /*********************************************************************/    
    public void save(){
    	for (int j=0;j<records.size();j++){
    		// Init SQL String
    		String strSQL="insert into "+name+" ";
    		String strColumnNames="";
    		String strColumnValues="";
    		
    		// Get the current record
    		Record record=records.get(j);
    		if (debug)
    			Log.d("<PhanDB-Record>",record.toString());
    		
    		for (int k=0;k<record.columns.size();k++){
    			Column col=record.columns.get(k);
    			
    			if (debug){
        			Log.d("<PhanDB-Column NAME>"	,	col.name);
        			Log.d("<PhanDB-Column VALUE>" ,	col.value);    				
    			}
    			
    			// Build column name list
    			strColumnNames	=	strColumnNames	+	col.name;
    			    			
    			// Build column value list
    			strColumnValues	=	strColumnValues	+ "\"" + col.value + "\"";
    			
    			// if not the last column
    			if (k<(record.columns.size()-1)){
    				strColumnNames	=	strColumnNames	+	",";
    				strColumnValues	=	strColumnValues	+	",";
    			}    			
    		}
    		    		
    		strSQL=strSQL + "(" + strColumnNames + ")";
    		strSQL=strSQL + " values(" + strColumnValues + ");";
    		
    		if (debug){
    			Log.d("<PhanDB-ColumnNames>",strColumnNames);
    			Log.d("<PhanDB-ColumnValues>",strColumnValues);
    			Log.d("<PhanDB-SQL>",strSQL);
    		}
    		database.execSQL("delete from "+name+";");
    		database.execSQL(strSQL);
    	}        	
    	
    }
    /*********************************************************************/                
}

