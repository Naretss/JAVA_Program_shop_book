package test;

import java.sql.*;
public class DBTest {

    public static void main(String[] args) {

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conn = DriverManager.getConnection("jdbc:odbc:Databasebook");
            Statement st = conn.createStatement();
            String sql = "Select * from UserID";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                System.out.println("\n"+rs.getString(1));
            
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

}