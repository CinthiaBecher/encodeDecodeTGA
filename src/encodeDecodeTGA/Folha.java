package encodeDecodeTGA;

public class Folha extends Node{
	
	private char caracter;
	private int frequencia;
	
	public Folha (char caracter, int frequencia) {
		this.caracter = caracter;
		this.frequencia = frequencia;
	}
	
	public char getCaracter() {
		return caracter;
	}
	
	public int getFrequencia() {
		return frequencia;
	}
}