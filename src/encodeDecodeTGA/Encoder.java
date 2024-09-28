package encodeDecodeTGA;

public interface Encoder {

	public String encode(String input);
	
	// Converte o caracter no valor inteiro em ASCII
	public default int caracterToASCIIValue(char caracter) {
		int valorASCII = (int) caracter; // Converte char para o valor em ASCII
		return valorASCII;
	}
	
	// Converte o caracter no numero binario em ASCII
	public default String caracterToBinaryASCII(char caracter) {

		int valorASCII = (int) caracter; // Converte char para o valor em ASCII
		String binario = Integer.toBinaryString(valorASCII); // Converte o valor ASCII para binario

		// Caso o binario tenha menos de 8 digitos, acrescenta os zeros iniciais
		while (binario.length() < 8) {
			binario = 0 + binario;
		}

		return binario;

	}

}
