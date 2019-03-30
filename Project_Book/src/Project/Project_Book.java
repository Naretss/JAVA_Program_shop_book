
package Project;
import java.sql.*;

public class Project_Book {

    public static void main(String[] args) {
        //connectDB();
        showData();
        //insertDb();
        //updateDb();
        //deleteDb();
    }
 //*****************เชื่อมต่อข้อมูล****************//
    //public void connectDB(){ แล้วต้องเอา
    public static Connection connectDB(){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection connect = DriverManager.getConnection("jdbc:odbc:Databasebook");
            System.out.println("เชื่อมต่อ  ");
            return connect; 
        }catch(Exception e){
            System.out.println("error");
            System.out.println(e.getMessage());
        }
        return null;
    }
    
 //*****************ปริ้นข้อมูล****************//
    public static void showData(){ 
        String sql="select * from User_book"; //เลือกข้อมูลทุกอย่าง
        try {
            Connection c=Project_Book.connectDB(); //เชื่อมฐานข้อมูล ถ้าคอน จะทำการ
            ResultSet rs=c.createStatement().executeQuery(sql); //นำผลนับมาเก็บไว้ในrs
            while(rs.next()){
                System.out.println("รหัส:"+rs.getString(1)+" ชื่อ:"+rs.getString(2));
            }
            rs.close();
            c.close(); //ปิดการเชื่อมต่อ
        }catch (Exception e){
            e.printStackTrace();
        }
    }
 
//*****************เพิ่มข้อมูล****************//  
    public static void insertDb(){
        
        String sql ="INSERT INTO User_book VALUES('gsdgds','gdgs','gdsgsd','gsdgdsg','gdsgdsg','gdsgds','gdsg')";
        try{
            Connection c=connectDB(); //เชื่อมฐานข้อมูล
            Statement stm=c.createStatement();//ทำการสร้างชุดคำสั่งขึ้นขึนมาเพื่อเอามาใช้
            int s = stm.executeUpdate(sql);//นำคำสั้ง sql ไปดำเนินการ
            System.out.println("บันทึกข้อมูล");
            stm.close();
            c.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
 
 //*****************แก้ไขข้อมูล****************// 
    public static void updateDb(){
        String sql="update tb_user set id=150,"
                + "fName='gg',"
                + "lName='gdg',"
                + "age=10 where id=2";
        try{
          Connection c=connectDB();
          Statement stm=c.createStatement();
          stm.executeUpdate(sql);
          stm.close();
          c.close();
          System.out.println("แก้ไขข้อมูลแล้ว");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteDb(){
        String sql="delete from tb_user where id=150";
        
        try{
          Connection c=connectDB();
          Statement stm=c.createStatement();
          stm.executeUpdate(sql);
          System.out.println("ลบข้อมูลแล้ว");
          stm.close();
          c.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}

