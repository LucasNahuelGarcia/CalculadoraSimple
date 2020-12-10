package logica;

import java.util.*;

import plugins.*;

/**
 * Modela la lógica de la calculadora.
 */
public class Logica {
	private final static String PLUGIN_PATH_DEFAULT = "plugins";
	private PluginLoader pluginLoader;
	private String pluginPath;
	private Operacion operacionActual;

	/**
	 * Crea una nueva instancia de logica.
	 */
	public Logica() {
		pluginPath = PLUGIN_PATH_DEFAULT;
		pluginLoader = new PluginLoader();

		updatePlugins();
	}

	/**
	 * A partir de un conjunto de operandos, calcula el resultado según la operación
	 * actual.
	 * 
	 * @param operandos Valores sobre los que se va a operar.
	 * @return El resultado de la operacion.
	 * @throws InvalidOperationException En caso de una operación con valores
	 *                                   inválidos.
	 */
	public double operar(double[] operandos) throws InvalidOperationException {
		return operacionActual.operar(operandos);
	}

	/**
	 * Devuelve la lista de operaciones cargadas.
	 * 
	 * @return Operaciones cargadas.
	 */
	public List<Operacion> getOperaciones() {
		return pluginLoader.getOperaciones();
	}

	/**
	 * Devuelve la operación actual.
	 * 
	 * @return Operacion Actual.
	 */
	public Operacion getOperacionActual() {
		return operacionActual;
	}

	/**
	 * Establece la operación actual.
	 * 
	 * @param op La nueva operación actual.
	 */
	public void setOperacionActual(Operacion op) {
		operacionActual = op;
	}

	/**
	 * Setea la ubicación relativa de la carpeta de plugins.
	 * 
	 * @param pluginPath Path relativo a la carpeta de plugins.
	 */
	public void setPluginPath(String pluginPath) {
		this.pluginPath = pluginPath;
	}

	/**
	 * Busca plugins dentro de la carpeta de plugins.
	 */
	public void updatePlugins() {
		pluginLoader.buscarPlugins(pluginPath);

		// Por defecto, la operación actual es la primera de la lista.
		if (!pluginLoader.getOperaciones().isEmpty())
			operacionActual = pluginLoader.getOperaciones().get(0);
	}
}
