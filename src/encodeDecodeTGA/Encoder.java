package encodeDecodeTGA;

public interface Encoder {
	
	public String encode(String input);
	
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
