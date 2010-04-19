package com.phanovatives.phanDB;
import java.util.ArrayList;

import android.database.Cursor;
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
    private ArrayList<Column> copy_columns(ArrayList<Column> cols){
    	ArrayList<Column> new_cols  = new ArrayList<Column>();
    	for(int i=0;i<cols.size();i++){
    		Column new_col=new Column(cols.get(i).name,cols.get(i).type);
    		new_col.value=cols.get(i).value;    		
    		new_cols.add(new_col);
    	}
    	return new_cols;
    }
    /*********************************************************************/    
	public Record create_record(boolean save){
    	ArrayList<Column> cols  = new ArrayList<Column>();
    	
    	for(int i=0;i<columns.size();i++){
    		Column col=new Column(columns.get(i).name,columns.get(i).type);
    		col.value=columns.get(i).value;    		
    		cols.add(col);
    	}
    	    	
        Record record = new Record(cols);
        records.add(record);
        if(save)
        	save();
        return record;
    }
    /*********************************************************************/
    public Column create_column(String strName,String strType){
    	Column column= new Column(strName,strType);
    	columns.add(column);
    	return column;
    }
    /*********************************************************************/
    public Column get_column(String strName){
    	for (Column col:columns){
    		if (col.name.equals(strName)){
    			return col;
    		}
    	}
		return null;
    }
    /*********************************************************************/
    public void set_column(String strName,String strValue){
    	Column col=get_column(strName);
    	if (col!=null)
    		col.value=strValue;
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
    		
    		if (debug){
    			for (Column col: record.columns){
    				Log.d("<phanDB-Columns>",col.name);
    			}
    		}
    		
    		// Sync only records that were not saved: doesn't have id yet
    		if (record.columns.get(0).value==null){
        		if (debug)
        			Log.d("<PhanDB-Record>",record.toString());
        		
        		for (int k=0;k<record.columns.size();k++){
        			Column col=record.columns.get(k);
        			
        			if (col.name.equals("id")){
        				// Do nothing
        			}else{
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
        		}
        		    		
        		strSQL=strSQL + "(" + strColumnNames + ")";
        		strSQL=strSQL + " values(" + strColumnValues + ");";
        		
        		if (debug){
        			Log.d("<PhanDB-ColumnNames>",strColumnNames);
        			Log.d("<PhanDB-ColumnValues>",strColumnValues);
        			Log.d("<PhanDB-SQL>",strSQL);
        		}
        		database.execSQL(strSQL);
        		record.columns.get(0).value="saved";
    		}
    	}        	    	
    }
    /*********************************************************************/
    public void find_all( 
    		String condition,
    		String[] conditions, 
    		String groupBy, 
    		String having, 
    		String orderBy    		
    		){
    	
    	// Populate all columns		
		String[] strCols = new String[columns.size()];
		for(int i=0;i<strCols.length;i++){
			strCols[i]=columns.get(i).name;
		}
		
		// Remove all current data 
		records.clear();
		
		// Query data & add to array list
		Cursor aCur = database.query(name, strCols, condition, conditions, groupBy, having, orderBy);
		if (aCur.getCount()!=0){
			aCur.moveToFirst();						
			do{
				ArrayList<Column> cols=copy_columns(columns);
				aCur.getColumnCount();				
				for (int i=0;i<cols.size();i++){
					cols.get(i).value	=	aCur.getString(i);	
				}
				records.add(new Record(cols));
			}
			while(aCur.moveToNext());
		}		
		aCur.close();
    }
    /*********************************************************************/    
}

