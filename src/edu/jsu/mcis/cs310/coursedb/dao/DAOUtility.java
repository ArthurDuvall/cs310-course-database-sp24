package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {
                ResultSetMetaData rsmd = rs.getMetaData(); // create variable for metadata

                // iterate 
                while (rs.next()) {
                    JsonObject jsonObject = new JsonObject(); // Create the jsonobject

                    
                    
                    // iterate through each field
                    
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        // get variables
                        //create string for namevalue
                        String columnName = rsmd.getColumnName(i);
                        //create string for value.
                        String columnValue = rs.getObject(i).toString();
                        // put variables in jsonobject
                        jsonObject.put(columnName, columnValue);
                        
                    }
                    // add json object value to records
                    records.add(jsonObject);
                }
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}