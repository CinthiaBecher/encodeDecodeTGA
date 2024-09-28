package encodeDecodeTGA;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	
    	
        Scanner scanner = new Scanner(System.in);

        // Escolha entre codificação ou decodificação
        System.out.println("Escolha a operação que deseja realizar:");
        System.out.println("1 - Codificação");
        System.out.println("2 - Decodificação");

        int operacao = scanner.nextInt();

        if (operacao != 1 && operacao != 2) {
            System.out.println("Opção inválida. Encerrando o programa.");
            return;
        }

        // Escolha do método de codificação/decodificação
        System.out.println("Escolha o método:");
        System.out.println("1 - Golomb");
        System.out.println("2 - Elias-Gamma");
        System.out.println("3 - Fibonacci/Zeckendorf");
        System.out.println("4 - Huffman");

        int metodo = scanner.nextInt();

        if (metodo < 1 || metodo > 4) {
            System.out.println("Método inválido. Encerrando o programa.");
            return;
        }

        // Solicita ao usuário a entrada para codificação/decodificação
        System.out.println("Digite o símbolo ou codeword que deseja processar:");
        String input = scanner.next();

        // Processa a entrada com base na operação e no método escolhido
        switch (operacao) {
            case 1: // Codificação
                switch (metodo) {
                    case 1:
                    	EncodeGolomb encodeGolomb = new EncodeGolomb();
                        System.out.println(encodeGolomb.encode(input));
                        break;
                    case 2:
                    	EncodeEliasGamma encodeEliasGamma = new EncodeEliasGamma();
                        System.out.println(encodeEliasGamma.encode(input));
                        break;
                    case 3:
                    	EncodeFibonacci encodeFibonacci = new EncodeFibonacci();
                        System.out.println(encodeFibonacci.encode(input));
                        break;
                    case 4:
                    	EncodeHuffman encodeHuffman = new EncodeHuffman();
                        System.out.println(encodeHuffman.encode(input));
                        break;
                }
                break;
            case 2: // Decodificação
                switch (metodo) {
                    case 1:
                        System.out.println("Decodificando com Golomb: " + input);
                        break;
                    case 2:
                        System.out.println("Decodificando com Elias-Gamma: " + input);
                        break;
                    case 3:
                        System.out.println("Decodificando com Fibonacci/Zeckendorf: " + input);
                        break;
                    case 4:
                        System.out.println("Decodificando com Huffman: " + input);
                        break;
                }
                break;
        }

        scanner.close();
    }
}
