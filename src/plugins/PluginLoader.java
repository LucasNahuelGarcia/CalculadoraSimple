package plugins;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.*;
import java.util.jar.*;

public class PluginLoader {
	List<URL> pathDeClases;
	List<String> clases;
	List<Operacion> operaciones;

	public PluginLoader() {
		operaciones = new ArrayList<>();
	}

	public void buscarPlugins(String path) {
		File pluginFolder = new File(path);

		if (pluginFolder.exists()) {
			cargarPlugins(pluginFolder);
		} else
			pluginFolder.mkdir();
	}

	public List<Operacion> getOperaciones() {
		return operaciones;
	}

	public List<String> pluginToString() {
		List<String> nombreOperacion = new ArrayList<>();
		Iterator<Operacion> it = operaciones.iterator();
		while (it.hasNext()) {
			nombreOperacion.add(it.next().getName());
			System.out.println("nombre: " + it.next());
		}
		return nombreOperacion;
	}

	private void cargarPlugins(File pluginFolder) {
		pathDeClases = new ArrayList<>();
		clases = new ArrayList<>();
		File[] plugins = pluginFolder.listFiles((dir, name) -> name.endsWith(".jar"));

		if (plugins != null) {

			cargarClases(plugins);

			URLClassLoader urlClassLoader = new URLClassLoader(pathDeClases.toArray(new URL[pathDeClases.size()]));
			for(String className : clases) {
				try {
					Class cls = urlClassLoader.loadClass(className.replaceAll("/", ".").replace(".class", ""));

					Class[] interfaces = cls.getInterfaces();
					for (Class intface : interfaces) {
						System.out.println(intface.getName());
						if (intface.equals(Operacion.class)) {
							Operacion plugin = (Operacion) cls.getDeclaredConstructor().newInstance();
							System.out.println("added plugin: " + cls.getName());
							operaciones.add(plugin);
						}
					}
				} catch (NoClassDefFoundError | ClassNotFoundException | InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException | NoSuchMethodException
						| SecurityException | ClassCastException e) {
					System.out.println(className + " no era una clase valida");
				}
			}

		}
	}

	private void cargarClases(File[] archivos) {
		for (File file : archivos)
			agregarClasesDeJar(file);
	}

	private void agregarClasesDeJar(File file) {
		JarFile jarFile;
		try {
			jarFile = new JarFile(file);

			jarFile.stream().forEach(jarEntry -> {
				if (jarEntry.getName().endsWith(".class")) {
					clases.add(jarEntry.getName());
					System.out.println(jarEntry.getName());
				}
			});

			jarFile.close();

			pathDeClases.add(file.toURI().toURL());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
