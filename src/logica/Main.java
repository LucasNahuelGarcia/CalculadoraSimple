package logica;

import java.util.ArrayList;
import java.util.List;

import gui.Frame;
import plugins.PluginLoader;

public class Main {
	public static void main(String[] args) {
		Frame frame = new Frame();
		PluginLoader operaciones = new PluginLoader();
		operaciones.buscarPlugins("./plugins");
		frame.open("Calculadora Simple");

		frame.setOperaciones(operaciones.pluginToString());
	}
}
