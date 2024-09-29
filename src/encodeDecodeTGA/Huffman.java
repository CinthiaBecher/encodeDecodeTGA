package encodeDecodeTGA;

import java.util.*;

public class Huffman {

	public void encode(String input, boolean decodificar) {
		System.out.println("Codificando com Huffman: " + input);
		
		char[] caracteres = input.toCharArray();
		
		HashMap<Character, Integer> quantCaracteres = countCaracteres(input);
		
		ArvoreBinaria arvore = new ArvoreBinaria();
		
		arvore.geraArvore(quantCaracteres);
		arvore.imprimirArvore(arvore.getRaiz());
		
		String inputCodificado = "";
		
		for (int i = 0; i < caracteres.length; i++) {
			inputCodificado = inputCodificado + arvore.percorreArvoreCodificacao(arvore.getRaiz(), caracteres[i], "");
		}
		
		System.out.println("Resultado da codificação Huffman de: " + inputCodificado);
		
		// Se o usuario quiser decodificar tambem, chama o metodo de decode
		if(decodificar) {
			System.out.println("Resultado da codificação Huffman de: " + this.decode(inputCodificado, arvore));
			
		}
	}
	
	public String decode(String input, ArvoreBinaria arvore) {
		System.out.println("Decodificando com Huffman: " + input);
		
		char[] binario = input.toCharArray();
		
		String inputDecodificado = "";
		
		Node nodeAtual = arvore.getRaiz();
		
		for(int i = 0; i < binario.length; i++) {
			nodeAtual = arvore.percorreArvoreDecode(nodeAtual, binario[i]);
			
			if (nodeAtual instanceof Folha) {
				inputDecodificado = inputDecodificado + ((Folha)nodeAtual).getCaracter();
				nodeAtual = arvore.getRaiz();
			}
			
		}
		
		return inputDecodificado;
	}
	
	// Metodo para contar a quantidade de vezes que cada caracter aparece
	public static HashMap<Character, Integer> countCaracteres(String input){
		
		char[] caracteres = input.toCharArray();

		HashMap<Character, Integer> contCaracteres = new HashMap<>();

		for (int i = 0; i < caracteres.length; i++) {
			if (contCaracteres.containsKey(caracteres[i])) {
				contCaracteres.replace(caracteres[i], contCaracteres.get(caracteres[i]) + 1);
			} else {
				contCaracteres.put(caracteres[i], 1);
			}
		}
		
		HashMap<Character, Integer> contCaracteresOrdenado = sortMapByValue(contCaracteres);
		
		System.out.printf("Caracteres: %s\n", contCaracteresOrdenado.keySet());
		System.out.printf("Quantidade: %s\n", contCaracteresOrdenado.values());
		
		return contCaracteresOrdenado;
		
	}
	
	
	// Metodo para ordenar o HashMap por valores
	public static HashMap<Character, Integer> sortMapByValue(HashMap<Character, Integer> caracteres) {
	
		
		// Cria uma lista com os elementos do HashMap para ordenar
		List<Map.Entry<Character, Integer>> lista = new ArrayList<Map.Entry<Character, Integer>>(caracteres.entrySet());

		// Ordena a lista com base no valor em ordem crescente
		Collections.sort(lista, new Comparator<Map.Entry<Character, Integer>>() {
			public int compare(Map.Entry<Character, Integer> caracter1, Map.Entry<Character, Integer> caracter2) {
				return (caracter1.getValue()).compareTo(caracter2.getValue());
			}
		});
		
		// Inverte a ordem da lista para ficar em ordem descrescente
		Collections.reverse(lista);
		
		// Passa a lista ordenada de volta para um HashMap
		HashMap<Character, Integer> mapOrdenado = new LinkedHashMap<Character, Integer>();
		
		for (Map.Entry<Character, Integer> caracter : lista) {
			mapOrdenado.put(caracter.getKey(), caracter.getValue());
		}
		
		return mapOrdenado;
	}

}
