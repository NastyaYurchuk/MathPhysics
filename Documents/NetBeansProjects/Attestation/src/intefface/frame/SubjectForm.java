/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intefface.frame;

import dao.DaoFactory;
import dao.entities.Subject;
import dao.entities.User;
import intefface.EditSubjectForm;
import intefface.EditUser;
import java.util.List;
import javax.swing.JOptionPane;
//import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nastja
 */
public class SubjectForm extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    DaoFactory daoFactory;
    List<Subject> dataTable;
    /**
     * Creates new form SubjectForm
     */
    public SubjectForm() {
        initComponents();
        model = (DefaultTableModel)tableSubject.getModel();
        daoFactory = DaoFactory.getInstance(DaoFactory.DaoType.ORACLE);
        
        
        fillTable();
        tableSubject.setRowSelectionAllowed(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSubject = new javax.swing.JTable();
        buttonAddSubject = new javax.swing.JButton();
        buttonEditSubject = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();

        setClosable(true);
        setTitle("Subject Edit");
        setPreferredSize(new java.awt.Dimension(600, 485));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(623, 463));

        tableSubject.setBackground(new java.awt.Color(254, 239, 225));
        tableSubject.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableSubject);

        buttonAddSubject.setBackground(new java.awt.Color(245, 134, 81));
        buttonAddSubject.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        buttonAddSubject.setText("Add");
        buttonAddSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddSubjectActionPerformed(evt);
            }
        });

        buttonEditSubject.setBackground(new java.awt.Color(245, 134, 81));
        buttonEditSubject.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        buttonEditSubject.setText("Edit");
        buttonEditSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditSubjectActionPerformed(evt);
            }
        });

        Delete.setBackground(new java.awt.Color(245, 134, 81));
        Delete.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        buttonRefresh.setText("Refresh");
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonAddSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(buttonEditSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonRefresh)))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRefresh))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Delete)
                    .addComponent(buttonEditSubject)
                    .addComponent(buttonAddSubject))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddSubjectActionPerformed
        EditSubjectForm addSubjectForm = new EditSubjectForm();
        addSubjectForm.setVisible(true);
        fillTable();
    }//GEN-LAST:event_buttonAddSubjectActionPerformed

    private void buttonEditSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditSubjectActionPerformed
      int selectedRow = tableSubject.getSelectedRow();
       if(selectedRow != -1){
           EditSubjectForm editSubject = new EditSubjectForm(dataTable.get(selectedRow));
           
           editSubject.setVisible(true);
       }
       fillTable();
    }//GEN-LAST:event_buttonEditSubjectActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        int[] deleteRows = tableSubject.getSelectedRows();
        boolean result = true;
        for(int i :deleteRows){
            boolean d = daoFactory.createSubjectDao().delete(dataTable.get(i));
            result = result && d;
        }
        if(result){
            JOptionPane.showMessageDialog(rootPane, "All selected subjects deleted");
        }else{
            JOptionPane.showMessageDialog(rootPane, "Some subjects can't be deleted");
        }
        
    }//GEN-LAST:event_DeleteActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        System.out.println("mouse clicked");
    }//GEN-LAST:event_formMouseClicked

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
       System.out.println("focus");
    }//GEN-LAST:event_formFocusGained

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        clearTable();
        System.out.println("left " + tableSubject.getRowCount());
        System.out.println("remove colunmn");
        fillTable();
        System.out.println("new" + tableSubject.getRowCount());
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void clearTable() {
        int n = tableSubject.getRowCount();
        for (int i = 0; i < n; i++) {
            System.out.println(tableSubject.getRowCount());
            model.removeRow(0);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JButton buttonAddSubject;
    private javax.swing.JButton buttonEditSubject;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableSubject;
    // End of variables declaration//GEN-END:variables

    private void fillTable() {
        clearTable();
        dataTable = daoFactory.createSubjectDao().findAll();
        int i = 0;
       for(Subject s : dataTable){
           model.addRow(new Object[]{++i, s.getName()});
       }
    
    }
}
