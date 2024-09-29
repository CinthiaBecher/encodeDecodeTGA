package encodeDecodeTGA;

import java.util.*;

public class ArvoreBinaria {

	private Node raiz;

	public ArvoreBinaria() {
		raiz = null;
	}

	public Node getRaiz() {
		return this.raiz;
	}

	// Cria uma pilha com as folhas em ordem crescente
	public Stack<Character> criaFolhas(HashMap<Character, Integer> quantCaracteres) {

		Stack<Character> pilha = new Stack<Character>();

		for (Character key : quantCaracteres.keySet()) {
			pilha.push(key);
		}

		return pilha;
	}

	public void geraArvore(HashMap<Character, Integer> quantCaracteres) {

		Stack<Character> pilha = this.criaFolhas(quantCaracteres);

		// Arvore vazia
		while (!pilha.empty()) {
			if (raiz == null) {

				char c1 = pilha.pop();
				char c2 = pilha.pop();

				Folha f1 = new Folha(c1, quantCaracteres.get(c1));
				Folha f2 = new Folha(c2, quantCaracteres.get(c2));

				raiz = new Node(f1.getFrequencia() + f2.getFrequencia());
				raiz.setDireita(f1);
				raiz.setEsquerda(f2);
			} else {
				char c = pilha.pop();

				Folha f = new Folha(c, quantCaracteres.get(c));

				Node raizAnterior = raiz;

				raiz = new Node(raizAnterior.getFrequencia() + f.getFrequencia());
				raiz.setDireita(raizAnterior);
				raiz.setEsquerda(f);
			}
		}
	}

	public void imprimirArvore(Node no) {
		if (no != null) {
			if (no instanceof Folha) {
				System.out.println((Folha) no);
				imprimirArvore(no.getEsquerda());
				imprimirArvore(no.getDireita());
			} else {
				System.out.println(no);
				imprimirArvore(no.getEsquerda());
				imprimirArvore(no.getDireita());
			}
		}

	}

	public String percorreArvore(Node no, char caracter, String resultado) {
		if (no != null) {
			if (no instanceof Folha) {
				if (((Folha) no).getCaracter() == caracter)
					return resultado + "0";

			} else {
				if (((Folha) no.getEsquerda()).getCaracter() == caracter)
					return resultado + "0";
				else {
					resultado = resultado + "1";
					return percorreArvore(no.getDireita(), caracter, resultado);
				}
			}
		}
		return resultado;
	}

}
