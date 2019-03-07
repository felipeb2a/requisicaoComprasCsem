/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author felipe.ferreira
 */
public class BarraDeProgresso {

    JFrame frame;
    JProgressBar progressBar;
    Label lbStatus;

    public JProgressBar getJProgressBar() {

        UIManager.put("ProgressBar.background", Color.blue);
        UIManager.put("ProgressBar.foreground", Color.green);
        UIManager.put("ProgressBar.selectionBackground", Color.blue);
        UIManager.put("ProgressBar.selectionForeground", Color.green);
        

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException ex) {
            Logger.getLogger(BarraDeProgresso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BarraDeProgresso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BarraDeProgresso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BarraDeProgresso.class.getName()).log(Level.SEVERE, null, ex);
        }

        frame = new JFrame("Status");
        frame.setSize(400, 80);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //sem fechar aplicacao
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fecha toda aplicacao
        frame.setLocationRelativeTo(null); //frame centralizado
        frame.setResizable(false); //não redimencionavel;
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.GREEN);
        progressBar.setForeground(Color.BLUE);
        frame.getContentPane().add(progressBar, BorderLayout.SOUTH);
        frame.setVisible(true);

        return progressBar;
    }

    public void progressBar() {
        new Thread() {
            public void run() {

                getJProgressBar();

                for (int i = 0; i < 101; i++) {
                    try {
                        sleep(100);
                        progressBar.setValue(i);

                        //EXIBIR MSG EM UM LABEL
//                        if (progressBar.getValue() <= 25) {
//                            lbStatus.setText("Carregando...");
//                        } else if (progressBar.getValue() <= 50) {
//                            lbStatus.setText("Salvando no banco  de dados...");
//                        } else if (progressBar.getValue() <= 75) {
//                            lbStatus.setText("Finalizando...");
//                        } else {
//                            lbStatus.setText("Requisição salva com sucesso!");
//                        }
                    } catch (InterruptedException ex) {
                        System.out.println("Erro");
                    }
                }
                frame.dispose();
            }

        }.start();
    }

}
