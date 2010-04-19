/**
 * See the file "LICENSE" for the full license governing this code.
 */
package com.phanovatives.phanDB;

public class Column {
    /*********************************************************************/    
    public 	String 	name;
    public	String	value;
    public  String  type;
    public  String  default_value;
    /*********************************************************************/    
    public Column(String strName,String strType){
        name	=strName;
        type	=strType;
    }
    /*********************************************************************/                

}
