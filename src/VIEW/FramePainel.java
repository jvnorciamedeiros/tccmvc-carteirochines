package VIEW;


import CONTROLLER.GraphController;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JV
 */
public class FramePainel extends javax.swing.JFrame {
    //private CPP CarteiroChines;
    private GraphController thisGC;

    /**
     * Creates new form Frame1
     */
    public FramePainel() {
        GraphController controller = new GraphController(this);
        thisGC=controller;
        initComponents();
    }
    
    public boolean getPausado(){
        return CheckBoxPausado.isSelected();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddVerticeBut = new javax.swing.JButton();
        AddArestaBut = new javax.swing.JButton();
        CPPBut = new javax.swing.JButton();
        LimparBut = new javax.swing.JButton();
        CarregarBut = new javax.swing.JButton();
        SalvarBut = new javax.swing.JButton();
        ExibirBut = new javax.swing.JButton();
        CheckBoxPausado = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TCC - Caminho Carteiro Chinês");
        setLocation(new java.awt.Point(400, 400));

        AddVerticeBut.setText("Adicionar Vertice");
        AddVerticeBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddVerticeButActionPerformed(evt);
            }
        });

        AddArestaBut.setText("Adicionar aresta");
        AddArestaBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddArestaButActionPerformed(evt);
            }
        });

        CPPBut.setText("Caminho C. Chinês");
        CPPBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CPPButActionPerformed(evt);
            }
        });

        LimparBut.setText("Limpar Grafo");
        LimparBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimparButActionPerformed(evt);
            }
        });

        CarregarBut.setText("Carregar do Bd");
        CarregarBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CarregarButActionPerformed(evt);
            }
        });

        SalvarBut.setText("Salvar em Bd");
        SalvarBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarButActionPerformed(evt);
            }
        });

        ExibirBut.setText("Exibir Vizualização");
        ExibirBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirButActionPerformed(evt);
            }
        });

        CheckBoxPausado.setText("Execução com CPP Pausada");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddVerticeBut)
                            .addComponent(LimparBut))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AddArestaBut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CPPBut))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CarregarBut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SalvarBut))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ExibirBut)
                        .addGap(18, 18, 18)
                        .addComponent(CheckBoxPausado)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddVerticeBut)
                    .addComponent(AddArestaBut)
                    .addComponent(CPPBut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LimparBut)
                    .addComponent(CarregarBut)
                    .addComponent(SalvarBut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ExibirBut)
                    .addComponent(CheckBoxPausado)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Botão Adicionar Vértice
    private void AddVerticeButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddVerticeButActionPerformed
        // TODO add your handling code here:        
        thisGC.addVertex();
    }//GEN-LAST:event_AddVerticeButActionPerformed
    //Botão Adicionar Aresta
    private void AddArestaButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddArestaButActionPerformed
        // TODO add your handling code here:
        thisGC.addEdge();
    }//GEN-LAST:event_AddArestaButActionPerformed
    //Botão resolver CPP
    private void CPPButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPPButActionPerformed
        // TODO add your handling code here:
        thisGC.CPP();  
    }//GEN-LAST:event_CPPButActionPerformed
    //Botão Limpar
    private void LimparButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimparButActionPerformed
        // TODO add your handling code here:
        thisGC.LimparGraphIn();
    }//GEN-LAST:event_LimparButActionPerformed
    //Botão Exibir Visualização
    private void ExibirButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirButActionPerformed
        // TODO add your handling code here:
        thisGC.ShowHideGraphView();
    }//GEN-LAST:event_ExibirButActionPerformed
    //Botão Salvar na DB
    private void SalvarButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarButActionPerformed
        // TODO add your handling code here:
        thisGC.GravarGrafo();
    }//GEN-LAST:event_SalvarButActionPerformed

    private void CarregarButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CarregarButActionPerformed
        // TODO add your handling code here:    
        thisGC.CarregarGrafo();
    }//GEN-LAST:event_CarregarButActionPerformed

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
            java.util.logging.Logger.getLogger(FramePainel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePainel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePainel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePainel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePainel().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddArestaBut;
    private javax.swing.JButton AddVerticeBut;
    private javax.swing.JButton CPPBut;
    private javax.swing.JButton CarregarBut;
    private javax.swing.JCheckBox CheckBoxPausado;
    private javax.swing.JButton ExibirBut;
    private javax.swing.JButton LimparBut;
    private javax.swing.JButton SalvarBut;
    // End of variables declaration//GEN-END:variables

}
