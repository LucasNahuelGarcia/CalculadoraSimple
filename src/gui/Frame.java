package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;

import logica.Logica;
import plugins.Operacion;

public class Frame {
	private Logica logica;

	private JFrame ventana;

	private JPanel panelOperandos;
	private JPanel panelHerramientas;

	private JLabel screenCalculadora;
	private JButton botonResolver;
	private JComboBox<Operacion> comboBoxOperaciones;

	private List<JFormattedTextField> textFieldOperandos;
	private DecimalFormat numFormat;

	public Frame() {
		numFormat = new DecimalFormat();

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
		botonResolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double[] operandos = new double[textFieldOperandos.size()];
				String valorOperando;
				double resultado;

				for (int i = 0; i < textFieldOperandos.size(); i++) {
					valorOperando = textFieldOperandos.get(i).getText();
					valorOperando = valorOperando.replace(",", "");
					operandos[i] = Double.parseDouble(valorOperando);
				}

				resultado = logica.operar(operandos);

				screenCalculadora.setText("" + resultado);
			}
		});

		comboBoxOperaciones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				try {
					logica.setOperacionActual((Operacion) comboBoxOperaciones.getSelectedItem());
				} catch (ClassCastException e) {
					e.printStackTrace();
				}
			}
		});

		setCantidadArgumentos(logica.getOperacionActual().getCantidadOperandos());

		ventana.pack();
		ventana.setVisible(true);

		updateOperaciones();
	}

	public void updateOperaciones() {
		Iterator<Operacion> it = logica.getOperaciones().iterator();

		comboBoxOperaciones.removeAllItems();

		while (it.hasNext())
			comboBoxOperaciones.addItem(it.next());
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
