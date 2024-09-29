package encodeDecodeTGA;

import java.util.ArrayList;

public class Fibonacci {

    private String inputCodificado = "";

    public String encode(String input) {
        System.out.println("Codificando com Fibonacci: " + input);

        // Transformar em ASCII
        char[] caracteresInput = input.toCharArray();

        for (int i = 0; i < caracteresInput.length; i++) {
            int valorASCII = (int) caracteresInput[i]; // Converte char para o valor em ASCII


            // Gera a sequência de Fibonacci até o valor ASCII
            int[] fibonacci = generateFibonacci(valorASCII);

            // Lógica de encontrar o subconjunto
            StringBuilder result = new StringBuilder();
            int sum = 0;

            // Iterando do final para o início do array
            for (int j = fibonacci.length - 1; j >= 0; j--) {
                if (sum + fibonacci[j] <= valorASCII) {
                    sum += fibonacci[j]; // Adiciona o número à soma
                    result.append("1"); // Marca o número como incluído
                } else {
                    result.append("0"); // Marca o número como não incluído
                }
            }

            // Inverte a string para que a ordem corresponda ao array original
            String caracterCodificado = result.reverse().toString();
            
            // Adicionando stop bit
            if(i < caracteresInput.length -1)
            	caracterCodificado = caracterCodificado + "1";

            System.out.println("Caracter: " + caracteresInput[i]);
         // Imprimindo o resultado
            for (int num : fibonacci) {
                System.out.print(num + " ");
            }
            System.out.println("Codigo: " + caracterCodificado);
            
            inputCodificado = inputCodificado + caracterCodificado;
        }

        return "Resultado da codificação Fibonacci : " + inputCodificado;
    }

    public String decode(String input) {
        String decodedMessage = "";
        
        // Dividir a entrada usando dois bits de 1 como delimitador
        String[] codewords = input.split("11"); // Usamos "11" como delimitador

        // Iterar sobre cada codeword para decodificá-los
        for (int i = 0; i < codewords.length; i++) {
        	String codeword = codewords[i];
        	if(i < codewords.length -1)
        		codeword = codeword + "1";
        	
        	System.out.println("codeword: " + codeword);
        	int[] fibonacci = generateFibonacci(codeword);
            
            int ascii = sumFibonacciValues(codeword, fibonacci);
            char character = (char) ascii;
        	System.out.println("ascii: " + ascii + "->"+character);
        	
            decodedMessage = decodedMessage + character;

        }

        return "Resultado da decodificação Fibonacci: " + decodedMessage;
    }

    public static int[] generateFibonacci(int n) {
        ArrayList<Integer> fibonacciList = new ArrayList<>();
        int a = 1, b = 1; // Começamos com 1 e 1

        while (b < n) {
            fibonacciList.add(b); // Adiciona apenas os números da sequência
            int next = a + b; // Próximo número da sequência
            a = b; // Atualiza a para o próximo número
            b = next; // Atualiza b para o próximo número
        }

        // Converte ArrayList para array
        return fibonacciList.stream().mapToInt(i -> i).toArray();
    }
    
    public static int[] generateFibonacci(String input) {
        int length = input.length();
        int[] fibonacci = new int[length];

        // Handle edge cases
        if (length >= 1) {
            fibonacci[0] = 1; // First Fibonacci number
        }
        if (length >= 2) {
            fibonacci[1] = 2; // Second Fibonacci number
        }

        // Generate Fibonacci sequence
        for (int i = 2; i < length; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        return fibonacci;
    }
    
    public static int sumFibonacciValues(String binaryString, int[] fibonacci) {
        int sum = 0;

        // Certifique-se de que a string binária tenha o mesmo comprimento que o array de Fibonacci
        if (binaryString.length() != fibonacci.length) {
            throw new IllegalArgumentException("A string binária deve ter o mesmo comprimento que o array de Fibonacci.");
        }

        // Itera pela string binária
        for (int i = 0; i < binaryString.length(); i++) {
            // Se a posição tiver '1', soma o valor correspondente da sequência de Fibonacci
            if (binaryString.charAt(i) == '1') {
                sum += fibonacci[i];
            }
        }

        return sum;
    }
}
