package logica;

import java.util.*;
import plugins.*;

public class Logica {
	private final static String PLUGIN_PATH_DEFAULT = "plugins";
	private PluginLoader pluginLoader;
	private String pluginPath;
	private List<Operacion> operaciones;
	private Operacion operacionActual;

	public Logica() {
		pluginPath = PLUGIN_PATH_DEFAULT;
		pluginLoader = new PluginLoader();
		pluginLoader.buscarPlugins(pluginPath);

		if (!pluginLoader.getOperaciones().isEmpty())
			operacionActual = pluginLoader.getOperaciones().get(0);

		System.out.println("Se cargaron " + pluginLoader.getOperaciones().size() + " plugins.");
	}

	public void setPluginPath(String pluginPath) {
		this.pluginPath = pluginPath;
	}

	public void updatePlugins() {
		pluginLoader.buscarPlugins(pluginPath);
	}

}
