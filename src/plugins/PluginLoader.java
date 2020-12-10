package plugins;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.*;
import java.util.jar.*;

/**
 * Modela una estructura que carga los plugins de un directorio.
 */
public class PluginLoader {
	List<URL> pathDeClases;
	List<String> nombreClases;
	List<Operacion> operaciones;

	/**
	 * Crea una nueva instancia de PluginLoader
	 */
	public PluginLoader() {
		operaciones = new ArrayList<>();
	}

	/**
	 * Si la carpeta plugins existe, busca plugins dentro de esta. Si no existe la
	 * crea.
	 * 
	 * @param path ubicación donde se deben buscar los plugins.
	 */
	public void buscarPlugins(String path) {
		operaciones = new ArrayList<>();
		File pluginFolder = new File(path);

		if (pluginFolder.exists())
			cargarPluginsEnCarpeta(pluginFolder);
		else
			pluginFolder.mkdir();
	}

	/**
	 * Retorna una lista con todas las operaciones cargadas en la estructura.
	 * 
	 * @return Lista con las operaciones cargadas.
	 */
	public List<Operacion> getOperaciones() {
		return operaciones;
	}

	/**
	 * Carga los plugins dentro de una carpeta.
	 * 
	 * @param pluginFolder Carpeta en donde buscar plugins.
	 */
	private void cargarPluginsEnCarpeta(File pluginFolder) {
		pathDeClases = new ArrayList<>();
		nombreClases = new ArrayList<>();
		JarFile jarFile;

		File[] plugins = pluginFolder.listFiles((dir, name) -> name.endsWith(".jar"));

		if (plugins != null)
			for (File file : plugins) {
				try {
					jarFile = new JarFile(file);
					cargarPluginsDeJar(jarFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}

	/**
	 * Busca plugins validos dentro de un archivo jar y los carga en la estructura.
	 * 
	 * @param file Archivo donde buscar plugins.
	 */
	private void cargarPluginsDeJar(JarFile jarFile) {
		Iterator<JarEntry> jarEntryIterator;
		JarEntry jarEntry;
		Class posiblePlugin;
		String nombreClase;

		try {
			jarEntryIterator = jarFile.stream().iterator();

			while (jarEntryIterator.hasNext()) {
				jarEntry = jarEntryIterator.next();
				if (jarEntry.getName().endsWith(".class")) {
					nombreClase = getClassNameDeJarEntry(jarEntry);

					posiblePlugin = Class.forName(nombreClase);

					if (esPluginOperacion(posiblePlugin))
						operaciones.add((Operacion) posiblePlugin.getDeclaredConstructor().newInstance());
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A partir de un JarEntry que contenga una clase, obtiene el nombre de la
	 * clase.
	 * 
	 * @param jarEntry Entrada de archivo .jar
	 * @return Nombre de la clase asociada al archivo.
	 */
	private String getClassNameDeJarEntry(JarEntry jarEntry) {
		String classname = jarEntry.getName();
		classname = classname.replace('/', '.');
		classname = classname.substring(0, jarEntry.getName().length() - 6);

		return classname;
	}

	/**
	 * Dada una clase, verifica si es una operación plugin valida.
	 * 
	 * @param clase La clase que se quiere verificar.
	 * @return True si es plugin valido, false si no.
	 */
	private boolean esPluginOperacion(Class clase) {
		boolean esOperacion = false;
		Class[] interfaces = clase.getInterfaces();

		for (int i = 0; i < interfaces.length && !esOperacion; i++)
			esOperacion = interfaces[i].equals(Operacion.class);

		return esOperacion;
	}
}
