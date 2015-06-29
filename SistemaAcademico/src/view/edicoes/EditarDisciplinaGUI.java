/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.edicoes;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.dao.Dao;
import model.dao.DisciplinaDaoImpl;
import model.pojo.Disciplina;

public class EditarDisciplinaGUI extends javax.swing.JFrame {

    private static Disciplina disciplina;
    private static Dao disciplinaDao = DisciplinaDaoImpl.getInstancia();
    private static EntityManager em;
    
    /**
     * Creates new form EditarDisciplinaGUI
     */
    public EditarDisciplinaGUI() {
        initComponents();
        setTitle("EDITAR DISCIPLINA");
        setVisible(true);
    }
    
    /**
     *
     * @param em gerenciador de entidades para o BD
     */
    public EditarDisciplinaGUI(EntityManager em) {
        this();
        EditarDisciplinaGUI.em = em;
        bloquearEdicao(true);
    }

    /**
     *
     * @param bloquear determina se a edição será ou não permitida
     */
    public void bloquearEdicao (Boolean bloquear) {
        CampoID.setEditable(bloquear);
        CampoCargaHoraria.setEditable(!bloquear);
        PainelEmenta.setEnabled(!bloquear);
        BotaoConcluir.setEnabled(!bloquear);
    }
    
    /**
     *
     * @param txtCampo1 atualiza o campo com o valor corrente da variável
     * @param txtCampo2 atualiza o campo com o valor corrente da variável
     */
    public void setTexts (String txtCampo1, String txtCampo2) {
        CampoCargaHoraria.setText(txtCampo1);
        PainelEmenta.setText(txtCampo2);
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
        labelCargaHoraria = new javax.swing.JLabel();
        labelEmenta = new javax.swing.JLabel();
        CampoCargaHoraria = new javax.swing.JTextField();
        BotaoConcluir = new javax.swing.JButton();
        BotaoFechar = new javax.swing.JButton();
        labelID = new javax.swing.JLabel();
        CampoID = new javax.swing.JTextField();
        BotaoSuperior = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PainelEmenta = new javax.swing.JTextPane();

        labelCargaHoraria.setText("Carga horária:");

        labelEmenta.setText("Ementa:");

        BotaoConcluir.setText("Concluir");
        BotaoConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoConcluirActionPerformed(evt);
            }
        });

        BotaoFechar.setText("Fechar");
        BotaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoFecharActionPerformed(evt);
            }
        });

        labelID.setText("Nome:");

        BotaoSuperior.setText("Pesquisar");
        BotaoSuperior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSuperiorActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(PainelEmenta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCargaHoraria)
                    .addComponent(labelEmenta)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CampoCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelID)
                                .addGap(18, 18, 18)
                                .addComponent(CampoID, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BotaoConcluir)
                                .addGap(29, 29, 29)
                                .addComponent(BotaoFechar)))
                        .addGap(18, 18, 18)
                        .addComponent(BotaoSuperior)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoSuperior)
                    .addComponent(labelID))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCargaHoraria)
                    .addComponent(CampoCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelEmenta)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoConcluir)
                    .addComponent(BotaoFechar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotaoConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoConcluirActionPerformed
        // TODO add your handling code here:
        disciplina.setCargaHoraria(Integer.valueOf(CampoCargaHoraria.getText()));
        disciplina.setEmenta(PainelEmenta.getText());
        try {
            disciplinaDao.atualizar(em, disciplina);
        } catch (Exception ex) {
            //Logger.getLogger(EditarTurmaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(rootPane, "EDIÇÃO EFETUADA!", "INFORMAÇÃO",
                    JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_BotaoConcluirActionPerformed

    private void BotaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoFecharActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_BotaoFecharActionPerformed

    private void BotaoSuperiorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSuperiorActionPerformed
        // TODO add your handling code here:
        if (BotaoSuperior.getText().equals("Pesquisar")) {
            Disciplina disciplina = (Disciplina) disciplinaDao.obter(em, CampoID.getText());
            if (disciplina != null) {
                EditarDisciplinaGUI.disciplina = disciplina;
                bloquearEdicao(false);
                BotaoSuperior.setText("Alterar");
                setTexts(disciplina.getCargaHoraria().toString(), disciplina.getEmenta());
            }
            else
                JOptionPane.showMessageDialog(rootPane, "DISCIPLINA NÃO CADASTRADA!", "AVISO", 
                        JOptionPane.WARNING_MESSAGE);
        }
        else if (BotaoSuperior.getText().equals("Alterar")) {
            BotaoSuperior.setText("Pesquisar");
            bloquearEdicao(true);
            setTexts("","");
        }
    }//GEN-LAST:event_BotaoSuperiorActionPerformed

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
            java.util.logging.Logger.getLogger(EditarDisciplinaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarDisciplinaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarDisciplinaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarDisciplinaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarDisciplinaGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoConcluir;
    private javax.swing.JButton BotaoFechar;
    private javax.swing.JButton BotaoSuperior;
    private javax.swing.JTextField CampoCargaHoraria;
    private javax.swing.JTextField CampoID;
    private javax.swing.JTextPane PainelEmenta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCargaHoraria;
    private javax.swing.JLabel labelEmenta;
    private javax.swing.JLabel labelID;
    // End of variables declaration//GEN-END:variables
}