package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;

import logica.Logica;
import plugins.InvalidOperationException;
import plugins.Operacion;

public class Frame {
	private Logica logica;

	private JFrame ventana;

	private JPanel panelOperandos;
	private JPanel panelHerramientas;

	private JLabel screenCalculadora;
	private JButton botonResolver;
	private JButton botonActualizarPlugins;
	private JComboBox<Operacion> comboBoxOperaciones;

	private ActionListener comboBoxActionListener;

	private List<JFormattedTextField> textFieldOperandos;
	private DecimalFormat numFormat;

	public Frame() {
		numFormat = new DecimalFormat();
		textFieldOperandos = new ArrayList<>();
	}

	public void open(String titulo, Logica logica, IconProvider iconProvider) {
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

		botonActualizarPlugins = new JButton(iconProvider.getActualizarPluginsIcon());
		botonActualizarPlugins.setToolTipText("Buscar Plugins");
		panelHerramientas.add(botonActualizarPlugins);

		botonActualizarPlugins.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updatePlugins();
			}
		});

		botonResolver = new JButton(iconProvider.getResolverIcon());
		botonResolver.setToolTipText("Aplicar Operación");
		panelHerramientas.add(botonResolver);
		botonResolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent act) {
				resolverOperacion();
			}

		});

		comboBoxActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				try {
					logica.setOperacionActual((Operacion) comboBoxOperaciones.getSelectedItem());
					setCantidadArgumentos(logica.getOperacionActual().getCantidadOperandos());
				} catch (ClassCastException e) {
					e.printStackTrace();
				}
			}
		};
		comboBoxOperaciones.addActionListener(comboBoxActionListener);

		setCantidadArgumentos(logica.getOperacionActual().getCantidadOperandos());

		ventana.pack();

		updateOperaciones();
		
		ventana.setVisible(true);
	}

	/**
	 * Llama la operación de actualizar plugins de lógica y luego actualiza los de
	 * la GUI.
	 */
	private void updatePlugins() {
		logica.updatePlugins();
		updateOperaciones();
	}

	/**
	 * Obtiene el resultado de la operación descrita.
	 * 
	 */
	private void resolverOperacion() {
		double[] operandos = new double[textFieldOperandos.size()];
		String valorOperando;
		String resultado;

		for (int i = 0; i < textFieldOperandos.size(); i++) {
			valorOperando = textFieldOperandos.get(i).getText();
			valorOperando = valorOperando.replace(",", "");
			operandos[i] = Double.parseDouble(valorOperando);
		}

		try {
			resultado = "" + logica.operar(operandos);
		} catch (InvalidOperationException err) {
			resultado = "ERROR";
		}

		screenCalculadora.setText(resultado);
	}

	/**
	 * Actualiza las operaciones de la GUI con las almacenadas en logica.
	 */
	private void updateOperaciones() {
		Iterator<Operacion> it = logica.getOperaciones().iterator();

		limpiarComboBox();

		while (it.hasNext())
			comboBoxOperaciones.addItem(it.next());
	}

	private void limpiarComboBox() {
		comboBoxOperaciones.removeActionListener(comboBoxActionListener);
		comboBoxOperaciones.removeAllItems();
		comboBoxOperaciones.addActionListener(comboBoxActionListener);
	}

	/**
	 * Establece la cantidad de inputs que se muestran en pantalla.
	 * 
	 * @param num cantidad de inputs que se va a mostrar.
	 */
	private void setCantidadArgumentos(int num) {
		JFormattedTextField nuevoTF;
		System.out.println(num);
		
		panelOperandos.removeAll();
		textFieldOperandos = new ArrayList<>();
		
		for (int i = 0; i < num; i++) {
			nuevoTF = new JFormattedTextField(numFormat);
			nuevoTF.setText("" + 0);
			textFieldOperandos.add(nuevoTF);
			panelOperandos.add(nuevoTF);
		}
		
		panelOperandos.revalidate();
	}
}
