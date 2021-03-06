/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JTable;
import model.dao.AlunoDaoImpl;
import model.dao.AtividadeDaoImpl;
import model.dao.AulaDaoImpl;
import model.dao.Dao;
import model.dao.DisciplinaDaoImpl;
import model.dao.FaltaDaoImpl;
import model.dao.NotaDaoImpl;
import model.dao.ProfessorDaoImpl;
import model.dao.TurmaDaoImpl;
import model.pojo.Aluno;
import model.pojo.Atividade;
import model.pojo.Aula;
import model.pojo.Disciplina;
import model.pojo.Falta;
import model.pojo.Nota;
import model.pojo.Professor;
import model.pojo.Turma;

public class MenuListarGUI extends javax.swing.JFrame {

    /**
     * Creates new form MenuListarGUI
     */
    
    private static EntityManager em;
    private Dao alunoDao = AlunoDaoImpl.getInstancia();
    private Dao professorDao = ProfessorDaoImpl.getInstancia();
    private Dao turmaDao = TurmaDaoImpl.getInstancia();
    private Dao disciplinaDao = DisciplinaDaoImpl.getInstancia();
    private Dao notaDao = NotaDaoImpl.getInstancia();
    private Dao faltaDao = FaltaDaoImpl.getInstancia();
    private Dao aulaDao = AulaDaoImpl.getInstancia();
    private Dao atividadeDao = AtividadeDaoImpl.getInstancia();
            
            
    public MenuListarGUI() {
        initComponents();
        setTitle("LISTAR");
        setVisible(true);
    }
    
    public MenuListarGUI(EntityManager em){
        this();
        MenuListarGUI.em = em;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opcao = new javax.swing.JComboBox();
        cancelar = new javax.swing.JButton();
        listar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaListagem = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        opcao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alunos", "Professores", "Turmas", "Disciplinas", "Atividades", "Aulas", "Notas", "Faltas", " " }));
        opcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcaoActionPerformed(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        listar.setText("Listar");
        listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarActionPerformed(evt);
            }
        });

        areaListagem.setColumns(20);
        areaListagem.setRows(5);
        jScrollPane1.setViewportView(areaListagem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(listar)
                        .addGap(129, 129, 129)
                        .addComponent(cancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(opcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(opcao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listar)
                    .addComponent(cancelar))
                .addContainerGap(218, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(96, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarActionPerformed
        // TODO add your handling code here:
        switch(opcao.getSelectedItem().toString()){
            case "Alunos":{
                areaListagem.setText(null);
                List<Aluno> attachedAluno = alunoDao.obterTodos(em);
                for(Aluno aluno : attachedAluno){
                    areaListagem.append(aluno.toString() + "\n");
                }
             break;
            }
            case "Atividades":{
                areaListagem.setText("");
                List<Atividade> attachedAtividade = atividadeDao.obterTodos(em);
                for(Atividade atividade : attachedAtividade){
                    areaListagem.append(atividade.toString() + "\n");
                }
                break;
            }
            case "Professores":{
                areaListagem.setText(null);
                List<Professor> attachedProfessor = professorDao.obterTodos(em);
                for(Professor professor : attachedProfessor){
                    areaListagem.append(professor.toString() + "\n");
                }
            break;
            }
            case "Aulas":{
                areaListagem.setText(null);
                List<Aula> attachedAula = aulaDao.obterTodos(em);
                for(Aula aula : attachedAula){
                    areaListagem.append(aula.toString() + "\n");
                }
            break;
            }
            case "Disciplinas":{
                areaListagem.setText(null);
                List<Disciplina> attachedDisciplina = disciplinaDao.obterTodos(em);
                for(Disciplina disciplina : attachedDisciplina){
                    areaListagem.append(disciplina.toString() + "\n");
                }
            break;
            }
            case "Turmas":{ 
                areaListagem.setText(null);
                List<Turma> attachedTurma = turmaDao.obterTodos(em);
                for(Turma turma : attachedTurma){
                    areaListagem.append(turma.toString() + "\n");
                }
            break;
            }
            case "Notas":{
                areaListagem.setText(null);
                List<Nota> attachedNota = notaDao.obterTodos(em);
                for(Nota nota : attachedNota){
                    areaListagem.append(nota.toString() + "\n");
                }
            break;
            }
            case "Faltas":{
                areaListagem.setText(null);
                List<Falta> attachedFalta = faltaDao.obterTodos(em);
                for(Falta falta : attachedFalta){  
                    areaListagem.append(falta.toString() + "\n");
                }
            break;
            }
        }
    }//GEN-LAST:event_listarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_cancelarActionPerformed

    private void opcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcaoActionPerformed

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
            java.util.logging.Logger.getLogger(MenuListarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuListarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuListarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuListarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuListarGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaListagem;
    private javax.swing.JButton cancelar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton listar;
    private javax.swing.JComboBox opcao;
    // End of variables declaration//GEN-END:variables
}
