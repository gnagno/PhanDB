package com.phanovatives.phanDB;

import java.util.ArrayList;

public class Record {
    /*********************************************************************/    
	public ArrayList<Column>     columns      = new ArrayList<Column>();
    /*********************************************************************/    
    public Record(ArrayList<Column> arrayCols){
    	columns=arrayCols;
    }
    /*********************************************************************/
    public String get_column_value(String strFileName){
    	for (Column col:columns){
    		if (col.name.equals(strFileName))
    			return col.value;
    	}
    	return null;
    }
    /*********************************************************************/                

}
