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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class Borrow extends javax.swing.JFrame {

    DefaultTableModel model_book=new DefaultTableModel(); //การจำลองตาราง
    Object[] data=new Object[0];
    DefaultTableModel model_user=new DefaultTableModel(); 
    DefaultTableModel model_borrow=new DefaultTableModel(); 
    int sum=0;
    int sumAll=0;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");  //ช่วงของเวลาปัจจุบัน
    String currentDate = df.format(c.getTime());
        
    GregorianCalendar gc = new GregorianCalendar();
    int d=Integer.valueOf(gc.get(Calendar.DATE));
    int d_temp=d;
    int m=Integer.valueOf(gc.get(Calendar.MONTH))+1;
    int y_on=Integer.valueOf(gc.get(Calendar.YEAR))+543; 
    
    public Borrow() {
        initComponents();
        model_book=(DefaultTableModel)tbbook.getModel();
        model_user=(DefaultTableModel)tbMem2.getModel();
        model_borrow=(DefaultTableModel)jBorrow.getModel();
        showDataAll_book();
        showDataAll_user();
        showDataRecord_borrow();
        
        setExtendedState(MAXIMIZED_BOTH);
        JTableHeader theader = tbMem2.getTableHeader();
        theader.setFont(new Font("Tahome",Font.PLAIN,20));
        //tbMem2.setFont(new Font("Tahome",Font.PLAIN,20));
        
        JTableHeader theader1 = tbbook.getTableHeader();
        theader1.setFont(new Font("Tahome",Font.PLAIN,20));
        //tbbook.setFont(new Font("Tahome",Font.PLAIN,20));

        JTableHeader theader2 = jBorrow.getTableHeader();
        theader2.setFont(new Font("Tahome",Font.PLAIN,20));
        jBorrow.setFont(new Font("Tahome",Font.PLAIN,20));
        
    }
    
  public void insertdataChange(){
       String ID=Mem_id.getText();
       String NAME=Mem_name.getText();
       int SumMoney=Integer.valueOf(MoneyRent.getText());
       int Get=Integer.valueOf(MoneyGet.getText());
       int Change=Integer.valueOf(change.getText());
       String sql="INSERT INTO Change_User (Mem_id,Mem_name,RentDate_book,SumMoney,GetMoney,Change) VALUES("
               + "'"+ID+"',"
               +"'"+NAME+"',"
               + "'"+currentDate+"',"
               + ""+SumMoney+","
               + ""+Get+","
               + ""+Change+")";
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
  public void calculationChange(){
        sumAll=Integer.valueOf(MoneyGet.getText())-sum;
        change.setText(Integer.toString(sumAll));
  }
 
  public void calculation(){
        int select=tbbook.getSelectedRow();  
        sum=sum+Integer.valueOf(tbbook.getValueAt(select,4).toString());
        System.out.println(sum);
        MoneyRent.setText(Integer.toString(sum));

  }

  public void calculationSUB(){
        int select=jBorrow.getSelectedRow();  
        sum=sum-Integer.valueOf(jBorrow.getValueAt(select,3).toString());
        if(sum==-5) sum=0;
        System.out.println(sum);
        MoneyRent.setText(Integer.toString(sum));
  }
 /*******โชว์ข้อมูล********************/
  public void showDataRecord_book(){
      clearRow();
      //showDataRecord_user();
      String id=scantext_book.getText();
      String sql="SELECT * FROM TB_book where Book_id LIKE '%"+id+"%'";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          int row=0;
          while(rs.next()){
              model_book.insertRow(model_book.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)});
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
      
  }
  
  public void showDataRecord_user(){
      clearRow1();
      String id=scantext_User.getText();
      String sql="SELECT * FROM User_book where Mem_id LIKE '%"+id+"%'";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          int row=0;
          while(rs.next()){
              model_user.insertRow(model_user.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
      
  }
  
  public void showDataRecord_borrow(){
    clearRow2();
    String sql="SELECT * FROM Borrow_Book";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          while(rs.next()){
              model_borrow.insertRow(model_borrow.getRowCount(),new Object[]{rs.getString(3),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(7)});
 
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
  }
 /*****ล้างตารางที่โชว์เฉยๆ**************/

  public void clearRow(){
     int row=model_book.getRowCount()-1;
     while(row>-1){
         model_book.removeRow(row);
         row--;
    }
 } 
  
  public void clearRow1(){
     int row=model_user.getRowCount()-1;
     while(row>-1){
         model_user.removeRow(row);
         row--;
    }
 } 
  
  public void clearRow2(){
     int row=model_borrow.getRowCount()-1;
     while(row>-1){
         model_borrow.removeRow(row);
         row--;
    }
 } 
  
 public void insertdata(){
       d=d_temp;
       int select_book=tbbook.getSelectedRow();
       String ID_book=tbbook.getValueAt(select_book,0).toString();
       int select_user=tbMem2.getSelectedRow();
       String ID_user=tbMem2.getValueAt(select_user,0).toString();
       d=d+Integer.valueOf(tbbook.getValueAt(select_book,3).toString());
       
//       String ID_book=Book_id1.getText();
//       String ID_user=Mem_id.getText();
//       d=d+Integer.valueOf(RentDate_book1.getText());
//       String time_book = d+"-"+gc.get(Calendar.MONTH)+"-"+y_on;
         String time_book = d+"-"+m+"-"+y_on;   
       String name = tbbook.getValueAt(select_book,1).toString();
       int RentPrice = Integer.valueOf(tbbook.getValueAt(select_book,4).toString());
       
       //int RentPrice=Integer.valueOf(RentPrice_book.getText());
       String sql="INSERT INTO Borrow_Book (Book_id,Mem_id,Name_book,RentPrice_book,Borrow_Date,Borrow_Now,Status) VALUES("
               + "'"+ID_book+"',"
               + "'"+ID_user+"',"
               + "'"+name+"',"
               + ""+RentPrice+","
               + "'"+currentDate+"',"
               + "'"+time_book+"',"
               + "'not return')";
       
    String scan="SELECT * FROM Borrow_Book where Book_id like '"+Book_id1.getText().toString()+"'"; 
    try{
        Connection c=Project_Book.connectDB();
        ResultSet rs=c.createStatement().executeQuery(scan);
      if(rs.next()){
          if(rs.getString(8).equals("return")){
                try{
      //            Connection c=Project_Book.connectDB(); 
                  Statement stm=c.createStatement();
                  int s = stm.executeUpdate(sql);
                  System.out.println("ทำการยืม");
                  JOptionPane.showMessageDialog(this,"บันทึกข้อมูลเรียบร้อย");
                  calculation();
                  stm.close();
                  c.close();
                  rs.close();
              }catch(Exception e){
                  System.out.println("insert error : ");
                  System.out.println(e.getMessage());
              }
            
                  c.close();
                  rs.close();
        }else{
              JOptionPane.showMessageDialog(this,"สถานะ : not return");
              c.close();
              rs.close();
          }
      }
      else{ //กรณีไม่มีข้อมูล
                 try{
      //            Connection c=Project_Book.connectDB(); 
                  Statement stm=c.createStatement();
                  int s = stm.executeUpdate(sql);
                  System.out.println("ทำการยืม");
                  JOptionPane.showMessageDialog(this,"บันทึกข้อมูลเรียบร้อย");
                  calculation();
                  stm.close();
                  c.close();
                  rs.close();
                  
              }catch(Exception e){
                  System.out.println("insert error : ");
                  System.out.println(e.getMessage());
         }
      }
    }catch(Exception e){
            System.out.println("scan error : ");
            System.out.println(e.getMessage());
        }
    
  }
  
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        ข้อมูลหนังสือ = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scan_book = new javax.swing.JButton();
        scantext_book = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbbook = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        Book_id1 = new javax.swing.JTextField();
        Name_book1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        RentDate_book1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        Price_book1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        Fine_book1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        RentPrice_book1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ข้อมูลสมาชิก = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        scan_user = new javax.swing.JButton();
        scantext_User = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbMem2 = new javax.swing.JTable();
        Mem_id = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Mem_name = new javax.swing.JTextField();
        Mem_code = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Mem_address = new javax.swing.JTextField();
        Mem_phone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Mem_MFG = new javax.swing.JTextField();
        Mem_EXP = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ข้อมูลการยืม = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jBorrow = new javax.swing.JTable();
        jฺBorrow = new javax.swing.JButton();
        jDelete = new javax.swing.JButton();
        รวมสุทธิ = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ยืมหนังสือ = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        MoneyRent = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        รับเงิน = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        ทอนเงิน = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        change = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        MoneyGet = new javax.swing.JTextField();
        jChang = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jPanel6.setBackground(new java.awt.Color(51, 153, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel14.setText("ยืมหนังสือ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(68, 68, 68))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel14)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(1993, 993));

        ข้อมูลหนังสือ.setBackground(new java.awt.Color(0, 153, 102));
        ข้อมูลหนังสือ.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ข้อมูลหนังสือ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 36), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("รหัสหนังสือ");

        scan_book.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        scan_book.setText("ค้นหา");
        scan_book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scan_bookActionPerformed(evt);
            }
        });

        scantext_book.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N

        tbbook.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbbook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสหนังสือ", "ชื่อหนังสือ", "ราคาหนังสือ", "จำนวนวันที่เช่าได้", "ค่าเช่า", "ค่าปรับ"
            }
        ));
        tbbook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbookMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbbook);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("รหัสหนังสือ");

        Book_id1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        Name_book1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("ชื่อหนังสือ");

        RentDate_book1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        RentDate_book1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("วัน");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("จำนวนวันที่เช่าได้");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("บาท");

        Price_book1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Price_book1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Price_book1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Price_book1ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ราคาหนังสือ");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("ค่าปรับ");

        Fine_book1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Fine_book1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("บาท");

        RentPrice_book1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        RentPrice_book1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ค่าเช่า");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("บาท");

        javax.swing.GroupLayout ข้อมูลหนังสือLayout = new javax.swing.GroupLayout(ข้อมูลหนังสือ);
        ข้อมูลหนังสือ.setLayout(ข้อมูลหนังสือLayout);
        ข้อมูลหนังสือLayout.setHorizontalGroup(
            ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ข้อมูลหนังสือLayout.createSequentialGroup()
                .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ข้อมูลหนังสือLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(scantext_book, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scan_book))
                    .addGroup(ข้อมูลหนังสือLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ข้อมูลหนังสือLayout.createSequentialGroup()
                                .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ข้อมูลหนังสือLayout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Book_id1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ข้อมูลหนังสือLayout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Price_book1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel23)))
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ข้อมูลหนังสือLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(RentPrice_book1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addGap(31, 31, 31)))
                        .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ข้อมูลหนังสือLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(Name_book1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ข้อมูลหนังสือLayout.createSequentialGroup()
                                .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Fine_book1)
                                    .addComponent(RentDate_book1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel26)))))
                    .addGroup(ข้อมูลหนังสือLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ข้อมูลหนังสือLayout.setVerticalGroup(
            ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ข้อมูลหนังสือLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(scan_book)
                    .addComponent(scantext_book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(Book_id1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Name_book1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ข้อมูลหนังสือLayout.createSequentialGroup()
                        .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(Price_book1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(RentPrice_book1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel25)
                            .addComponent(Fine_book1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ข้อมูลหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel21)
                        .addComponent(RentDate_book1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ข้อมูลสมาชิก.setBackground(new java.awt.Color(0, 153, 102));
        ข้อมูลสมาชิก.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ข้อมูลสมาชิก", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 36), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("รหัสสมาชิก");

        scan_user.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        scan_user.setText("ค้นหา");
        scan_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scan_userActionPerformed(evt);
            }
        });

        scantext_User.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        scantext_User.setText("Mem");
        scantext_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scantext_UserActionPerformed(evt);
            }
        });

        tbMem2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสสมาชิก", "เลขบัตรประชาชน", "ชื่อ นามสกุล", "ที่อยู่", "เบอร์โทร", "วันที่สมัคร", "วันหมดอายุ"
            }
        ));
        tbMem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMem2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbMem2);

        Mem_id.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Mem_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mem_idActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("รหัสสมาชิก");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ชื่อ-นามสกุล");

        Mem_name.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        Mem_code.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("เลขบัตรประชาชน");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ที่อยู่");

        Mem_address.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Mem_address.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Mem_address.setToolTipText("");

        Mem_phone.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("เบอร์โทร");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("วันที่สมัคร");

        Mem_MFG.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Mem_MFG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mem_MFGActionPerformed(evt);
            }
        });

        Mem_EXP.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("วันหมดอายุ");

        javax.swing.GroupLayout ข้อมูลสมาชิกLayout = new javax.swing.GroupLayout(ข้อมูลสมาชิก);
        ข้อมูลสมาชิก.setLayout(ข้อมูลสมาชิกLayout);
        ข้อมูลสมาชิกLayout.setHorizontalGroup(
            ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ข้อมูลสมาชิกLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ข้อมูลสมาชิกLayout.createSequentialGroup()
                        .addGroup(ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ข้อมูลสมาชิกLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(38, 38, 38)
                                .addComponent(Mem_MFG, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(Mem_EXP, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ข้อมูลสมาชิกLayout.createSequentialGroup()
                                .addGroup(ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(ข้อมูลสมาชิกLayout.createSequentialGroup()
                                        .addComponent(Mem_id, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Mem_code, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Mem_name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Mem_address, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(ข้อมูลสมาชิกLayout.createSequentialGroup()
                                        .addComponent(Mem_phone)
                                        .addGap(323, 323, 323))))
                            .addGroup(ข้อมูลสมาชิกLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(95, 95, 95)
                                .addComponent(scantext_User, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scan_user)))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ข้อมูลสมาชิกLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        ข้อมูลสมาชิกLayout.setVerticalGroup(
            ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ข้อมูลสมาชิกLayout.createSequentialGroup()
                .addGroup(ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(scan_user)
                    .addComponent(scantext_User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(Mem_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mem_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Mem_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(Mem_address, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Mem_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ข้อมูลสมาชิกLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Mem_MFG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(Mem_EXP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ข้อมูลการยืม.setBackground(new java.awt.Color(0, 153, 102));
        ข้อมูลการยืม.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ข้อมูลการยืม", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 36), new java.awt.Color(255, 255, 255))); // NOI18N

        jBorrow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสสมาชิก", "รหัสหนังสือ", "ชื่อหนังสือ", "ค่าเช่า", "กำหนดคืน"
            }
        ));
        jBorrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBorrowMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jBorrow);

        jฺBorrow.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jฺBorrow.setText("ยืม/ตกลง");
        jฺBorrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jฺBorrowActionPerformed(evt);
            }
        });

        jDelete.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jDelete.setText("ลบ");
        jDelete.setToolTipText("");
        jDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ข้อมูลการยืมLayout = new javax.swing.GroupLayout(ข้อมูลการยืม);
        ข้อมูลการยืม.setLayout(ข้อมูลการยืมLayout);
        ข้อมูลการยืมLayout.setHorizontalGroup(
            ข้อมูลการยืมLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ข้อมูลการยืมLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ข้อมูลการยืมLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ข้อมูลการยืมLayout.createSequentialGroup()
                        .addComponent(jฺBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(550, 550, 550))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ข้อมูลการยืมLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(226, 226, 226))))
        );
        ข้อมูลการยืมLayout.setVerticalGroup(
            ข้อมูลการยืมLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ข้อมูลการยืมLayout.createSequentialGroup()
                .addGroup(ข้อมูลการยืมLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jฺBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        รวมสุทธิ.setBackground(new java.awt.Color(0, 102, 102));
        รวมสุทธิ.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("รวมสุทธิ...");

        javax.swing.GroupLayout รวมสุทธิLayout = new javax.swing.GroupLayout(รวมสุทธิ);
        รวมสุทธิ.setLayout(รวมสุทธิLayout);
        รวมสุทธิLayout.setHorizontalGroup(
            รวมสุทธิLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, รวมสุทธิLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
        );
        รวมสุทธิLayout.setVerticalGroup(
            รวมสุทธิLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(รวมสุทธิLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ยืมหนังสือ.setBackground(new java.awt.Color(0, 102, 102));

        jLabel13.setBackground(new java.awt.Color(0, 102, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 54)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ยืมหนังสือ");

        javax.swing.GroupLayout ยืมหนังสือLayout = new javax.swing.GroupLayout(ยืมหนังสือ);
        ยืมหนังสือ.setLayout(ยืมหนังสือLayout);
        ยืมหนังสือLayout.setHorizontalGroup(
            ยืมหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ยืมหนังสือLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        ยืมหนังสือLayout.setVerticalGroup(
            ยืมหนังสือLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ยืมหนังสือLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));

        MoneyRent.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        MoneyRent.setForeground(new java.awt.Color(255, 255, 255));
        MoneyRent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        MoneyRent.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MoneyRent, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MoneyRent)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));

        รับเงิน.setBackground(new java.awt.Color(0, 102, 102));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("รับเงิน...");

        javax.swing.GroupLayout รับเงินLayout = new javax.swing.GroupLayout(รับเงิน);
        รับเงิน.setLayout(รับเงินLayout);
        รับเงินLayout.setHorizontalGroup(
            รับเงินLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, รับเงินLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
        );
        รับเงินLayout.setVerticalGroup(
            รับเงินLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(รับเงินLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(รับเงิน, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(รับเงิน, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        ทอนเงิน.setBackground(new java.awt.Color(0, 102, 102));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("ทอนเงิน...");

        javax.swing.GroupLayout ทอนเงินLayout = new javax.swing.GroupLayout(ทอนเงิน);
        ทอนเงิน.setLayout(ทอนเงินLayout);
        ทอนเงินLayout.setHorizontalGroup(
            ทอนเงินLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ทอนเงินLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ทอนเงินLayout.setVerticalGroup(
            ทอนเงินLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ทอนเงินLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel31)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(0, 0, 0));
        jPanel16.setForeground(new java.awt.Color(255, 255, 255));

        change.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        change.setForeground(new java.awt.Color(255, 255, 255));
        change.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        change.setText("0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(change)
                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jButton1.setText("บันทึกข้อมูล");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        MoneyGet.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        MoneyGet.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        MoneyGet.setText("0");

        jChang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jChang.setText("คำนวณ");
        jChang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChangActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jButton3.setText("กลับสู่หน้าหลัก");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ยืมหนังสือ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(รวมสุทธิ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(MoneyGet, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jChang, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ทอนเงิน, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(36, 36, 36)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ข้อมูลสมาชิก, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ข้อมูลหนังสือ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ข้อมูลการยืม, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(720, 720, 720))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ข้อมูลหนังสือ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ข้อมูลสมาชิก, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ข้อมูลการยืม, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(ทอนเงิน, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(MoneyGet, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(jChang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(รวมสุทธิ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addComponent(ยืมหนังสือ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 2543, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1011, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbbookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbookMouseClicked
        int select=tbbook.getSelectedRow();
        Book_id1.setText(tbbook.getValueAt(select,0).toString());
        Name_book1.setText(tbbook.getValueAt(select,1).toString());
        Price_book1.setText(tbbook.getValueAt(select,2).toString());
        RentDate_book1.setText(tbbook.getValueAt(select,3).toString());
        RentPrice_book1.setText(tbbook.getValueAt(select,4).toString());
        Fine_book1.setText(tbbook.getValueAt(select,5).toString());

        Book_id1.setEnabled(false);
        Name_book1.setEnabled(false);
        Price_book1.setEnabled(false);
        RentDate_book1.setEnabled(false);
        RentPrice_book1.setEnabled(false);
        Fine_book1.setEnabled(false);
    }//GEN-LAST:event_tbbookMouseClicked

    private void tbMem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMem2MouseClicked
        int select=tbMem2.getSelectedRow();
        Mem_id.setText(tbMem2.getValueAt(select,0).toString());
        Mem_code.setText(tbMem2.getValueAt(select,1).toString());
        Mem_name.setText(tbMem2.getValueAt(select,2).toString());
        Mem_address.setText(tbMem2.getValueAt(select,3).toString());
        Mem_phone.setText(tbMem2.getValueAt(select,4).toString());
        Mem_MFG.setText(tbMem2.getValueAt(select,5).toString());
        Mem_EXP.setText(tbMem2.getValueAt(select,6).toString());
        
        Mem_id.setEnabled(false);
        Mem_code.setEnabled(false);
        Mem_name.setEnabled(false);
        Mem_address.setEnabled(false);
        Mem_phone.setEnabled(false);
        Mem_MFG.setEnabled(false);
        Mem_EXP.setEnabled(false);
    }//GEN-LAST:event_tbMem2MouseClicked

    private void Mem_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mem_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mem_idActionPerformed

    private void Mem_MFGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mem_MFGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mem_MFGActionPerformed

    private void Price_book1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Price_book1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Price_book1ActionPerformed

    private void scantext_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scantext_UserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scantext_UserActionPerformed

    private void scan_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scan_userActionPerformed
        showDataRecord_user();
        System.out.println("    ค้นหาสมาชิก");
    }//GEN-LAST:event_scan_userActionPerformed

    private void scan_bookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scan_bookActionPerformed
        showDataRecord_book(); 
        System.out.println("    ค้นหาหนังสือ");
    }//GEN-LAST:event_scan_bookActionPerformed

    private void jฺBorrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jฺBorrowActionPerformed
         insertdata();
         showDataRecord_borrow();
         showDataAll_book();
    }//GEN-LAST:event_jฺBorrowActionPerformed

    private void jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteActionPerformed
      int select2=jBorrow.getSelectedRow(); 
      String Mem=jBorrow.getValueAt(select2,0).toString();
      String book=jBorrow.getValueAt(select2,1).toString();
      String sql="DELETE FROM Borrow_Book where Book_id='"+book+"' and Mem_id='"+Mem+"'";
      calculationSUB();
      try{
          Connection c=Project_Book.connectDB();
          Statement stm=c.createStatement();
          stm.executeUpdate(sql);
              JOptionPane.showMessageDialog(this,"ลบข้อมูลเรียบร้อย");
              showDataRecord_borrow();
          c.close();
          stm.close();
          
        
      }catch(Exception e){
          
          System.out.println(e.getMessage());
      }
       showDataRecord_borrow();
    }//GEN-LAST:event_jDeleteActionPerformed

    private void jBorrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBorrowMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jBorrowMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertdataChange();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jChangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChangActionPerformed
        calculationChange();
    }//GEN-LAST:event_jChangActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                             Home form1 = new Home();
                             form1.setVisible(true);
                             setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Borrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Borrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Borrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Borrow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Borrow().setVisible(true);
            }
        });
    }
  public void showDataAll_book(){
      clearRow();
      String sql="SELECT * FROM TB_book";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          int row=0;
          while(rs.next()){
              model_book.insertRow(model_book.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)});
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
      
  }
  
    public void showDataAll_user(){
      clearRow1();
      String id=scantext_User.getText();
      String sql="SELECT * FROM User_book";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          int row=0;
          while(rs.next()){
              model_user.insertRow(model_user.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
      
  }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Book_id1;
    private javax.swing.JTextField Fine_book1;
    private javax.swing.JTextField Mem_EXP;
    private javax.swing.JTextField Mem_MFG;
    private javax.swing.JTextField Mem_address;
    private javax.swing.JTextField Mem_code;
    private javax.swing.JTextField Mem_id;
    private javax.swing.JTextField Mem_name;
    private javax.swing.JTextField Mem_phone;
    private javax.swing.JTextField MoneyGet;
    private javax.swing.JLabel MoneyRent;
    private javax.swing.JTextField Name_book1;
    private javax.swing.JTextField Price_book1;
    private javax.swing.JTextField RentDate_book1;
    private javax.swing.JTextField RentPrice_book1;
    private javax.swing.JLabel change;
    private javax.swing.JTable jBorrow;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jChang;
    private javax.swing.JButton jDelete;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jฺBorrow;
    private javax.swing.JButton scan_book;
    private javax.swing.JButton scan_user;
    private javax.swing.JTextField scantext_User;
    private javax.swing.JTextField scantext_book;
    private javax.swing.JTable tbMem2;
    private javax.swing.JTable tbbook;
    private javax.swing.JPanel ข้อมูลการยืม;
    private javax.swing.JPanel ข้อมูลสมาชิก;
    private javax.swing.JPanel ข้อมูลหนังสือ;
    private javax.swing.JPanel ทอนเงิน;
    private javax.swing.JPanel ยืมหนังสือ;
    private javax.swing.JPanel รวมสุทธิ;
    private javax.swing.JPanel รับเงิน;
    // End of variables declaration//GEN-END:variables
}
