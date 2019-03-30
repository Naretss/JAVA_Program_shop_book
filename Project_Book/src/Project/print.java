
package Project;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class print extends javax.swing.JFrame {
     DefaultTableModel model_borrow=new DefaultTableModel(); 

    public print() {
        initComponents();
        model_borrow=(DefaultTableModel)jBorrow.getModel();
        
        JTableHeader theader2 = jBorrow.getTableHeader();
        theader2.setFont(new Font("Tahome",Font.PLAIN,20));
        jBorrow.setFont(new Font("Tahome",Font.PLAIN,20));
        showDataRecord_borrow();
                setExtendedState(MAXIMIZED_BOTH);
                DateBorrow.setVisible(false);
                DateReturn.setVisible(false);
    }

public void showDataRecord_borrow(){
    String sql="SELECT * FROM Borrow_Book where Status like '%not return%'";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          while(rs.next()){
              model_borrow.insertRow(model_borrow.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7)});
 
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
  }
  
public void showDataRecord_borrow_scan(){
    clearRow();
    String borrow_on=borrow.getText().toString();
    String sql="SELECT * FROM Borrow_Book where Status like '%not return%' and Borrow_ID like '%"+borrow_on+"%'";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          while(rs.next()){
              model_borrow.insertRow(model_borrow.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7)});
 
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
  }
  
public void showDataRecord_Book_id(){
    clearRow();
    String Book=Book_id.getText().toString();
    String sql="SELECT * FROM Borrow_Book where Status like '%not return%' and Book_id like '%"+Book+"%'";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          while(rs.next()){
              model_borrow.insertRow(model_borrow.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7)});
 
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
  }
  
public void showDataRecord_Mem_id(){
    clearRow();
    String Mem=Mem_id.getText().toString();
    String sql="SELECT * FROM Borrow_Book where Status like '%not return%' and Mem_id like '%"+Mem+"%'";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          while(rs.next()){
              model_borrow.insertRow(model_borrow.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7)});
 
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
  }
 
public void showDataRecord_Book_name(){
    clearRow();
    String Name=Book_name.getText().toString();
    String sql="SELECT * FROM Borrow_Book where Status like '%not return%' and Name_book like '%"+Name+"%'";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          while(rs.next()){
              model_borrow.insertRow(model_borrow.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7)});
 
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
  }

public void showDataRecord_DateBorrow(){
    clearRow();
    String dateB=DateBorrow.getText().toString();
    String sql="SELECT * FROM Borrow_Book where Status like '%not return%' and Borrow_Date like '%"+dateB+"%'";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          while(rs.next()){
              model_borrow.insertRow(model_borrow.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7)});
 
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
  }

public void showDataRecord_DateReturn(){
    clearRow();
    String dateR=DateReturn.getText().toString();
    String sql="SELECT * FROM Borrow_Book where Status like '%not return%' and Borrow_Now like '%"+dateR+"%'";
      try{
          Connection c=Project_Book.connectDB();
          ResultSet rs=c.createStatement().executeQuery(sql);
          while(rs.next()){
              model_borrow.insertRow(model_borrow.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7)});
 
          }
          c.close();
          rs.close();
      }catch(Exception e){
          System.out.println("show "+e.getMessage());
      }
  }


   public void clearRow(){
     int row=model_borrow.getRowCount()-1;
     while(row>-1){
         model_borrow.removeRow(row);
         row--;
    }
 } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        PT = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jBorrow = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        borrow = new javax.swing.JTextField();
        Book_id = new javax.swing.JTextField();
        Mem_id = new javax.swing.JTextField();
        Book_name = new javax.swing.JTextField();
        DateBorrow = new javax.swing.JTextField();
        DateReturn = new javax.swing.JTextField();

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

        PT.setFont(new java.awt.Font("Tahoma", 0, 80)); // NOI18N
        PT.setForeground(new java.awt.Color(255, 255, 255));
        PT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PT.setText("รายงานการค้างส่ง");

        jBorrow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสการยืม", "รหัสหนังสือ", "รหัสสมาชิก", "ชื่อหนังสือ", "วันที่ยืม", "วันกำหนดคืน"
            }
        ));
        jScrollPane1.setViewportView(jBorrow);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jButton1.setText("กลับสู่หน้าหลัก");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        borrow.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        borrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowActionPerformed(evt);
            }
        });

        Book_id.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Book_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Book_idActionPerformed(evt);
            }
        });

        Mem_id.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Mem_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mem_idActionPerformed(evt);
            }
        });

        Book_name.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Book_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Book_nameActionPerformed(evt);
            }
        });

        DateBorrow.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        DateBorrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateBorrowActionPerformed(evt);
            }
        });

        DateReturn.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        DateReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(777, 777, 777)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1640, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(borrow, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Book_id, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Mem_id, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Book_name, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DateBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(DateReturn))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(510, 510, 510)
                        .addComponent(PT, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(436, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(PT, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrow, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Book_id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mem_id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Book_name, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 2243, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                             Home form1 = new Home();
                             form1.setVisible(true);
                             setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void borrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowActionPerformed
       showDataRecord_borrow_scan();
    }//GEN-LAST:event_borrowActionPerformed

    private void Book_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Book_idActionPerformed
       showDataRecord_Book_id();
    }//GEN-LAST:event_Book_idActionPerformed

    private void Mem_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mem_idActionPerformed
       showDataRecord_Mem_id();
    }//GEN-LAST:event_Mem_idActionPerformed

    private void Book_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Book_nameActionPerformed
        showDataRecord_Book_name();
    }//GEN-LAST:event_Book_nameActionPerformed

    private void DateReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateReturnActionPerformed
        showDataRecord_DateReturn();
    }//GEN-LAST:event_DateReturnActionPerformed

    private void DateBorrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateBorrowActionPerformed
        showDataRecord_DateBorrow();
    }//GEN-LAST:event_DateBorrowActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new print().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Book_id;
    private javax.swing.JTextField Book_name;
    private javax.swing.JTextField DateBorrow;
    private javax.swing.JTextField DateReturn;
    private javax.swing.JTextField Mem_id;
    private javax.swing.JLabel PT;
    private javax.swing.JTextField borrow;
    private javax.swing.JTable jBorrow;
    private javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
