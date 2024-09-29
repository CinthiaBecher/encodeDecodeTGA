package encodeDecodeTGA;

public class Golomb{
	
	private int k = 8;
	private int stopBit = 1;
	private int prefixo;
	private int sufixo;
	
	private String caracterCodificado;
	private String inputCodificado = "";
	
	private char caracterDecodificado;
	private String inputDecodificado = "";
	
	public String encode(String input) {
		System.out.println("Codificando com Golomb: " + input);
		
		char[] caracteresInput = input.toCharArray();
		
		for(int i = 0; i < caracteresInput.length; i++) {
			caracterCodificado = "";
			
			int valorASCII = (int) caracteresInput[i]; // Converte char para o valor em ASCII
			
			if(valorASCII < k)
				prefixo = 0;
			else
				prefixo = valorASCII / k;
			sufixo = valorASCII % k;
			
			// Adiciona o prefixo
			for(int j = 0; j < prefixo; j++)
				caracterCodificado = caracterCodificado + 0;
			
			// Adiciona o stopBit
			caracterCodificado = caracterCodificado + stopBit;
			
			// Adiciona o sufixo
			String sufixoBinary = Integer.toBinaryString(sufixo);
			
			while (sufixoBinary.length() < 3) {
				sufixoBinary = 0 + sufixoBinary;
			}
			
			caracterCodificado = caracterCodificado + sufixoBinary;
			
			System.out.println("Caracter: " + caracteresInput[i]);
			System.out.println("Codigo: " + caracterCodificado);
			
			inputCodificado = inputCodificado + caracterCodificado;
		}
		
		System.out.println("Resultado da codificação Golomb de: ");
        return inputCodificado;
    }
	
	public String decode(String input) {
		System.out.println("Decodificando com Golomb: " + input);
		
		char caracteres[] = input.toCharArray();
		
		int quantZerosPrefixo = 0;
		
		for(int i = 0; i < caracteres.length; i++) {
			if(caracteres[i] != '1') {
				quantZerosPrefixo++;
			}
			else {
				String sufixoBinario = caracteres[i+1] + "" + caracteres[i+2] + "" + caracteres[i+3];
				sufixo = Integer.parseInt(sufixoBinario, 2);
				
				int valorASCII = quantZerosPrefixo * k + sufixo;
				caracterDecodificado = (char) valorASCII;
				inputDecodificado = inputDecodificado + caracterDecodificado;
				quantZerosPrefixo = 0;
				i += 3;
			}
			
		}
		
		System.out.println("Resultado da decodificação Golomb de: ");
		return inputDecodificado;
	}

}
