package encodeDecodeTGA;

import java.util.*;

public class Huffman {
	
	public void encode(String input, boolean isDecodeActive) {
		System.out.println("Codificando com Huffman: " + input);
		
		char[] characters = input.toCharArray();
		
		// Conta a quantidade de vezes que cada caracter apareceu
		HashMap<Character, Integer> quantCharacters = countCharacters(input);
		
		BinaryTree tree = new BinaryTree();
		
		// Constroi a arvore binaria de huffman
		tree.buildTree(quantCharacters);
		tree.printTree(tree.getRoot(), 0);
		System.out.println();

		
		String result = "";
		
		// Codifica a entrada com base na arvore binaria criada
		for (int i = 0; i < characters.length; i++) {
			result = result + tree.readTreeEncode(tree.getRoot(), characters[i], "");
		}
		
		System.out.println("Resultado da codificação de Huffman: " + result);
		
		// Se o usuario quiser decodificar tambem, chama o metodo de decode
		if(isDecodeActive) {
			System.out.println("-----------------------------------------");
			System.out.println("Resultado da decodificação de Huffman: "); 
			System.out.println(this.decode(result, tree));
			System.out.println("-----------------------------------------");
		}
	}
	
	public String decode(String input, BinaryTree tree) {
		
		char[] binary = input.toCharArray();
		
		String result = "";
		
		Node currentNode = tree.getRoot();
		
		for(int i = 0; i < binary.length; i++) {
			currentNode = tree.readTreeDecode(currentNode, binary[i]);
			
			// Se chegar numa folha, quer dizer que achou o caracter, entao adiciona no resultado
			if (currentNode instanceof Leaf) {
				result = result + ((Leaf)currentNode).getCharacter();
				currentNode = tree.getRoot();
			}
			
		}
		
		return result;
	}
	
	// Metodo para contar a quantidade de vezes que cada caracter aparece
	public static HashMap<Character, Integer> countCharacters(String input){
		
		char[] characters = input.toCharArray();

		HashMap<Character, Integer> contCharacters = new HashMap<>();
		
		for (int i = 0; i < characters.length; i++) {
			// Se o caracter ja foi mapeado, acrescenta 1 na sua conta
			if (contCharacters.containsKey(characters[i])) {
				contCharacters.replace(characters[i], contCharacters.get(characters[i]) + 1);
			// Se nao foi mapeado ainda, mapeia com a quantidade 1
			} else {
				contCharacters.put(characters[i], 1);
			}
		}
		
		// Chama o metodo para ordenar o mapa de caracteres
		HashMap<Character, Integer> contCharactersSorted = sortMapByValue(contCharacters);
		
		return contCharactersSorted;
		
	}
	
	// Metodo para ordenar o HashMap por valores em ordem decrescente
	public static HashMap<Character, Integer> sortMapByValue(HashMap<Character, Integer> caracters) {
	
		
		// Cria uma lista com os elementos do HashMap para ordenar
		List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(caracters.entrySet());

		// Ordena a lista com base no valor em ordem crescente
		Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
			public int compare(Map.Entry<Character, Integer> character1, Map.Entry<Character, Integer> character2) {
				return (character1.getValue()).compareTo(character2.getValue());
			}
		});
		
		// Inverte a ordem da lista para ficar em ordem descrescente
		Collections.reverse(list);
		
		// Passa a lista ordenada de volta para um HashMap
		HashMap<Character, Integer> mapSorted = new LinkedHashMap<Character, Integer>();
		
		for (Map.Entry<Character, Integer> character : list) {
			mapSorted.put(character.getKey(), character.getValue());
		}
		
		return mapSorted;
	}

}
