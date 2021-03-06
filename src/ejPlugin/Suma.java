package ejPlugin;

import plugins.InvalidOperationException;
import plugins.Operacion;

public class Suma implements Operacion {
	private static final String nombre = "Suma";

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
		return operandos[0] + operandos[1];
	}

}
