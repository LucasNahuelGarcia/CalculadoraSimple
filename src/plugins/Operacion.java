package plugins;

/**
 * Modela una operación aritmética.
 */
public interface Operacion {
	/**
	 * Devuelve la cantidad de valores que requiere esta operación.
	 * 
	 * @return Cantidad de operandos requeridos.
	 */
	public int getCantidadOperandos();

	/**
	 * Calcula el resultado de la operación, dados los operandos.
	 * 
	 * @param operandos Valores sobre los que se aplica la operación.
	 * @return Resultado de la operacion.
	 * @throws InvalidOperationException En caso de ejecutar una operación no
	 *                                   valida.
	 */
	public double operar(double[] operandos) throws InvalidOperationException;
}
