//double intA =  Double.valueOf(numA.getText()); // 
//double intB =  Double.valueOf(numB.getText()); //
package Project;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class TB_book extends javax.swing.JFrame {
    DefaultTableModel model=new DefaultTableModel(); //การจำลองตาราง
    Object[] data=new Object[0];
         
    public TB_book() {
        initComponents();
        
        model=(DefaultTableModel)tbMem.getModel();
        showDataRecord();
        jEdit.setEnabled(false);
        jDelete.setEnabled(false);

    }

 /*******โชว์ข้อมูล********************/
  public void showDataRecord(){
      clearRow();
      String sql="SELECT * FROM TB_book";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          int row=0;
          while(rs.next()){
              model.insertRow(model.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)});
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
      Book_id.setText("B0000");
      Fine_book.setText("0");
      Name_book.setText("");
      Price_book.setText("0");
      RentDate_book.setText("0");
      RentPrice_book.setText("0");
      Book_id.setEnabled(true); //ให้เปลี่ยนรหัส

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
       String ID=Book_id.getText();
       String NAME=Name_book.getText();
       float Price=Float.valueOf(Price_book.getText());
       int RentDate=Integer.valueOf(RentDate_book.getText());
       int Fine=Integer.valueOf(Fine_book.getText());
       int RentPrice=Integer.valueOf(RentPrice_book.getText());
       String sql="INSERT INTO TB_book VALUES("
               + "'"+ID+"',"
               +"'"+NAME+"',"
               + ""+Price+","
               + ""+RentDate+","
               + ""+RentPrice+","
               + ""+Fine+")";
        try{
            Connection c=Project_Book.connectDB(); //เชื่อมฐานข้อมูล
            Statement stm=c.createStatement();//ทำการสร้างชุดคำสั่งขึ้นขึนมาเพื่อเอามาใช้
            int s = stm.executeUpdate(sql);//นำคำสั้ง sql ไปดำเนินการ
            System.out.println("บันทึกข้อมูล");
            JOptionPane.showMessageDialog(this,"บันทึกข้อมูลเรียบร้อย");
            stm.close();
            c.close();
            
        }catch(Exception e){
            
            System.out.println(e.getMessage());
        }
  }
 /*******อัพเดทข้อมูล********************/
  public void udpateRecord(){
       String ID=Book_id.getText();
       String NAME=Name_book.getText();
       float Price=Float.valueOf(Price_book.getText());
       int RentDate=Integer.valueOf(RentDate_book.getText());
       int RentPrice=Integer.valueOf(RentPrice_book.getText());
       int Fine=Integer.valueOf(Fine_book.getText());
       String sql="UPDATE TB_book SET  Name_book='"+NAME+"',Price_book="+Price+",RentDate_book="+RentDate+",RentPrice_book="+RentPrice+", Fine_book ="+Fine+" where Book_id='"+ID+"'";
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
      String ID=Book_id.getText();
      String sql="DELETE FROM TB_book where Book_id='"+ID+"'";
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMem = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSave = new javax.swing.JButton();
        jDelete = new javax.swing.JButton();
        jEdit = new javax.swing.JButton();
        Book_id = new javax.swing.JTextField();
        Name_book = new javax.swing.JTextField();
        Price_book = new javax.swing.JTextField();
        Fine_book = new javax.swing.JTextField();
        RentDate_book = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        RentPrice_book = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jClean = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ข้อมูลหนังสือ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        tbMem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbMem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสหนังสือ", "ชื่อหนังสือ", "ราคาหนังสือ", "จำนวนวันที่เช่าได้", "ค่าเช่า", "ค่าปรับ"
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("รหัสหนังสือ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ชื่อหนังสือ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("บาท");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("จำนวนวันที่เช่าได้");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ค่าปรับ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ราคาหนังสือ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("วัน");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("บาท");

        jSave.setBackground(new java.awt.Color(255, 255, 255));
        jSave.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jSave.setText("บันทึก");
        jSave.setToolTipText("");
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
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

        Book_id.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N

        Name_book.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N

        Price_book.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        Price_book.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Price_book.setText("0");
        Price_book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Price_bookActionPerformed(evt);
            }
        });

        Fine_book.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        Fine_book.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Fine_book.setText("0");

        RentDate_book.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        RentDate_book.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ค่าเช่า");

        RentPrice_book.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        RentPrice_book.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        RentPrice_book.setText("0");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("บาท");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(Book_id, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Name_book, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Price_book, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(75, 75, 75)
                                .addComponent(RentPrice_book, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(RentDate_book, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Fine_book, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))))
                .addGap(80, 80, 80)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jClean, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jClean, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(Fine_book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(Book_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Name_book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4)
                                    .addComponent(Price_book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(RentDate_book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(RentPrice_book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel11))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1259, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
            insertdata();
            showDataRecord();
    }//GEN-LAST:event_jSaveActionPerformed

    private void tbMemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMemMouseClicked
       int select=tbMem.getSelectedRow();
       Book_id.setText(tbMem.getValueAt(select,0).toString());
       Name_book.setText(tbMem.getValueAt(select,1).toString());
       Price_book.setText(tbMem.getValueAt(select,2).toString());
       RentDate_book.setText(tbMem.getValueAt(select,3).toString());
       RentPrice_book.setText(tbMem.getValueAt(select,4).toString());
       Fine_book.setText(tbMem.getValueAt(select,5).toString());
       
       jSave.setEnabled(false);
       jClean.setEnabled(false);
       jEdit.setEnabled(true);
       jDelete.setEnabled(true);

       Book_id.setEnabled(false); //ไม่ให้เปลี่ยนรหัส
    }//GEN-LAST:event_tbMemMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
               jSave.setEnabled(true);
       jClean.setEnabled(true);
       jEdit.setEnabled(false);
       jDelete.setEnabled(false);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        udpateRecord();
        showDataRecord();
    }//GEN-LAST:event_jEditActionPerformed

    private void jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteActionPerformed
        deleteRecord();
        showDataRecord();
    }//GEN-LAST:event_jDeleteActionPerformed

    private void Price_bookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Price_bookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Price_bookActionPerformed

    private void jCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCleanActionPerformed
        cleartax();
    }//GEN-LAST:event_jCleanActionPerformed

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
            java.util.logging.Logger.getLogger(TB_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TB_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TB_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TB_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TB_book().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Book_id;
    private javax.swing.JTextField Fine_book;
    private javax.swing.JTextField Name_book;
    private javax.swing.JTextField Price_book;
    private javax.swing.JTextField RentDate_book;
    private javax.swing.JTextField RentPrice_book;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jClean;
    private javax.swing.JButton jDelete;
    private javax.swing.JButton jEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbMem;
    // End of variables declaration//GEN-END:variables
}
