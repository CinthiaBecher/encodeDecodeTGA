package encodeDecodeTGA;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true; // Controla se o programa continua executando

        while (continuar) {
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
            scanner.nextLine(); 
            System.out.println("Digite o símbolo ou codeword que deseja processar:");
            String input = scanner.nextLine();

            Golomb golomb = new Golomb();
            EliasGamma eliasGama = new EliasGamma();
            Fibonacci fibonacci = new Fibonacci();
            Huffman huffman = new Huffman();

            // Processa a entrada com base na operação e no método escolhido
            switch (operacao) {
                case 1: // Codificação
                    switch (metodo) {
                        case 1:
                            System.out.println(golomb.encode(input));
                            break;
                        case 2:
                            System.out.println(eliasGama.encode(input));
                            break;
                        case 3:
                            System.out.println(fibonacci.encode(input));
                            break;
                        case 4:
                            System.out.println(huffman.encode(input));
                            break;
                    }
                    break;
                case 2: // Decodificação
                    switch (metodo) {
                        case 1:
                            System.out.println(golomb.decode(input));
                            break;
                        case 2:
                            System.out.println(eliasGama.decode(input));
                            break;
                        case 3:
                            System.out.println(fibonacci.decode(input));
                            break;
                        case 4:
                            System.out.println("Decodificando com Huffman: " + input);
                            break;
                    }
                    break;
            }

            // Pergunta se o usuário deseja realizar outra ação
            System.out.println("Deseja realizar outra operação? (S/N)");
            String resposta = scanner.nextLine().trim().toUpperCase();
            if (!resposta.equals("S")) {
                continuar = false;
                System.out.println("Encerrando o programa.");
            }
        }

        scanner.close();
    }
}
