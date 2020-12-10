package gui;

import logica.Logica;

public class Main {
	public static void main(String[] args) {
		IconProvider iconProvider = new DefaultIconoProvider();
		Frame frame = new Frame();
		frame.open("Calculadora Simple", new Logica(), iconProvider);
	}
}
