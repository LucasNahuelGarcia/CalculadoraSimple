package gui;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * El cargador de iconos por defecto.
 */
public class DefaultIconoProvider implements IconProvider {
	private Icon actualizarPlugins;
	private Icon resolverOperacion;

	/**
	 * Crea una nueva instancia de DefualtIconoProvider.
	 */
	public DefaultIconoProvider() {
		loadActualizarPluginsIcon();
		loadResolverIcon();
	}

	@Override
	public Icon getActualizarPluginsIcon() {
		return actualizarPlugins;
	}

	@Override
	public Icon getResolverIcon() {
		return resolverOperacion;
	}

	/**
	 * Carga el icono del boton actualizar plugins.
	 */
	private void loadActualizarPluginsIcon() {
		URL path = getClass().getResource("/actualizar.png");
		actualizarPlugins = new ImageIcon(path);
	}

	/**
	 * Carga el icono del boton resolver operacion.
	 */
	private void loadResolverIcon() {
		URL path = getClass().getResource("/igual.png");
		resolverOperacion = new ImageIcon(path);
	}
}
