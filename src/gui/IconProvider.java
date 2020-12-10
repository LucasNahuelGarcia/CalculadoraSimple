package gui;

import javax.swing.Icon;

/**
 * Modela una clase que carga y provee los iconos que requiere la GUI.
 */
public interface IconProvider {

	/**
	 * Devuelve el icono del boton que actualiza los plugins.
	 * 
	 * @return Icono del boton que actualiza los plugins.
	 */
	public Icon getActualizarPluginsIcon();

	/**
	 * Devuelve el icono del boton que resuelve la operacion.
	 * 
	 * @return Icono del boton que resuelve la operacion.
	 */
	public Icon getResolverIcon();

}