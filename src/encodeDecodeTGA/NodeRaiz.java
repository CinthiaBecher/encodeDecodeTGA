package encodeDecodeTGA;

public class NodeRaiz {
	
	private char caracter;
	private int frequencia;
	
	public NodeRaiz (char caracter, int frequencia) {
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