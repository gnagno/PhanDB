package com.phanovatives.phanDB;

import android.util.Log;

public class Settings {
    /**
     * Global phanDB setting
     */
    private static boolean debug = false;
    
    /**
     * Sets the global debug mode
     * @param debug
     */
    public static void setDebug(boolean debug) {
        Settings.debug = debug;
    }

    /**
     * Gets the global debug mode
     */
    public static boolean getDebug() {
        return debug;
    }
    
    /**
     * The same as Log.d but accept null values
     */
    public static void Log(String tag, String msg){
    	if ((tag!=null)&&(msg!=null)){
    		Log.d(tag,msg);
    	}    	
    }
}
