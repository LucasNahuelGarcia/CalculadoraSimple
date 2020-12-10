package ejPlugin;

import plugins.InvalidOperationException;
import plugins.Operacion;

public class Division implements Operacion {
	private static final String nombre = "Division";

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int getCantidadOperandos() {
		return 2;
	}

	@Override
	public double operar(double[] operandos) throws InvalidOperationException {
		double res;
		if (operandos[1] != 0)
			res = operandos[0] / operandos[1];
		else
			throw new InvalidOperationException("Se intentó dividir por cero.");

		return operandos[0] / operandos[1];
	}

}
