package gui;

import java.awt.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import logica.Logica;

public class Frame {
	private Logica logica;

	private JFrame ventana;

	private JPanel panelOperandos;
	private JPanel panelHerramientas;

	private JLabel screenCalculadora;
	private JButton botonResolver;
	private JComboBox<Object> comboBoxOperaciones;

	private List<JFormattedTextField> textFieldOperandos;
	private NumberFormatter numFormat;

	private List<String> nombreOperaciones;

	public Frame() {
		numFormat = new NumberFormatter(NumberFormat.getIntegerInstance());
		numFormat.setMinimum(0l);
		numFormat.setAllowsInvalid(false);

		nombreOperaciones = new ArrayList<>();
		textFieldOperandos = new ArrayList<>();
	}

	public void open(String titulo, Logica logica) {
		this.logica = logica;

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

		setCantidadArgumentos(3);

		ventana.pack();
		ventana.setVisible(true);
	}

	public void setOperaciones(List<String> operaciones) {
		Iterator<String> it = operaciones.iterator();

		nombreOperaciones = operaciones;
		comboBoxOperaciones.removeAllItems();
		while (it.hasNext())
			comboBoxOperaciones.addItem(makeObj(it.next()));
	}

	private Object makeObj(final String item) {
		return new Object() {
			public String toString() {
				return item;
			}
		};
	}

	/**
	 * Devuelve el argumento en un indice dado.
	 * 
	 * @param index indice del argumento.
	 * @return valor del argumento en la posicion index.
	 */
	public int getArgumentoAt(int index) {
		int argumento;

		try {
			argumento = Integer.parseInt(textFieldOperandos.get(index).getText());
		} catch (NumberFormatException e) {
			argumento = 0;
		}

		return argumento;
	}

	/**
	 * Devuelve la cantidad de argumentos que muestra actualmente la GUI.
	 * 
	 * @return cantidad de argumentos mostrados.
	 */
	public int getCantidadArgumentos() {
		return textFieldOperandos.size();
	}

	/**
	 * Establece la cantidad de inputs que se muestran en pantalla.
	 * 
	 * @param num cantidad de inputs que se va a mostrar.
	 */
	public void setCantidadArgumentos(int num) {
		JFormattedTextField nuevoTF;

		for (int i = 0; i < num; i++) {
			nuevoTF = new JFormattedTextField(numFormat);
			nuevoTF.setText("" + 0);
			textFieldOperandos.add(nuevoTF);
			panelOperandos.add(nuevoTF);
		}
	}
}
