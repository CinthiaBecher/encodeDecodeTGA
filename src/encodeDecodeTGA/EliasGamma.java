package encodeDecodeTGA;

public class EliasGamma{
	
	private String inputEncoded = "";
	
	public String encode(String input) {
   		System.out.println("Codificando com Elias-Gamma: " + input);
   		
   		//Transforma em array
   		char[] caracteresInput = input.toCharArray();
   		
   		for(int i = 0; i < caracteresInput.length; i++) {
			
			int valorASCII = (int) caracteresInput[i]; // Converte char para o valor em ASCII
		
	
			 // Encontrar o maior N tal que 2^N <= num
	        int N = (int) (Math.log(valorASCII) / Math.log(2));  // log2(num)
		
	        // Codificar N usando Unary coding (N zeros seguidos de 1)
	        String unary = "0".repeat(N) + "1";
			
	        
	        // Codificar o valor restante (num - 2^N) usando N bits binários
	        int remainder = valorASCII - (1 << N);
	        String binaryRemainder = String.format("%" + N + "s", Integer.toBinaryString(remainder)).replace(' ', '0');
	        
	        // Combinar a codificação unária e o binário restante
	        String characterEncoded = unary + binaryRemainder;
	        
	        System.out.println("Caracter: " + caracteresInput[i]);
			System.out.println("Codigo: " + characterEncoded);
			
			inputEncoded = inputEncoded + characterEncoded;
   		}
      
        return "Resultado da codificação Elias-Gamma: " + inputEncoded;
    
	}
	
	
    public String decode(String input) {
        String decodedMessage = "";
        int currentIndex = 0;

        // Percorrer o código Elias-Gamma completo
        while (currentIndex < input.length()) {
            // Encontrar o N -> contar zeros no início
            int N = 0;
            while (currentIndex < input.length() && input.charAt(currentIndex) == '0') {
                N++;
                currentIndex++;
            }

            // Verificar se o código é inválido (não há um '1' após os zeros)
            if (currentIndex >= input.length() || input.charAt(currentIndex) != '1') {
                return "Código inválido. Formato incorreto.";
            }

            // Pular o '1' que marca o fim da parte unária
            currentIndex++;

            // Ler os próximos N bits como um número binário
            String binaryPart = input.substring(currentIndex, Math.min(currentIndex + N, input.length()));

            // Se a parte binária for vazia, o número é exatamente 2^N
            int remainder = binaryPart.isEmpty() ? 0 : Integer.parseInt(binaryPart, 2);

            // Recalcular o valor original em ASCII
            int asciiOriginalNumber = (1 << N) + remainder;  // 2^N + restante binário

            // Converter o valor ASCII de volta para um caractere
            decodedMessage = decodedMessage + ((char) asciiOriginalNumber);

            // Avançar o índice para os próximos N bits
            currentIndex += N;
        }

        return "Resultado da decodificação Elias-Gamma: " + decodedMessage;
    }
	

}
