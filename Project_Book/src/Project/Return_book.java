/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class Return_book extends javax.swing.JFrame {
    DefaultTableModel model_tbBorrow=new DefaultTableModel(); //การจำลองตาราง
     DefaultTableModel model_tbReturn=new DefaultTableModel(); //การจำลองตาราง
    Object[] data=new Object[0];
    int rount=0;
    int check_item=0;
    long SubDay=0;
    int sumRent=0;
    int sumRentAll=0;
    int sumDefective=0;
    int sumDefectiveAll=0;
    int sumAll=0;
    String Borrow_on;
        Calendar c_on = Calendar.getInstance();
        SimpleDateFormat dc_on  = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate_on  = dc_on.format(c_on.getTime());
    public Return_book() {
        initComponents();
        model_tbBorrow=(DefaultTableModel)tbBorrow.getModel();
        model_tbReturn=(DefaultTableModel)jReturn.getModel();
        setSize(1100, 800);
        setLocationRelativeTo(null);
        Commbo();
        showDataRecord_return();
        setExtendedState(MAXIMIZED_BOTH);
        
        JTableHeader theader = tbBorrow.getTableHeader();
        theader.setFont(new Font("Tahome",Font.PLAIN,20));
        tbBorrow.setFont(new Font("Tahome",Font.PLAIN,20));
        
        JTableHeader theader1 = jReturn.getTableHeader();
        theader1.setFont(new Font("Tahome",Font.PLAIN,20));
        jReturn.setFont(new Font("Tahome",Font.PLAIN,20));
    }

    
 public void showDataRecord(){
      clearRow();
      String ID=jComboBox.getSelectedItem().toString();
      String sql="SELECT * FROM Borrow_Book where Mem_id LIKE '%"+ID+"%' and Status='not return'";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          int row=0;
          while(rs.next()){
              model_tbBorrow.insertRow(model_tbBorrow.getRowCount(),new Object[]{rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(1)});
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
  }
  public void showDataRecord_return(){
      clearRow1();
      int select=tbBorrow.getSelectedRow();

      String sql="SELECT * FROM Return_book";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          while(rs.next()){
              model_tbReturn.insertRow(model_tbReturn.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("No show return");
          System.out.println("show "+e.getMessage());
      }
  }
 
 /*****ล้างตารางที่โชว์เฉยๆ**************/
 public void clearRow(){
     int row=model_tbBorrow.getRowCount()-1;
     while(row>-1){
         model_tbBorrow.removeRow(row);
         row--;
    }
 } 
 public void clearRow1(){
     int row=model_tbReturn.getRowCount()-1;
     while(row>-1){
         model_tbReturn.removeRow(row);
         row--;
    }
 } 
  /********************************/
 public void Commbo(){
    // String ID=scantext_borrow.getText();
      String sql="SELECT * FROM User_book ";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);

          while(rs.next()){
             jComboBox.addItem(rs.getString(1));
             check_item++;
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
  }
 
 public void SelectIT(){
          
      String sql="SELECT * FROM User_book ";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);

          while(rs.next()){
             if(jComboBox.getSelectedItem().equals(rs.getString(1))){
                 String name=rs.getString(3);
                 Mem_nam.setText(name);
                 break;
             }
             rount++;
          }
        c.close();
        rs.close();
          
          
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
    //    Mem_nam.setText(jComboBox.getSelectedItem().toString());
 }
 
 public void DateCal(){
        int select=tbBorrow.getSelectedRow();
	String sDate = tbBorrow.getValueAt(select,3).toString();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	
        int select1=tbBorrow.getSelectedRow();
	String sDate1 = tbBorrow.getValueAt(select1,4).toString();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dc = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dc.format(c.getTime());
        
	try {
            Date date = df.parse(currentDate);  
            Date date1 = df.parse(sDate1); 
            
            //System.out.println(date);
           // System.out.println(date1);
            SubDay = date.getTime() - date1.getTime()  ;  //แตกต่างของวันเวลา
            SubDay=SubDay / (1000*60*60*24);
            SubDay=SubDay-198318;
            if(SubDay>0){
            Day01.setText(Integer.toString((int) SubDay));
            System.out.println(SubDay);
                    
            sumRent=Integer.valueOf(tbBorrow.getValueAt(select,2).toString())*(int) SubDay;     
            sumRentAll=sumRentAll+sumRent;
            }else JOptionPane.showMessageDialog(this,"ไม่มีค่าปรับเกินกำหนด");
            
            sumDefective=Integer.valueOf(Defective.getText());//ค่าปรับชำรุด
            sumDefectiveAll=sumDefectiveAll+sumDefective;  //รวมค่าปรับเกินกำหนดคืน
            
            DefectiveAll.setText(Integer.toString(sumDefectiveAll)); //รวมค่าปรับชำรุด
            Rent_sumAll.setText(Integer.toString(sumRentAll)); //รวมค่าปรับเกินกำหนดคืน
            Rent_sum.setText(Integer.toString(sumRent));  //ค่าปรับเกินกำหนดคืน
            
            sumAll=sumRentAll+sumDefectiveAll;
            sum_All.setText(Integer.toString(sumAll));
            
	}
        catch (ParseException e) {
            e.printStackTrace();
	}
        
 }
 
 public void insertdata(){
     int select=tbBorrow.getSelectedRow();
       String Borrow_ID=tbBorrow.getValueAt(select,6).toString();
       String Mem_id=jComboBox.getSelectedItem().toString();
       String Return_Date=currentDate_on;
       int Return_FineTotal=Integer.valueOf(sum_All.getText());
       String Defective_note=Defective_on.getText();
       String sql="INSERT INTO Return_book (Borrow_ID,Mem_id,Return_Date,Return_FineTotal,Defective) VALUES("
               + "'"+Borrow_ID+"',"
               +"'"+Mem_id+"',"
               + "'"+Return_Date+"',"
               + ""+Return_FineTotal+","
               + "'"+Defective_note+"')";
        try{
            Connection c=Project_Book.connectDB(); //เชื่อมฐานข้อมูล
            Statement stm=c.createStatement();//ทำการสร้างชุดคำสั่งขึ้นขึนมาเพื่อเอามาใช้
            int s = stm.executeUpdate(sql);//นำคำสั้ง sql ไปดำเนินการ
            System.out.println("บันทึกข้อมูล");
            JOptionPane.showMessageDialog(this,"บันทึกข้อมูลเรียบร้อย");
            stm.close();
            c.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"แจ้งข้อผิดผลาด : "+e.getMessage());
            System.out.println(e.getMessage());
        }
  }
 
   public void udpateRecord(){
     int select=tbBorrow.getSelectedRow();
     String Book_id=tbBorrow.getValueAt(select,0).toString();
     String Mem_id=jComboBox.getSelectedItem().toString();
     String sql="UPDATE Borrow_Book SET  Status = 'return' where Book_id='"+Book_id+"' and Mem_id='"+Mem_id+"'";
     try{
          Connection c=Project_Book.connectDB();
          Statement stm=c.createStatement();
          stm.executeUpdate(sql);
              JOptionPane.showMessageDialog(this,"อัพเดทข้อมูลเรียบร้อย");
              showDataRecord();
          c.close();
          stm.close();
          System.out.println("อัพเดท");
          
      }catch(Exception e){
          
          System.out.println("update : ");
          System.out.println(e.getMessage());
      }
  }
   public void udpateRecord_return(){
     int select=jReturn.getSelectedRow();
     String borrow=jReturn.getValueAt(select,1).toString();
     String Mem_id=jReturn.getValueAt(select,2).toString();
     String sql="UPDATE Borrow_Book SET  Status = 'not return' WHERE Borrow_ID LIKE '"+borrow+"' AND Mem_id LIKE '"+Mem_id+"'";
     try{
          Connection c=Project_Book.connectDB();
          Statement stm=c.createStatement();
          stm.executeUpdate(sql);
              JOptionPane.showMessageDialog(this,"อัพเดทข้อมูลเรียบร้อย");
              showDataRecord();
          c.close();
          stm.close();
          System.out.println("อัพเดท");
          
      }catch(Exception e){
          
          System.out.println("update : ");
          System.out.println(e.getMessage());
      }
  }
   
  public void deleteRecord(){
      int select=jReturn.getSelectedRow();
      String ID=jReturn.getValueAt(select,0).toString();
      String sql="DELETE FROM Return_book where Return_ID like '"+ID+"'";
      try{
          Connection c=Project_Book.connectDB();
          Statement stm=c.createStatement();
          stm.executeUpdate(sql);
              JOptionPane.showMessageDialog(this,"ลบข้อมูลเรียบร้อย");
              showDataRecord();
          c.close();
          stm.close();
      }catch(Exception e){
          
          System.out.println(e.getMessage());
      }
  }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jReturn = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Rent_sum = new javax.swing.JLabel();
        DefectiveAll = new javax.swing.JLabel();
        Rent_sumAll = new javax.swing.JLabel();
        Day01 = new javax.swing.JLabel();
        Defective = new javax.swing.JTextField();
        Defective_on = new javax.swing.JTextField();
        sum_All = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Databorrow = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBorrow = new javax.swing.JTable();
        jComboBox = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        Mem_nam = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 400));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ข้อมูลการคืน", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 36), new java.awt.Color(255, 255, 255))); // NOI18N

        jReturn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสการคืน", "รหัสการยืม", "รหัสสมาชิก", "วันที่คืน"
            }
        ));
        jReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jReturnMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jReturn);
        if (jReturn.getColumnModel().getColumnCount() > 0) {
            jReturn.getColumnModel().getColumn(0).setHeaderValue("รหัสการคืน");
            jReturn.getColumnModel().getColumn(1).setHeaderValue("รหัสการยืม");
            jReturn.getColumnModel().getColumn(2).setHeaderValue("รหัสสมาชิก");
            jReturn.getColumnModel().getColumn(3).setHeaderValue("วันที่คืน");
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setText("ลบการคืน");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ข้อมูลค่าปรับ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 36))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel2.setText("ค่าปรับเกินกำหนดคืน");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel3.setText("ค่าปรับชำรุด");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel4.setText("บาท");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel5.setText("บาท");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel6.setText("วัน");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel7.setText("จำนวนวันที่เกิน");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel8.setText("รวมค่าปรับเกินกำหนดคืน");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel9.setText("บาท");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel10.setText("รายละเอียดที่ชำรุด");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel11.setText("รวมสุทธิ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel12.setText("บาท");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel13.setText("รวมค่าปรับชำรุด");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel14.setText("บาท");

        Rent_sum.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        Rent_sum.setForeground(new java.awt.Color(255, 51, 0));
        Rent_sum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Rent_sum.setText("0");

        DefectiveAll.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        DefectiveAll.setForeground(new java.awt.Color(255, 51, 0));
        DefectiveAll.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DefectiveAll.setText("0");

        Rent_sumAll.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        Rent_sumAll.setForeground(new java.awt.Color(255, 51, 0));
        Rent_sumAll.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Rent_sumAll.setText("0");

        Day01.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        Day01.setForeground(new java.awt.Color(255, 51, 0));
        Day01.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Day01.setText("0");

        Defective.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        Defective.setForeground(new java.awt.Color(255, 0, 51));
        Defective.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Defective.setText("0");

        Defective_on.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        Defective_on.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        sum_All.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        sum_All.setForeground(new java.awt.Color(255, 51, 0));
        sum_All.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sum_All.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Defective, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Rent_sum, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(4, 4, 4)
                        .addComponent(Rent_sumAll, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DefectiveAll, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Day01, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(sum_All, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Defective_on, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(Day01))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Defective_on))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(sum_All))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(Rent_sum))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(Defective))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(Rent_sumAll))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(DefectiveAll)))))
                .addGap(37, 37, 37))
        );

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("บันทึกคืนหนังสือ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Databorrow.setBackground(new java.awt.Color(0, 153, 153));
        Databorrow.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ข้อมูลการยืม", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        Databorrow.setForeground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("รหัสสมาชิก");

        tbBorrow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัหนังสือ", "ชื่อหนังสือที่ยืม", "ค่าปรับ/วัน", "วันที่ยืม", "กำหนดคืน", "สถานะหนังสือ", "รหัสการยืม"
            }
        ));
        tbBorrow.getTableHeader().setReorderingAllowed(false);
        tbBorrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBorrowMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBorrow);

        jComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ชื่อสมาชิก");

        Mem_nam.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Mem_nam.setForeground(new java.awt.Color(255, 255, 255));
        Mem_nam.setText("ชื่อ");

        javax.swing.GroupLayout DataborrowLayout = new javax.swing.GroupLayout(Databorrow);
        Databorrow.setLayout(DataborrowLayout);
        DataborrowLayout.setHorizontalGroup(
            DataborrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataborrowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(DataborrowLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(DataborrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataborrowLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DataborrowLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Mem_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DataborrowLayout.setVerticalGroup(
            DataborrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataborrowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DataborrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DataborrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(Mem_nam))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
        );

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton3.setText("กลับสู่หน้าหลัก");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(429, 429, 429)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(421, 421, 421)
                                .addComponent(Databorrow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(402, 402, 402)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(693, 693, 693)
                        .addComponent(jButton1)
                        .addGap(28, 28, 28)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton3)))
                .addContainerGap(642, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Databorrow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed
       SelectIT();
       showDataRecord();
    }//GEN-LAST:event_jComboBoxActionPerformed

    private void tbBorrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBorrowMouseClicked


    }//GEN-LAST:event_tbBorrowMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       DateCal();
       insertdata();
       udpateRecord();
       showDataRecord();
       showDataRecord_return();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       deleteRecord();
       udpateRecord_return();
       showDataRecord_return();
       showDataRecord();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jReturnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jReturnMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                             Home form1 = new Home();
                             form1.setVisible(true);
                             setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Return_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Return_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Return_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Return_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Return_book().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Databorrow;
    private javax.swing.JLabel Day01;
    private javax.swing.JTextField Defective;
    private javax.swing.JLabel DefectiveAll;
    private javax.swing.JTextField Defective_on;
    private javax.swing.JLabel Mem_nam;
    private javax.swing.JLabel Rent_sum;
    private javax.swing.JLabel Rent_sumAll;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTable jReturn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel sum_All;
    private javax.swing.JTable tbBorrow;
    // End of variables declaration//GEN-END:variables
}
