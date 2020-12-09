package ejPlugin;

import plugins.Operacion;

public class Multiplicacion implements Operacion {
	private static final String nombre = "Multiplicacion";

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int getCantidadOperandos() {
		return 2;
	}

	@Override
	public double operar(double[] operandos) {
		return operandos[0] * operandos[1];
	}

}
