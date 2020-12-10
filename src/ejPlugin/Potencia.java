package ejPlugin;

import plugins.InvalidOperationException;
import plugins.Operacion;

public class Potencia implements Operacion {
	private static final int cantOperandos = 2;
	private static final String nombre = "Potencia";

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
		if (operandos.length < cantOperandos)
			throw new InvalidOperationException("No hay suficientes operandos");

		return Math.pow(operandos[0], operandos[1]);
	}

}