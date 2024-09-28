package encodeDecodeTGA;

public class EncodeGolomb implements Encoder{
	
	private int k = 8;
	private int stopBit = 1;
	private int prefixo;
	private int sufixo;
	private String caracterCodificado;
	
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
				
		}
		
        return "Resultado da codificação Golomb de: ";
    }

}
