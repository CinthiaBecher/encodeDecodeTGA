package encodeDecodeTGA;

import java.util.*;

public class Huffman {

	public void encode(String input, boolean isDecodeActive) {
		System.out.println("Codificando com Huffman: " + input);
		
		char[] characters = input.toCharArray();
		
		HashMap<Character, Integer> quantCharacters = countCharacters(input);
		
		BinaryTree tree = new BinaryTree();
		
		tree.buildTree(quantCharacters);
		tree.printTree(tree.getRoot());
		
		String result = "";
		
		for (int i = 0; i < characters.length; i++) {
			result = result + tree.readTreeEncode(tree.getRoot(), characters[i], "");
		}
		
		System.out.println("Resultado da codificação Huffman de: " + result);
		
		// Se o usuario quiser decodificar tambem, chama o metodo de decode
		if(isDecodeActive) {
			System.out.println("Resultado da codificação Huffman de: " + this.decode(result, tree));
			
		}
	}
	
	public String decode(String input, BinaryTree tree) {
		System.out.println("Decodificando com Huffman: " + input);
		
		char[] binary = input.toCharArray();
		
		String result = "";
		
		Node currentNode = tree.getRoot();
		
		for(int i = 0; i < binary.length; i++) {
			currentNode = tree.readTreeDecode(currentNode, binary[i]);
			
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
			if (contCharacters.containsKey(characters[i])) {
				contCharacters.replace(characters[i], contCharacters.get(characters[i]) + 1);
			} else {
				contCharacters.put(characters[i], 1);
			}
		}
		
		HashMap<Character, Integer> contCharactersSorted = sortMapByValue(contCharacters);
		
		System.out.printf("Caracteres: %s\n", contCharactersSorted.keySet());
		System.out.printf("Quantidade: %s\n", contCharactersSorted.values());
		
		return contCharactersSorted;
		
	}
	
	
	// Metodo para ordenar o HashMap por valores
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
