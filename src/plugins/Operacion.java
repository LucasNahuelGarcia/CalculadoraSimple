package plugins;

public interface Operacion {
	public int getCantidadOperandos();
	public double operar(double[] operandos) throws InvalidOperationException;
}
