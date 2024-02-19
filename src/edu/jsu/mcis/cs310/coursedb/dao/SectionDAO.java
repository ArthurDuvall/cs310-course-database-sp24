package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class SectionDAO {
    
    // Set quarry string for FIND method
    private static final String FIND = "SELECT * FROM section WHERE termid = ? AND subjectid = ? AND num = ? ORDER BY crn";
    /////////////////////////////
    private final DAOFactory daoFactory;
    
    SectionDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public String find(int termid, String subjectid, String num) {
        
        String result = "[]";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(FIND);
                
                //set integers and strings
                //set termid
                ps.setInt(1, termid);
                //set subjectid
                ps.setString(2, subjectid);
                //set num
                ps.setString(3, num);
                
                rs = ps.executeQuery();     // executes query and gets result set
                result = DAOUtility.getResultSetAsJson(rs);     // converts result to json
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}