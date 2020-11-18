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
	private JPanel panelHerramientas;
	
	private JLabel screenCalculadora;
	private JButton botonResolver;
	private JComboBox<JLabel> comboBoxOperaciones;
	
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
		ventana.add(panelOperandos, BorderLayout.CENTER);

		screenCalculadora = new JLabel();
		screenCalculadora.setText("0");
		ventana.getContentPane().add(screenCalculadora, BorderLayout.SOUTH);

		panelHerramientas = new JPanel(new GridLayout());
		ventana.add(panelHerramientas, BorderLayout.NORTH);
		
		comboBoxOperaciones = new JComboBox<>();
		comboBoxOperaciones.setToolTipText("Seleccionar operacion");
		panelHerramientas.add(comboBoxOperaciones);
		
		botonResolver = new JButton("Aplicar Operación");
		panelHerramientas.add(botonResolver);
		
		setArgumentosTextBox();

		ventana.pack();
		ventana.setVisible(true);
	}

	/**
	 * Configura las cajas de texto donde se escribe el valor de los operandos
	 */
	private void setArgumentosTextBox() {
		JFormattedTextField nuevoTF;

		for (int i = 0; i < cantidadArgumentos; i++) {
			nuevoTF = new JFormattedTextField(numFormat);
			nuevoTF.setText("" + i);
			textFieldOperandos.add(nuevoTF);
			panelOperandos.add(nuevoTF);
		}
	}

	/**
	 * Devuelve el argumento en un indice dado.
	 * 
	 * @param index indice del argumento.
	 * @return valor del argumento en la posicion index.
	 */
	public int getArgumentoAt(int index) {
		return Integer.parseInt(textFieldOperandos.get(index).getText());
	}

	/**
	 * Devuelve la cantidad de argumentos que muestra actualmente la GUI.
	 * 
	 * @return cantidad de argumentos mostrados.
	 */
	public int getCantidadArgumentos() {
		return textFieldOperandos.size();
	}
	
	public void setCantidadArgumentos() {
		JFormattedTextField nuevoTF;

		for (int i = 0; i < cantidadArgumentos; i++) {
			nuevoTF = new JFormattedTextField(numFormat);
			nuevoTF.setText("" + i);
			textFieldOperandos.add(nuevoTF);
			panelOperandos.add(nuevoTF);
		}
	}
}
