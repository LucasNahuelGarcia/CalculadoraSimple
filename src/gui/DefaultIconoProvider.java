package gui;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class DefaultIconoProvider implements IconProvider {
	private Icon actualizarPlugins;
	private Icon resolverOperacion;

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

	private void loadActualizarPluginsIcon() {
		URL path = getClass().getResource("/actualizar.png");
		actualizarPlugins = new ImageIcon(path);
	}

	private void loadResolverIcon() {
		URL path = getClass().getResource("/igual.png");
		resolverOperacion = new ImageIcon(path);
	}
}
