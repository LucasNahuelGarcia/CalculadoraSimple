package logica;

import java.util.*;

import plugins.*;

public class Logica {
	private final static String PLUGIN_PATH_DEFAULT = "plugins";
	private PluginLoader pluginLoader;
	private String pluginPath;
	private Operacion operacionActual;

	public Logica() {
		pluginPath = PLUGIN_PATH_DEFAULT;
		pluginLoader = new PluginLoader();

		updatePlugins();
	}

	public double operar(double[] operandos) throws InvalidOperationException {
		return operacionActual.operar(operandos);
	}

	public List<Operacion> getOperaciones() {
		return pluginLoader.getOperaciones();
	}

	public Operacion getOperacionActual() {
		return operacionActual;
	}

	public void setOperacionActual(Operacion op) {
		operacionActual = op;
	}

	public void setPluginPath(String pluginPath) {
		this.pluginPath = pluginPath;
	}

	public void updatePlugins() {
		pluginLoader.buscarPlugins(pluginPath);

		if (!pluginLoader.getOperaciones().isEmpty())
			operacionActual = pluginLoader.getOperaciones().get(0);

		System.out.println("Se cargaron " + pluginLoader.getOperaciones().size() + " plugins.");
	}
}
