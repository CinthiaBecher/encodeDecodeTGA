package encodeDecodeTGA;

import java.util.*;

public class ArvoreBinaria {
	
	private Node raiz;
	
	public ArvoreBinaria() {
		raiz = null;
	}
	
	public void inserir(char caracter, int frequencia) {
		
	}
	
	// Cria uma pilha com as folhas em ordem crescente
	public Stack<Character> criaFolhas(HashMap<Character, Integer> quantCaracteres) {
		
		Stack<Character> pilha = new Stack<Character>();
		
		for (Character key : quantCaracteres.keySet()) {
		    pilha.push(key);
		}
		
		return pilha;
	}
	
	public void geraArvore(HashMap<Character, Integer> quantCaracteres, Stack<Character> pilha) {
		// Arvore vazia
		if (raiz == null) {
			
			char c1 = pilha.pop();
			char c2 = pilha.pop();
			
			Folha f1 = new Folha(c1, quantCaracteres.get(c1));
			Folha f2 = new Folha(c2, quantCaracteres.get(c2));
			
			raiz = new Node(f1.getFrequencia()+f2.getFrequencia());
			raiz.setDireita(f1);
			raiz.setEsquerda(f2);
		}
	}
}
