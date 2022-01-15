/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tba.model;

import java.sql.*;
import javax.swing.JOptionPane;
import loginpage.ConnectDatabase;
import loginpage.LoginForm1;

/**
 *
 * @author user
 */
public class ModelStudentType {
    
    Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
    LoginForm1 lf = new LoginForm1();
    int stypenum;

    public ModelStudentType() {
        
    }
   
    public int checkType() {
        
        con = ConnectDatabase.connectdb();
        
        String query = "SELECT stdnt_type FROM logintable WHERE matrix_number = '" + lf.getMatrixNo() + "'";
        
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            rs.next();
            
            int STDNT_TYPE = rs.getInt("STDNT_TYPE");
            //String STDNT_TYPE = rs.getString("stdnt_type");
            
            if(STDNT_TYPE==-1){
                stypenum = -1;
            }else if(STDNT_TYPE==0){
                stypenum =0;
            }else
                stypenum = 1;
   
     

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return stypenum;
    }
    
  
    
    
}
