package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    // declare query variables.
    
    private static final String REGISTER = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
    
    private static final String DROP = "DELETE FROM registration WHERE (studentid = ?) AND (termid = ?) AND (crn = ?)";
    
    private static final String WITHDRAW = "DELETE FROM registration WHERE (studentid = ?) AND (termid = ?)";
    
    private static final String LIST = "SELECT * FROM registration WHERE (studentid = ?) AND (termid = ?)";
    
    ///////////////////////////////////////////
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(REGISTER);
                //set integers
                //set studentid
                ps.setInt(1, studentid);
                //set termid
                ps.setInt(2, termid);
                //set crn
                ps.setInt(3, crn);
                result = ps.executeUpdate() > 0;
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {

                ps = conn.prepareStatement(DROP);
                
                // Set the integers
                //set studentid
                ps.setInt(1, studentid);
                //set termid
                ps.setInt(2, termid);
                //set crn
                ps.setInt(3, crn);
                
                result = ps.executeUpdate() > 0;
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(WITHDRAW);
                //set the integers
                //set studentid
                ps.setInt(1, studentid);
                //set termid
                ps.setInt(2, termid);
                //set result
                result = ps.executeUpdate() > 0;
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        //ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(LIST);
                //set integers
                //set studentid
                ps.setInt(1, studentid);
                //set termid
                ps.setInt(2, termid);
                //set results
                rs = ps.executeQuery();
                // Set json to result
                result = DAOUtility.getResultSetAsJson(rs);
                
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