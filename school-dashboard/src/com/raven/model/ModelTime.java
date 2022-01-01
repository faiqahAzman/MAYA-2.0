/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.model;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import loginpage.ConnectDatabase;

/**
 *
 * @author user
 */
public class ModelTime {
    
    Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int time;
    public ModelTime() {
    }
    
    public int convertData() {
        
        con = ConnectDatabase.connectdb();
    
        
        String q1 = "SELECT TIMESTART FROM TIMETABLE_MODULES";
        try {
            ps = con.prepareStatement(q1);
            rs = ps.executeQuery();
            rs.next();
            
       
            String TS= rs.getString("TIMESTART");
                switch(TS) {
                    
                    case "09:00:00":
                        time =9;
                        break;
                    case "15:00:00":
                        time =15;
                        break;
                    case "16:00:00":
                        time =16;
                        break;
                    /*case "09:00:00":
                        time =9;
                        break;
                    case "09:00:00":
                        time =9;
                        break;
                    case "09:00:00":
                        time =9;
                        break;*/
                        
                }
                
          
                
                
            
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return time;
       
        
        } 
        
    }
    

    



