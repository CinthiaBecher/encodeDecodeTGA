package encodeDecodeTGA;

import java.util.*;

public class BinaryTree {

	private Node root;

	public BinaryTree() {
		root = null;
	}

	public Node getRoot() {
		return this.root;
	}

	// Cria uma pilha com as folhas em ordem crescente
	public Stack<Character> createLeafs(HashMap<Character, Integer> quantCaracteres) {

		Stack<Character> stack = new Stack<Character>();

		// Para cada caracter no mapa, adiciona ele no topo da pilha
		for (Character key : quantCaracteres.keySet()) {
			stack.push(key);
		}

		return stack;
	}

	// Cria a arvore binaria
	public void buildTree(HashMap<Character, Integer> quantCaracteres) {

		Stack<Character> stack = this.createLeafs(quantCaracteres);

		// Arvore vazia
		while (!stack.empty()) {
			// Quando a arvore estiver vazia
			if (root == null) {
				// Caso so haja 1 caracter no input, adiciono ele a esquerda da raiz
				if (stack.size() < 2) {
					char c = stack.pop();

					Leaf f = new Leaf(c, quantCaracteres.get(c));

					root = new Node(f.getFrequency());
					root.setLeft(f);

				}
				/*
				 * Senao, pega os dois primeiros caracteres da pilha, que sao os com menos
				 * repeticoes, e adiciona eles como folhas na arvore
				 */
				else {
					char c1 = stack.pop();
					char c2 = stack.pop();

					Leaf f1 = new Leaf(c1, quantCaracteres.get(c1));
					Leaf f2 = new Leaf(c2, quantCaracteres.get(c2));

					// A raiz tera a soma da frequencia das folhas inferiores
					root = new Node(f1.getFrequency() + f2.getFrequency());
					root.setRight(f1);
					root.setLeft(f2);
				}
			} else {
				char c = stack.pop();

				Leaf f = new Leaf(c, quantCaracteres.get(c));

				Node previousRoot = root;

				// A nova raiz tera a soma da frquencia dos nodes inferiores
				root = new Node(previousRoot.getFrequency() + f.getFrequency());
				// Adiciona a raiz anterior a direita
				root.setRight(previousRoot);
				// Adiciona a nova folha a esquerda
				root.setLeft(f);
			}
		}
	}

	// Imprime a arvore
	public void printTree(Node node) {
		if (node != null) {
			if (node instanceof Leaf) {
				System.out.println((Leaf) node);
				printTree(node.getLeft());
				printTree(node.getRight());
			} else {
				System.out.println(node);
				printTree(node.getLeft());
				printTree(node.getRight());
			}
		}

	}

	public String readTreeEncode(Node node, char character, String result) {
		if (node != null) {
			// Caso encontre uma folha, verifica se ela possui o caracter procurado
			if (node instanceof Leaf) {
				if (((Leaf) node).getCharacter() == character)
					return result;

				// Caso nao for uma folha
			} else {
				// Se o caracter estiver na folha a esquerda, adiciona um 0 no resultado
				if (((Leaf) node.getLeft()).getCharacter() == character)
					return result + "0";
				// Se o caracter estiver na folha a direita, adiciona um 1 no resultado
				else if (node.getRight() instanceof Leaf && ((Leaf) node.getRight()).getCharacter() == character) {
					return result + "1";
					// Se o caracter nao estiver no nivel ainda, adiciona um 1 ao resultado e chama
					// o metodo novamente
				} else {
					result = result + "1";
					return readTreeEncode(node.getRight(), character, result);
				}
			}
		}
		return result;
	}

	public Node readTreeDecode(Node node, char binary) {
		// Se o numero binario for 1, le o node da direita
		if (binary == '1') {
			return node.getRight();
		}
		// Se o numero binario for 0, quer dizer que o proximo é o caracter, entao le o
		// node da esquerda
		else {
			return node.getLeft();
		}
	}

}
