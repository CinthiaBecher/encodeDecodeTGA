package encodeDecodeTGA;

public class Node {

	int frequencia;

	Node esquerda;
	Node direita;
	
	public Node() {
		this.esquerda = null;
		this.direita = null;
	}

	public Node(int frequencia) {
		this.frequencia = frequencia;
		this.esquerda = null;
		this.direita = null;
	}

	public Node getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(Node esquerda) {
		this.esquerda = esquerda;
	}

	public Node getDireita() {
		return direita;
	}

	public void setDireita(Node direita) {
		this.direita = direita;
	}

	public int getFrequencia() {
		return frequencia;
	}

	@Override
	public String toString() {
		return "Node [frequencia=" + frequencia + "]";
	}
	
	

}
