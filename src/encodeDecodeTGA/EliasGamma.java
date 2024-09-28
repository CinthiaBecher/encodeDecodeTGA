package encodeDecodeTGA;

public class EliasGamma{
	
	public String encode(String input) {
		int num;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return "Entrada inválida. Por favor, insira um número inteiro.";
        }

        // Verificar se o número é maior que zero
        if (num < 1) {
            return "Elias-Gamma só pode codificar números inteiros positivos maiores que zero.";
        }
        
        //Codificando
		System.out.println("Codificando com Elias-Gamma: " + input);
		
	
		 // Encontrar o maior N tal que 2^N <= num
        int N = (int) (Math.log(num) / Math.log(2));  // log2(num)
		
        // Codificar N usando Unary coding (N zeros seguidos de 1)
        String unary = "0".repeat(N) + "1";
		
        
        // Codificar o valor restante (num - 2^N) usando N bits binários
        int remainder = num - (1 << N);
        String binaryRemainder = String.format("%" + N + "s", Integer.toBinaryString(remainder)).replace(' ', '0');
        
        // Combinar a codificação unária e o binário restante
        String eliasGammaCode = unary + binaryRemainder;
      
        return "Resultado da codificação Elias-Gamma: " + eliasGammaCode;
    
	}
	
	
	public String decode(String input) {
		// Encontrar o N -> contar zeros no início
        int N = 0;
        while (N < input.length() && input.charAt(N) == '0') {
            N++;
        }

        // Verificar se o comprimento é suficiente para decodificar
        if (N >= input.length()) {
            return "Código inválido. Formato incorreto.";
        }

        // Ler os próximos N+1 bits como um número binário
        int index = N + 1;  // Pular os N zeros e o primeiro 1 (representação unária)
        String binaryPart = input.substring(index, index + N);  // Os próximos N bits

        // Se a parte binária for vazia, o número é exatamente 2^N
        int remainder = binaryPart.isEmpty() ? 0 : Integer.parseInt(binaryPart, 2);

        // Recalcular o valor original
        int originalNumber = (1 << N) + remainder;  // 2^N + restante binário

        return "Resultado da decodificação Elias-Gamma: " + originalNumber;
			
	}
	

}
