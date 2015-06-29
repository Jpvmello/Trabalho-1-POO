/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.edicoes;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.dao.Dao;
import model.dao.FaltaDaoImpl;
import model.pojo.Falta;

public class EditarFaltaGUI extends javax.swing.JFrame {

    private static Falta falta;
    private static Dao faltaDao = FaltaDaoImpl.getInstancia();
    private static EntityManager em;
    
    /**
     * Creates new form EditarFaltaGUI
     */
    public EditarFaltaGUI() {
        initComponents();
        setTitle("EDITAR REGISTRO DE FALTAS");
        setVisible(true);
    }
    
    /**
     *
     * @param em gerenciador de entidades para o BD
     */
    public EditarFaltaGUI(EntityManager em) {
        this();
        EditarFaltaGUI.em = em;
        bloquearEdicao(true);
        bloquearFramesNaoEditaveis();
    }

    /**
     *
     * @param bloquear determina se a edição será ou não permitida
     */
    public void bloquearEdicao (Boolean bloquear) {
        CampoID.setEditable(bloquear);
        CampoFaltas.setEditable(!bloquear);
        BotaoConcluir.setEnabled(!bloquear);
    }
    
    public void bloquearFramesNaoEditaveis() {
        CampoAluno.setEditable(false);
        CampoDisciplina.setEditable(false);
    }
    
    /**
     *
     * @param txtCampo1 atualiza o campo com o valor corrente da variável
     * @param txtCampo2 atualiza o campo com o valor corrente da variável
     * @param txtCampo3 atualiza o campo com o valor corrente da variável
     */
    public void setTexts (String txtCampo1, String txtCampo2, String txtCampo3) {
        CampoFaltas.setText(txtCampo1);
        CampoAluno.setText(txtCampo2);
        CampoDisciplina.setText(txtCampo3);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelFaltas = new javax.swing.JLabel();
        labelDisciplina = new javax.swing.JLabel();
        CampoFaltas = new javax.swing.JTextField();
        CampoDisciplina = new javax.swing.JTextField();
        BotaoConcluir = new javax.swing.JButton();
        BotaoFechar = new javax.swing.JButton();
        labelID = new javax.swing.JLabel();
        CampoID = new javax.swing.JTextField();
        BotaoSuperior = new javax.swing.JButton();
        CampoAluno = new javax.swing.JTextField();
        labelAluno = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        labelFaltas.setText("Faltas:");

        labelDisciplina.setText("Disciplina:");

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

        labelID.setText("ID:");

        BotaoSuperior.setText("Pesquisar");
        BotaoSuperior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSuperiorActionPerformed(evt);
            }
        });

        CampoAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoAlunoActionPerformed(evt);
            }
        });

        labelAluno.setText("Aluno:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(BotaoConcluir)
                .addGap(18, 18, 18)
                .addComponent(BotaoFechar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelDisciplina)
                        .addComponent(labelFaltas)
                        .addComponent(labelID))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelAluno)
                        .addGap(14, 14, 14)))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CampoAluno)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 167, Short.MAX_VALUE)
                        .addComponent(CampoFaltas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CampoDisciplina, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CampoID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(BotaoSuperior)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelID)
                    .addComponent(CampoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoSuperior))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoFaltas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFaltas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAluno))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDisciplina))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotaoConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoConcluirActionPerformed
        // TODO add your handling code here:
        falta.setFalta(Integer.valueOf(CampoFaltas.getText()));
        try {
            faltaDao.atualizar(em, falta);
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
            Falta falta = (Falta) faltaDao.obter(em, CampoID.getText());
            if (falta != null) {
                EditarFaltaGUI.falta = falta;
                bloquearEdicao(false);
                BotaoSuperior.setText("Alterar");
                setTexts(falta.getFalta().toString(), falta.getAluno().getNome(), 
                        falta.getTurma().getDisciplina().getNome());
            }
            else
                JOptionPane.showMessageDialog(rootPane, "REGISTRO DE FALTAS NÃO CADASTRADO!", "AVISO", 
                        JOptionPane.WARNING_MESSAGE);
        }
        else if (BotaoSuperior.getText().equals("Alterar")) {
            BotaoSuperior.setText("Pesquisar");
            bloquearEdicao(true);
            setTexts("","","");
        }
    }//GEN-LAST:event_BotaoSuperiorActionPerformed

    private void CampoAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoAlunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoAlunoActionPerformed

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
            java.util.logging.Logger.getLogger(EditarFaltaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarFaltaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarFaltaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarFaltaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarFaltaGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoConcluir;
    private javax.swing.JButton BotaoFechar;
    private javax.swing.JButton BotaoSuperior;
    private javax.swing.JTextField CampoAluno;
    private javax.swing.JTextField CampoDisciplina;
    private javax.swing.JTextField CampoFaltas;
    private javax.swing.JTextField CampoID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelAluno;
    private javax.swing.JLabel labelDisciplina;
    private javax.swing.JLabel labelFaltas;
    private javax.swing.JLabel labelID;
    // End of variables declaration//GEN-END:variables
}
