package ejPlugin;

import plugins.InvalidOperationException;
import plugins.Operacion;

public class Sumar1 implements Operacion {
	private static final String nombre = "Sumar1";

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int getCantidadOperandos() {
		return 1;
	}

	@Override
	public double operar(double[] operandos) throws InvalidOperationException {
		return operandos[0] + 1;
	}

}
