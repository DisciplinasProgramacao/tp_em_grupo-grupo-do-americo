import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);

    public static void menuInicial() {
        System.out.println("      AeroLine      ");
        System.out.println("====================");
        System.out.println("Escolha o bilhete:");
        System.out.println("1 - Bilhete Comum");
        System.out.println("2 - Bilhete Fidelidade");
        System.out.println("3 - Bilhete Promocional");
        System.out.println("0 - Sair");
    }
    public static String codBilhete() {
        System.out.println("      AeroLine      ");
        System.out.println("====================");
        System.out.println("Digite Código do bilhete:");

        return teclado.next();
    }

    public static LocalDateTime dataBilhete() {
        System.out.println("      AeroLine      ");
        System.out.println("====================");
        System.out.println("Insira a Data:");
        System.out.println("Digite Ano:");
        int ano = teclado.nextInt();
        System.out.println("Digite Mês:");
        int mes = teclado.nextInt();
        System.out.println("Digite Dia:");
        int dia = teclado.nextInt();
        System.out.println("Digite Hora:");
        int hora = teclado.nextInt();
        System.out.println("Digite Minuto:");
        int minuto = teclado.nextInt();
        return LocalDateTime.of(ano, mes, dia, hora, minuto);
    }

    public static Trecho trechoBilhete() {
        System.out.println("Digite o Código do Trecho:");
        String cod = teclado.next();
        System.out.println("Digite a Origem do Trecho:");
        String origem = teclado.next();
        System.out.println("Digite o Destino do Trecho:");
        String destino = teclado.next();
        return new Trecho(cod, origem, destino);
    }

    public static Voo vooBilhete() {
        System.out.println("      AeroLine      ");
        System.out.println("====================");
        System.out.println("Definições do Voo:");
        Trecho trecho = trechoBilhete();
        LocalDate data = LocalDate.now();
        System.out.println("Digite o valor base:");
        double valor = teclado.nextDouble();

        return new Voo(trecho, data, valor);
    }

    public static void main(String[] args) {
        Bilhete bilhete;
        int opcao;
        do {
            menuInicial();
            opcao = teclado.nextInt();
            switch (opcao) {
                case 1:
                    bilhete = new BilheteComum(codBilhete(), LocalDate.now(), vooBilhete());
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println(bilhete.descricao());
                    System.out.println("Digite qualquer coisa para continuar...");
                    teclado.next();
                    break;
                case 2:
                    bilhete = new BilheteFidelidade(codBilhete(), LocalDate.now(), vooBilhete());
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println(bilhete.descricao());
                    System.out.println("Digite qualquer coisa para continuar...");
                    teclado.next();
                    break;
                case 3:
                    bilhete = new BilhetePromocional(codBilhete(), LocalDate.now(), vooBilhete());
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println(bilhete.descricao());
                    System.out.println("Digite qualquer coisa para continuar...");
                    teclado.next();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Valor Inválido");
                    break;
            }

        } while (opcao != 0);
        System.out.println("teste");
    }
}
