package gui;

import java.awt.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class Frame {
	private JFrame ventana;
	private JPanel panelOperandos;
	private JLabel screenCalculadora;
	private List<JFormattedTextField> textFieldOperandos;
	private NumberFormatter numFormat;
	private int cantidadArgumentos;

	public Frame() {
		numFormat = new NumberFormatter(NumberFormat.getIntegerInstance());
		numFormat.setMinimum(0l);
		numFormat.setAllowsInvalid(false);
		
		textFieldOperandos = new ArrayList<>();
		cantidadArgumentos = 6;
	}

	public void open(String titulo) {
		ventana = new JFrame(titulo);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());
		
		panelOperandos = new JPanel();
		panelOperandos.setLayout(new GridLayout());
		ventana.add(panelOperandos, BorderLayout.NORTH);
		
		screenCalculadora = new JLabel();
		screenCalculadora.setText("0");
		ventana.getContentPane().add(screenCalculadora);

		setArgumentosTextBox();

		ventana.pack();
		ventana.setVisible(true);
	}

	/**
	 * Configura las cajas de texto donde se escribe el valor de los operandos
	 */
	private void setArgumentosTextBox() {
    	JFormattedTextField nuevoTF;

    	for(int i = 0; i < cantidadArgumentos; i++) {
    		nuevoTF = new JFormattedTextField(numFormat);
    		nuevoTF.setText(""+i);
    		textFieldOperandos.add(nuevoTF);
    		panelOperandos.add(nuevoTF);
    	}
    }
	
	public int getArgumentoAt(int index) {
		return Integer.parseInt(textFieldOperandos.get(index).getText());
	}
		
	private boolean esInt(String texto) {
		boolean esInt = true;
		
		try {
			Integer.parseInt(texto);
		} catch (NumberFormatException e) {
			esInt = false;
		}
		
		return esInt;
	}

}
