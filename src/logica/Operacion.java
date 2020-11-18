package logica;

public interface Operacion {
	public String getName();
	public int getCantidadOperandos();
	public double operar(double[] operandos);
}
