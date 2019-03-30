
package test;
import Project.Project_Book;
import java.sql.*;

public class Guicon {
     String db_name="book_database";
     String user="admin";
     String pass="admin";
     String hostName="localhost";
     String db_driver="com.mysql.jdbc.Driver";//ไดเวอใช้ในการเชื่อมต่อ database
    public static void main(String[] args) {
        //connectDB();
        new Project_Book().connectDB();
        //showData();
        //insertDb();
        //updateDb();
        //deleteDb();
    }
 //*****************เชื่อมต่อข้อมูล****************//

    /**
     *
     * @return
     */
    public Connection connectDB(){
        try{
            Class.forName(db_driver);//ระบุ driver
            String url="jdbc:mysql://"+hostName+"/"+db_name;
            Connection connect=DriverManager.getConnection(url,user,pass);
            System.out.println("เชื่อมต่อ  "+url);
           return connect; 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
 //*****************ปริ้นข้อมูล****************//
    public  void showData(){ 
        String sql="select * from tb_user"; //เลือกข้อมูลทุกอย่าง
        try {
            Connection c=connectDB(); //เชื่อมฐานข้อมูล ถ้าคอน จะทำการ
            ResultSet rs=c.createStatement().executeQuery(sql); //นำผลนับมาเก็บไว้ในrs
            while(rs.next()){
                System.out.println("รหัส:"+rs.getString(1)+" ชื่อ:"+rs.getString(2)
                        +" นามสถุล:"+rs.getString(3)+" อายุ:"+rs.getString(4));
            }
            rs.close();
            c.close(); //ปิดการเชื่อมต่อ
        }catch (Exception e){
            e.printStackTrace();
        }
    }
 
//*****************เพิ่มข้อมูล****************//  
    public  void insertDb(){
        String sql ="insert into tb_user value(3,'janny','yed',30)";
        try{
            Connection c=connectDB(); //เชื่อมฐานข้อมูล
            Statement stm=c.createStatement();//ทำการสร้างชุดคำสั่งขึ้นขึนมาเพื่อเอามาใช้
            stm.executeUpdate(sql);//นำคำสั้ง sql ไปดำเนินการ
            System.out.println("บันทึกข้อมูล");
            stm.close();
            c.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
 
 //*****************แก้ไขข้อมูล****************// 
    public  void updateDb(){
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

    public  void deleteDb(){
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

