package logica;

import gui.Frame;

public class Main {
	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.open("Calculadora Simple", new Logica());
	}
}
