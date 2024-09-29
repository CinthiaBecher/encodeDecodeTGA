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

		for (Character key : quantCaracteres.keySet()) {
			stack.push(key);
		}

		return stack;
	}

	public void buildTree(HashMap<Character, Integer> quantCaracteres) {

		Stack<Character> stack = this.createLeafs(quantCaracteres);

		// Arvore vazia
		while (!stack.empty()) {
			if (root == null) {
				// Caso que so tenho 1 caracter
				if(stack.size() < 2) {
					char c = stack.pop();
					
					Leaf f = new Leaf(c, quantCaracteres.get(c));
					
					root = new Node(f.getFrequency());
					root.setLeft(f);
					
				}
				else {
					char c1 = stack.pop();
					char c2 = stack.pop();
	
					Leaf f1 = new Leaf(c1, quantCaracteres.get(c1));
					Leaf f2 = new Leaf(c2, quantCaracteres.get(c2));
	
					root = new Node(f1.getFrequency() + f2.getFrequency());
					root.setRight(f1);
					root.setLeft(f2);
				}
			} else {
				char c = stack.pop();

				Leaf f = new Leaf(c, quantCaracteres.get(c));

				Node previousRoot = root;

				root = new Node(previousRoot.getFrequency() + f.getFrequency());
				root.setRight(previousRoot);
				root.setLeft(f);
			}
		}
	}

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
			if (node instanceof Leaf) {
				if (((Leaf) node).getCharacter() == character)
					return result;

			} else {
				if (((Leaf) node.getLeft()).getCharacter() == character)
					return result + "0";
				else if(node.getRight() instanceof Leaf && ((Leaf) node.getRight()).getCharacter() == character) {
					return result + "1";
				}
				else {
					result = result + "1";
					return readTreeEncode(node.getRight(), character, result);
				}
			}
		}
		return result;
	}
	
	public Node readTreeDecode(Node node, char binary) {
		if(binary == '1') {
			return node.getRight();
		}
		else {
			return node.getLeft();
		}
	}

}
