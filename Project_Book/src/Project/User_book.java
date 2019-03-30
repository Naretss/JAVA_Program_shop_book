
package Project;
import java.sql.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;


public class User_book extends javax.swing.JFrame {
    DefaultTableModel model=new DefaultTableModel(); //การจำลองตาราง
    Object[] data=new Object[0];
        
         Calendar c = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");  //ช่วงของเวลาปัจจุบัน
         String currentDate = df.format(c.getTime());
        
         GregorianCalendar gc = new GregorianCalendar();
         int y_on=Integer.valueOf(gc.get(Calendar.YEAR))+543;
         int y1=Integer.valueOf(gc.get(Calendar.YEAR))+544;
         //int y=Integer.valueOf(gc.get(Calendar.YEAR))+544;
    public User_book() {
        initComponents();
//        Mem_MFG.setText(currentDate);
        Mem_MFG.setText(gc.get(Calendar.DATE)+"-"+gc.get(Calendar.MONTH)+"-"+y_on);
        Mem_EXP.setText(gc.get(Calendar.DATE)+"-"+gc.get(Calendar.MONTH)+"-"+y1);
        model=(DefaultTableModel)tbMem.getModel();
        showDataRecord();
        
       jDelete.setEnabled(false);
      
       jEdit.setEnabled(false);
    }
 /******ให้วันเวลาในเท๊กเหมือนเดิม****/
  public void DatetimeSet(){
        Mem_MFG.setText(gc.get(Calendar.DATE)+"-"+gc.get(Calendar.MONTH)+"-"+y_on);
        Mem_EXP.setText(gc.get(Calendar.DATE)+"-"+gc.get(Calendar.MONTH)+"-"+y1);
  }
 /*******โชว์ข้อมูล********************/
  public void showDataRecord(){
      clearRow();
      String sql="SELECT * FROM User_book";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          int row=0;
          while(rs.next()){
              model.insertRow(model.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
      cleartax();
  }
 /*****ล้างตารางที่โชว์เฉยๆ**************/
  public void cleartax(){
      Mem_id.setText("Mem0000");
      Mem_code.setText("");
      Mem_name.setText("");
      Mem_address.setText("");
      Mem_phone.setText("");
      
      Mem_id.setEnabled(true); //??????????????
      DatetimeSet();
  }
  public void clearRow(){
     int row=model.getRowCount()-1;
     while(row>-1){
         model.removeRow(row);
         row--;
    }
 } 
 /*******เพิ่มข้อมูล********************/
  public void insertdata(){
       String ID=Mem_id.getText();
       String CODE=Mem_code.getText();
       String NAME=Mem_name.getText();
       String ADDRESS=Mem_address.getText();
       String PHONE=Mem_phone.getText();
       String MFG=Mem_MFG.getText();
       String EXP=Mem_EXP.getText();
       String sql="INSERT INTO User_book VALUES("
               + "'"+ID+"',"
               +"'"+CODE+"',"
               + "'"+NAME+"',"
               + "'"+ADDRESS+"',"
               + "'"+PHONE+"',"
               + "'"+MFG+"',"
               + "'"+EXP+"')";
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
 /*******อัพเดทข้อมูล********************/
  public void udpateRecord(){
       String ID=Mem_id.getText();
       String CODE=Mem_code.getText();
       String NAME=Mem_name.getText();
       String ADDRESS=Mem_address.getText();
       String PHONE=Mem_phone.getText();
       String MFG=Mem_MFG.getText();
       String EXP=Mem_EXP.getText();
      String sql="UPDATE User_book SET  Mem_code='"+CODE+"',Mem_Name='"+NAME+"' ,Mem_address='"+ADDRESS+"' , Mem_phone ='"+PHONE+"' ,"
              + "Mem_MFG ='"+MFG+"' , Mem_EXP ='"+EXP+"'  WHERE Mem_id LIKE '%"+ID+"%'";
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
          JOptionPane.showMessageDialog(this,"แจ้งข้อผิดผลาด : "+e.getMessage());
          System.out.println(e.getMessage());
      }
  }
  
  public void deleteRecord(){
      String ID=Mem_id.getText();
      String sql="DELETE FROM User_book where Mem_id='"+ID+"'";
      try{
          Connection c=Project_Book.connectDB();
          Statement stm=c.createStatement();
          stm.executeUpdate(sql);
              JOptionPane.showMessageDialog(this,"ลบข้อมูลเรียบร้อย");
              showDataRecord();
              c.close();
              stm.close();
          
      }catch(Exception e){
            JOptionPane.showMessageDialog(this,"แจ้งข้อผิดผลาด : "+e.getMessage());
          System.out.println(e.getMessage());
      }
  }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMem = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Mem_id = new javax.swing.JTextField();
        Mem_code = new javax.swing.JTextField();
        Mem_name = new javax.swing.JTextField();
        Mem_address = new javax.swing.JTextField();
        Mem_phone = new javax.swing.JTextField();
        Mem_MFG = new javax.swing.JTextField();
        Mem_EXP = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jEdit = new javax.swing.JButton();
        jDelete = new javax.swing.JButton();
        jSave = new javax.swing.JButton();
        jClean = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ข้อมูลสมาชิก", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        tbMem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสสมาชิก", "เลขบัตรประชาชน", "ชื่อ นามสกุล", "ที่อยู่", "เบอรฺ์โทร", "ัวันที่สมัคร", "วันหมดอายุ"
            }
        ));
        tbMem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMem);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("รหัสสมาชิก");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("เลขบัตรประชาชน");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ชื่อ-นามสกุล");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ที่อยู่");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("เบอร์โทร");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("วันที่สมัคร");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("วันหมดอายุ");

        Mem_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Mem_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mem_idActionPerformed(evt);
            }
        });

        Mem_code.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        Mem_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        Mem_address.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Mem_address.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Mem_address.setToolTipText("");

        Mem_phone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        Mem_MFG.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Mem_MFG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mem_MFGActionPerformed(evt);
            }
        });

        Mem_EXP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Mem_EXP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mem_EXPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(31, 31, 31)
                        .addComponent(Mem_MFG, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(Mem_EXP, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(666, 666, 666))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Mem_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(Mem_id, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(9, 9, 9)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Mem_code, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(Mem_name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Mem_address)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(Mem_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mem_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Mem_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(Mem_address, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Mem_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Mem_MFG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(Mem_EXP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jEdit.setBackground(new java.awt.Color(255, 255, 255));
        jEdit.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jEdit.setText("แก้ไข");
        jEdit.setToolTipText("");
        jEdit.setActionCommand("");
        jEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditActionPerformed(evt);
            }
        });

        jDelete.setBackground(new java.awt.Color(255, 255, 255));
        jDelete.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jDelete.setText("ลบ");
        jDelete.setToolTipText("");
        jDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteActionPerformed(evt);
            }
        });

        jSave.setBackground(new java.awt.Color(255, 255, 255));
        jSave.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jSave.setText("บันทึก");
        jSave.setToolTipText("");
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
            }
        });

        jClean.setBackground(new java.awt.Color(255, 255, 255));
        jClean.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jClean.setText("Clean");
        jClean.setToolTipText("");
        jClean.setActionCommand("");
        jClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCleanActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("กลับสู่หน้าหลัก");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jClean, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jClean, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(321, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Mem_MFGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mem_MFGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mem_MFGActionPerformed

    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
            insertdata();
            clearRow();
            showDataRecord();
    }//GEN-LAST:event_jSaveActionPerformed

    private void Mem_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mem_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mem_idActionPerformed

    private void tbMemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMemMouseClicked
       int select=tbMem.getSelectedRow();
       Mem_id.setText(tbMem.getValueAt(select,0).toString());
       Mem_code.setText(tbMem.getValueAt(select,1).toString());
       Mem_name.setText(tbMem.getValueAt(select,2).toString());
       Mem_address.setText(tbMem.getValueAt(select,3).toString());
       Mem_phone.setText(tbMem.getValueAt(select,4).toString());
       Mem_MFG.setText(tbMem.getValueAt(select,5).toString());
       Mem_EXP.setText(tbMem.getValueAt(select,6).toString());
       
       jClean.setEnabled(false);
       jDelete.setEnabled(true);
       jSave.setEnabled(false);
       jEdit.setEnabled(true);
       Mem_id.setEnabled(false); //ไม่ให้เปลี่ยนรหัส
    }//GEN-LAST:event_tbMemMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        
       jClean.setEnabled(true);
       jDelete.setEnabled(false);
       jSave.setEnabled(true);
       jEdit.setEnabled(false);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        udpateRecord();
    }//GEN-LAST:event_jEditActionPerformed

    private void jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteActionPerformed
        deleteRecord();
    }//GEN-LAST:event_jDeleteActionPerformed

    private void jCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCleanActionPerformed
        cleartax();
    }//GEN-LAST:event_jCleanActionPerformed

    private void Mem_EXPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mem_EXPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mem_EXPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Home form1 = new Home();
        form1.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(User_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_book().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Mem_EXP;
    private javax.swing.JTextField Mem_MFG;
    private javax.swing.JTextField Mem_address;
    private javax.swing.JTextField Mem_code;
    private javax.swing.JTextField Mem_id;
    private javax.swing.JTextField Mem_name;
    private javax.swing.JTextField Mem_phone;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jClean;
    private javax.swing.JButton jDelete;
    private javax.swing.JButton jEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbMem;
    // End of variables declaration//GEN-END:variables
}
