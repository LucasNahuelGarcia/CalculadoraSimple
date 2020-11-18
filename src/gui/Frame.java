package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame {
    private JFrame ventana;
    private JLabel screenCalculadora;

    public void open(String titulo){
        ventana = new JFrame(titulo);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setScreenCalculadora();
        ventana.pack();
        ventana.setVisible(true);
    }

    private void setScreenCalculadora() {
        screenCalculadora = new JLabel();
        screenCalculadora.setText("Screen calculadora");
        ventana.getContentPane().add(screenCalculadora);
    }
}
