/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gerenciamento;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.dao.Dao;
import model.dao.DisciplinaDaoImpl;
import model.dao.ProfessorDaoImpl;
import model.pojo.Disciplina;
import model.pojo.Professor;

/**
 *
 * @author Filipe
 */
public class AtribuirProfessor extends javax.swing.JFrame {

    /**
     * Creates new form AtribuirDisciplina
     */
    private static EntityManager em;
    private static Dao professorDao = ProfessorDaoImpl.getInstancia();
    private static Dao disciplinaDao = DisciplinaDaoImpl.getInstancia();
    
    public AtribuirProfessor() {
        initComponents();
    }
    
    public AtribuirProfessor(EntityManager em){
        this();
        AtribuirProfessor.em = em;
        this.setTitle("ATRIBUIR PROFESSOR A DISCIPLINA");
        this.setVisible(true);     
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cpf = new javax.swing.JTextField();
        disciplina = new javax.swing.JTextField();
        atribuir = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        jLabel1.setText("CPF do Professor:");

        jLabel2.setText("Disciplina:");

        atribuir.setText("Atribuir");
        atribuir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atribuirActionPerformed(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(disciplina, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(cpf)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(atribuir)
                        .addGap(184, 184, 184)
                        .addComponent(cancelar)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(disciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(atribuir)
                    .addComponent(cancelar))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atribuirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atribuirActionPerformed
        // TODO add your handling code here:
        String cpfProfessor = cpf.getText();
        String nomeDisciplina = disciplina.getText();
        System.out.println("Informe o nome da disciplina: ");
        Disciplina disciplina = (Disciplina) disciplinaDao.obter(em, nomeDisciplina);
        if(disciplina != null) {
            System.out.println("Informe o CPF do(a) professor(a) a ser atribuído à disciplina: ");
            Professor professor = (Professor) professorDao.obter(em, cpfProfessor);
            if (professor != null)
                if (professor.adicionarDisciplina(disciplina))
                    JOptionPane.showMessageDialog(rootPane, "PROFESSOR ATRIBUIDO A DISCIPLINA COM SUCESSO!", "INFORMAÇÃO",
                            JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(rootPane, "ESTE PROFESSOR JÁ FOI ATRIBUÍDO A ESTA DISCIPLINA"
                            + " ANTERIORMENTE!", "AVISO",JOptionPane.WARNING_MESSAGE);                    
            else
                JOptionPane.showMessageDialog(rootPane, "PROFESSOR NÃO ENCONTRADO!", "AVISO",JOptionPane.WARNING_MESSAGE);                
        }
        else
            JOptionPane.showMessageDialog(rootPane, "DISCIPLINA NÃO ENCONTRADA!", "AVISO",JOptionPane.WARNING_MESSAGE);                     
    }//GEN-LAST:event_atribuirActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_cancelarActionPerformed

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
            java.util.logging.Logger.getLogger(AtribuirProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtribuirProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtribuirProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtribuirProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AtribuirProfessor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atribuir;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField cpf;
    private javax.swing.JTextField disciplina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}