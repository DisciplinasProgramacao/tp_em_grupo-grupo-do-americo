import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    static final String arquivo = "memoria.txt";
    static Scanner teclado = new Scanner(System.in);
    static Bilhete bilhete;
    static Cliente clienteAtual;
    static Map<String, Cliente> clientes = new HashMap<>();

    public static void lerArquivo(){
        //TODO Implementar.
    }
    public static void gravarArquivo(){
        //TODO Implementar.
    }

    public static void menuBilhete() {
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

    public static LocalDate dataBilhete() {
        System.out.println("      AeroLine      ");
        System.out.println("====================");
        System.out.println("Insira a Data:");
        System.out.println("Digite Ano:");
        int ano = teclado.nextInt();
        System.out.println("Digite Mês:");
        int mes = teclado.nextInt();
        System.out.println("Digite Dia:");
        int dia = teclado.nextInt();
        return LocalDate.of(ano, mes, dia);
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

    /**
     * Menu para realizar compras de um bilhete para cliente.
     */
    public static void menuCompra() {
        int opcao;
        do {
            menuBilhete();
            opcao = teclado.nextInt();
            // TODO Verificar se vai usar esta logica para bilhete promocional.
            if (clienteAtual.verificadorPontos() >= 1) {
                System.out.println("      AeroLine      ");
                System.out.println("====================");
                System.out
                        .println("Cliente tem direto a " + clienteAtual.verificadorPontos() + " bilhetes Promocional");
                System.out.println("Digite qualquer coisa para continuar...");
                teclado.next();
            }
            switch (opcao) {
                case 1:
                    bilhete = new BilheteComum(codBilhete(), LocalDate.now(), vooBilhete());
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println(bilhete.descricao());
                    clienteAtual.comprarBilhete(bilhete);
                    System.out.println("Digite qualquer coisa para continuar...");
                    teclado.next();
                    break;
                case 2:
                    bilhete = new BilheteFidelidade(codBilhete(), LocalDate.now(), vooBilhete());
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println(bilhete.descricao());
                    clienteAtual.comprarBilhete(bilhete);
                    System.out.println("Digite qualquer coisa para continuar...");
                    teclado.next();
                    break;
                case 3:
                    bilhete = new BilhetePromocional(codBilhete(), LocalDate.now(), vooBilhete());
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println(bilhete.descricao());
                    clienteAtual.comprarBilhete(bilhete);
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
    }

    public static void menuInicial() {
        System.out.println("      AeroLine      ");
        System.out.println("====================");
        System.out.println("Escolha o opção:");
        System.out.println("1 - Criar novo Cliente");
        System.out.println("2 - Selecionar Cliente");
        System.out.println("3 - Relatórios Geral");
        System.out.println("0 - Sair");
    }

    public static Cliente Criarcliente() {
        System.out.println("      AeroLine      ");
        System.out.println("====================");
        System.out.println("Digite o seu nome:");
        String nome = teclado.next();
        System.out.println("Digite o seu CPF:");
        String cpf = teclado.next();
        return new Cliente(nome, cpf);
    }

    public static void menuCliente() {
        System.out.println("      AeroLine      ");
        System.out.println("====================");
        System.out.println("Escolha o opção:");
        System.out.println("1 - Comprar Bilhete");
        System.out.println("2 - Contratar Acelerador de pontos");
        System.out.println("3 - Bilhetes dos últimos 12 meses");
        System.out.println("4 - Relatório do Cliente");
        System.out.println("0 - Sair");
        int opcao;
        do {
            opcao = teclado.nextInt();
            switch (opcao) {
                case 1:
                    menuCompra();
                    break;
                case 2:
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    // TODO Verificar aleterações do Enum na classe Cliente.
                    System.out.println("Multiplicado atual : " + clienteAtual.getMultiplicador().getDescricao());
                    System.out.println("Escolha um novo plano:");
                    System.out.println("1 - Prata");
                    System.out.println("2 - Preto");
                    System.out.println("0 - Sair");
                    int plano = teclado.nextInt();
                    if (plano == 1) {
                        clienteAtual.addMultiplicador(AceleradorEnum.PRATA);
                    } else if (plano == 2) {
                        clienteAtual.addMultiplicador(AceleradorEnum.PRETO);
                    } else {
                        System.out.println("Saindo...");
                    }
                    break;
                case 3:
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println("Bilhetes dos últimos 12 meses:");
                    List<Bilhete> auxBilhetes = clienteAtual.getCompras().stream()
                            .filter(b -> b.getDate().until(LocalDate.now(), ChronoUnit.MONTHS) <= 12).toList();
                    auxBilhetes.forEach(System.out::print);
                    if (clienteAtual.verificadorPontos() >= 1) {
                        System.out
                                .println("Cliente tem direto a " + clienteAtual.verificadorPontos()
                                        + " bilhetes Promocional");
                        System.out.println("Digite qualquer coisa para continuar...");
                        teclado.next();
                    }
                    break;
                case 4:
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println("Relatório:");
                    System.out.println(clienteAtual.descricao());
                case 0:
                    clientes.put(clienteAtual.getCpf(), clienteAtual);
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Valor Inválido");
                    break;
            }
        } while (opcao != 0);
    }

    public static void main(String[] args) {
        int opcao;
        do {
            menuInicial();
            opcao = teclado.nextInt();
            switch (opcao) {
                case 1:
                    clienteAtual = Criarcliente();
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println(clienteAtual.toString());
                    clientes.put(clienteAtual.getCpf(), clienteAtual);
                    System.out.println("Digite qualquer coisa para continuar...");
                    teclado.next();
                    break;
                case 2:
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println("Digite o CPF do cliente:");
                    String cpf = teclado.next();
                    if (clientes.containsKey(cpf)) {
                        clienteAtual = clientes.get(cpf);
                        menuCliente();
                    } else {
                        System.out.println("O cliente não foi encontrado...");
                        System.out.println("Digite qualquer coisa para continuar...");
                        teclado.next();
                    }
                    break;
                case 3:
                    // TODO Implementar relatórios gerais.
                    // Quais são os voos para uma cidade, em uma data, com mais de 100 reservas?
                    // Qual o total valor arrecadado com bilhetes em todo o período de funcionamento
                    // da empresa, podendo ainda filtrar o valor por um mês escolhido?
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Valor Inválido");
                    break;
            }
        } while (opcao != 0);

    }
}
