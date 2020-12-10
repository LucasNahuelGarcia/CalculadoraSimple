package ejPlugin;

import plugins.InvalidOperationException;
import plugins.Operacion;

public class Division implements Operacion {
	private static final int cantOperandos = 2;
	private static final String nombre = "Division";

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int getCantidadOperandos() {
		return cantOperandos;
	}

	@Override
	public double operar(double[] operandos) throws InvalidOperationException {
		double res;
		if (operandos.length < cantOperandos)
			throw new InvalidOperationException("No hay suficientes operandos");
		if (operandos[1] != 0)
			throw new InvalidOperationException("Se intentó dividir por cero.");

		return operandos[0] / operandos[1];
	}

}
