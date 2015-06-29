/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.edicoes;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.dao.Dao;
import model.dao.TurmaDaoImpl;
import model.pojo.Turma;

public class EditarTurmaGUI extends javax.swing.JFrame {

    private static Turma turma;
    private static Dao turmaDao = TurmaDaoImpl.getInstancia();
    private static EntityManager em;
    
    /**
     * Creates new form EditarTurmaGUI
     */
    public EditarTurmaGUI() {
        initComponents();
        setTitle("EDITAR TURMA");
        setVisible(true);
    }
    
    /**
     *
     * @param em gerenciador de entidades para o BD
     */
    public EditarTurmaGUI(EntityManager em) {
        this();
        EditarTurmaGUI.em = em;
        bloquearEdicao(true);
        bloquearFramesNaoEditaveis();
    }

    /**
     *
     * @param bloquear determina se a edição será ou não permitida
     */
    public void bloquearEdicao (Boolean bloquear) {
        CampoID.setEditable(bloquear);
        CampoAno.setEditable(!bloquear);
        CampoPeriodo.setEditable(!bloquear);
        CampoNumeroDeVagas.setEditable(!bloquear);
        BotaoConcluir.setEnabled(!bloquear);
    }
    
    public void bloquearFramesNaoEditaveis() {
        CampoDisciplina.setEditable(false);
        CampoProfessor.setEditable(false);
    }
    
    /**
     *
     * @param txtCampo1 atualiza o campo com o valor corrente da variável
     * @param txtCampo2 atualiza o campo com o valor corrente da variável
     * @param txtCampo3 atualiza o campo com o valor corrente da variável
     * @param txtCampo4 atualiza o campo com o valor corrente da variável
     * @param txtCampo5 atualiza o campo com o valor corrente da variável
     */
    public void setTexts (String txtCampo1, String txtCampo2, String txtCampo3, String txtCampo4, String txtCampo5) {
        CampoAno.setText(txtCampo1);
        CampoPeriodo.setText(txtCampo2);
        CampoNumeroDeVagas.setText(txtCampo3);
        CampoDisciplina.setText(txtCampo4);
        CampoProfessor.setText(txtCampo5);
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
        labelAno = new javax.swing.JLabel();
        labelPeriodo = new javax.swing.JLabel();
        labelNumeroDeVagas = new javax.swing.JLabel();
        CampoAno = new javax.swing.JTextField();
        CampoPeriodo = new javax.swing.JTextField();
        CampoNumeroDeVagas = new javax.swing.JTextField();
        BotaoConcluir = new javax.swing.JButton();
        BotaoFechar = new javax.swing.JButton();
        labelID = new javax.swing.JLabel();
        CampoID = new javax.swing.JTextField();
        BotaoSuperior = new javax.swing.JButton();
        CampoDisciplina = new javax.swing.JTextField();
        labelDisciplina = new javax.swing.JLabel();
        CampoProfessor = new javax.swing.JTextField();
        labelProfessor = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        labelAno.setText("Ano:");

        labelPeriodo.setText("Período:");

        labelNumeroDeVagas.setText("Número de vagas:");

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

        labelDisciplina.setText("Disciplina:");

        labelProfessor.setText("Professor:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BotaoConcluir)
                        .addGap(18, 18, 18)
                        .addComponent(BotaoFechar))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelProfessor)
                                .addComponent(labelDisciplina))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CampoDisciplina)
                                .addComponent(CampoProfessor)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelNumeroDeVagas, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelPeriodo, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelAno, javax.swing.GroupLayout.Alignment.LEADING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CampoPeriodo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CampoNumeroDeVagas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CampoAno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(labelID)
                            .addGap(18, 18, 18)
                            .addComponent(CampoID, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
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
                    .addComponent(CampoAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAno))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPeriodo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoNumeroDeVagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNumeroDeVagas))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDisciplina))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelProfessor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
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
        if (Integer.valueOf(CampoNumeroDeVagas.getText()) >= turma.getAluno().size()) {
            turma.setAno(Integer.valueOf(CampoAno.getText()));
            turma.setPeriodo(Integer.valueOf(CampoPeriodo.getText()));
            turma.setNumeroDeVagas(Integer.valueOf(CampoNumeroDeVagas.getText()));
            try {
                turmaDao.atualizar(em, turma);
            } catch (Exception ex) {
                //Logger.getLogger(EditarTurmaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(rootPane, "EDIÇÃO EFETUADA!", "INFORMAÇÃO",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(rootPane, "O NÚMERO DE ALUNOS JÁ MATRICULADOS NA TURMA EXCEDE "
                                    + "O NÚMERO DE VAGAS ESTIPULADO!", "AVISO", 
                    JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_BotaoConcluirActionPerformed

    private void BotaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoFecharActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_BotaoFecharActionPerformed

    private void BotaoSuperiorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSuperiorActionPerformed
        // TODO add your handling code here:
        if (BotaoSuperior.getText().equals("Pesquisar")) {
            Turma turma = (Turma) turmaDao.obter(em, CampoID.getText());
            if (turma != null) {
                EditarTurmaGUI.turma = turma;
                bloquearEdicao(false);
                BotaoSuperior.setText("Alterar");
                setTexts(turma.getAno().toString(), turma.getPeriodo().toString(),
                        turma.getNumeroDeVagas().toString(), turma.getDisciplina().getNome(),
                        turma.getProfessor().getNome());
            }
            else
                JOptionPane.showMessageDialog(rootPane, "TURMA NÃO CADASTRADA!", "AVISO", 
                        JOptionPane.WARNING_MESSAGE);
        }
        else if (BotaoSuperior.getText().equals("Alterar")) {
            BotaoSuperior.setText("Pesquisar");
            bloquearEdicao(true);
            setTexts("","","","","");
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
            java.util.logging.Logger.getLogger(EditarTurmaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarTurmaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarTurmaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarTurmaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarTurmaGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoConcluir;
    private javax.swing.JButton BotaoFechar;
    private javax.swing.JButton BotaoSuperior;
    private javax.swing.JTextField CampoAno;
    private javax.swing.JTextField CampoDisciplina;
    private javax.swing.JTextField CampoID;
    private javax.swing.JTextField CampoNumeroDeVagas;
    private javax.swing.JTextField CampoPeriodo;
    private javax.swing.JTextField CampoProfessor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelAno;
    private javax.swing.JLabel labelDisciplina;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelNumeroDeVagas;
    private javax.swing.JLabel labelPeriodo;
    private javax.swing.JLabel labelProfessor;
    // End of variables declaration//GEN-END:variables
}
