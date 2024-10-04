package encodeDecodeTGA;

public class Golomb{
	
	private int k = 8;
	private int stopBit = 1;
	private int prefix;
	private int suffix;
	
	private String characterEncoded;
	private String inputEncoded = "";
	
	private char characterDecoded;
	private String inputDecoded = "";
	
	public String encode(String input) {
		System.out.println("Codificando com Golomb: " + input);
		
		char[] caracteresInput = input.toCharArray();
		
		for(int i = 0; i < caracteresInput.length; i++) {
			characterEncoded = "";
			
			int valorASCII = (int) caracteresInput[i]; // Converte char para o valor em ASCII
			
			if(valorASCII < k)
				prefix = 0;
			else
				prefix = valorASCII / k;
			suffix = valorASCII % k;
			
			// Adiciona o prefixo
			for(int j = 0; j < prefix; j++)
				characterEncoded = characterEncoded + 0;
			
			// Adiciona o stopBit
			characterEncoded = characterEncoded + stopBit;
			
			// Adiciona o sufixo
			String sufixoBinary = Integer.toBinaryString(suffix);
			
			while (sufixoBinary.length() < 3) {
				sufixoBinary = 0 + sufixoBinary;
			}
			
			characterEncoded = characterEncoded + sufixoBinary;
			
			System.out.println("Caracter: " + caracteresInput[i]);
			System.out.println("Codigo: " + characterEncoded);
			
			inputEncoded = inputEncoded + characterEncoded;
		}
		
		System.out.println("Resultado da codificação Golomb de: ");
        return inputEncoded;
    }
	
	public String decode(String input) {
		System.out.println("Decodificando com Golomb: " + input);
		
		char caracteres[] = input.toCharArray();
		
		int quantZerosPrefixo = 0;
		
		for(int i = 0; i < caracteres.length; i++) {
			// conta a quantidade de zeros ate chegar no stopbit
			if(caracteres[i] != '1') {
				quantZerosPrefixo++;
			}
			else {
				// quando chegar, pega os proximos 3 caracteres e define eles como o sufixo
				String sufixoBinario = caracteres[i+1] + "" + caracteres[i+2] + "" + caracteres[i+3];
				// passa esse valor para inteiro
				suffix = Integer.parseInt(sufixoBinario, 2);
				
				int valorASCII = quantZerosPrefixo * k + suffix;
				characterDecoded = (char) valorASCII;
				inputDecoded = inputDecoded + characterDecoded;
				quantZerosPrefixo = 0;
				i += 3;
			}
			
		}
		
		System.out.println("Resultado da decodificação Golomb de: ");
		return inputDecoded;
	}

}
