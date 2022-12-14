import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    static final String arquivo = "memoria.bin";
    static Scanner teclado = new Scanner(System.in);
    static Bilhete bilhete;
    static Cliente clienteAtual;
    static Map<String, Cliente> clientes = new HashMap<>();
    /**
     * Método para ler os dados do cliente do arquivo.
     */
    public static void lerArquivo() {
        Map<String, Cliente> auxClientes = new HashMap<>();
        ObjectInputStream leitorObj;
        FileInputStream dados;
        try {
            dados = new FileInputStream(arquivo);
            leitorObj = new ObjectInputStream(dados);
            while (dados.available() != 0) {
                Cliente cliente = (Cliente) leitorObj.readObject();
                auxClientes.put(cliente.getCpf(), cliente);
            }
            clientes = (HashMap<String, Cliente>) auxClientes;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de leitura ainda não existe...");
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo...");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Erro na leitura do arquivo. Classe não encontrada...");
        }

    }
    /**
     * Método para salvar os dados do cliente no arquivo.
     */
    public static void gravarArquivo() {
        ObjectOutputStream gravadorObj;
        try {
            gravadorObj = new ObjectOutputStream(new FileOutputStream(arquivo));
            for (Cliente c : clientes.values()) {
                gravadorObj.writeObject(c);
            }
            gravadorObj.flush();
            gravadorObj.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao tentar encontrar arquivo de salvamento...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erro no salvamento do sistema...");
            e.printStackTrace();
        }
    }
    /**
     * Gerador de códigos dos bilhetes de acordo com quantidade de bilhetes ja criadas
     */
    public static String codBilhete() {

        return "AERO-" + (clientes.values().stream().mapToInt(c -> c.getCompras().size()).sum() + 1);
    }
    /**
     * Método para criar um data.
     */
    public static LocalDate data() {
        System.out.println("      AeroLine      ");
        System.out.println("====================");
        System.out.println("Insira a Data:");
        System.out.println("Digite Ano:");
        int ano = Integer.parseInt(teclado.nextLine());
        if (ano < 1904 || ano > 2100)
            throw new DateTimeException(null);
        System.out.println("Digite Mês:");
        int mes = Integer.parseInt(teclado.nextLine());
        System.out.println("Digite Dia:");
        int dia = Integer.parseInt(teclado.nextLine());
        return LocalDate.of(ano, mes, dia);
    }
    /**
     * Método para criar um Trecho.
     */
    public static Trecho trechoBilhete() {
        System.out.println("Digite o Código do Trecho:");
        String cod = teclado.nextLine();
        System.out.println("Digite a Origem do Trecho:");
        String origem = teclado.nextLine();
        System.out.println("Digite o Destino do Trecho:");
        String destino = teclado.nextLine();
        return new Trecho(cod, origem, destino);
    }
    /**
     * Método para criar um Voo.
     */
    public static Voo vooBilhete() {
        System.out.println("      AeroLine      ");
        System.out.println("====================");
        System.out.println("Definições do Voo:");
        Trecho trecho = trechoBilhete();
        LocalDate data = data();
        System.out.println("Digite o valor base:");
        double valor = Double.parseDouble(teclado.nextLine());

        return new Voo(trecho, data, valor);
    }
    /**
     * Menu de Voo para ajustes de Voo do bilhete.
     */
    public static void menuVoo() {
        String opcao;
        do {
            System.out.println("      AeroLine      ");
            System.out.println("====================");
            System.out.println("1 - Adicionar mais Voo");
            System.out.println("2 - Remover Voo");
            System.out.println("0 - Continuar");
            opcao = teclado.nextLine();
            switch (opcao) {
                case "1":
                    bilhete.adicionarVoo(vooBilhete());
                    break;
                case "2":
                    if (bilhete.getReservas().size() <= 1) {
                        System.out.println("Não é possível deixar bilhete sem Voo.");
                    } else {
                        System.out.println("      AeroLine      ");
                        System.out.println("====================");
                        System.out.println("Digite o código do Voo para remover:");
                        System.out.println(bilhete.removerVoo(teclado.nextLine()) ? "Removido" : "Não Encontrado");
                    }
                    break;
                case "0":
                    System.out.println("Salvando...");
                    break;
                default:
                    System.out.println("Valor Inválido");
                    break;
            }
        } while (!opcao.contains("0"));

    }

    /**
     * Menu para realizar compras de um bilhete para cliente.
     */
    public static void menuCompra() {
        String opcao;
        do {
            if (clienteAtual.verificadorPontos() >= 1) {
                System.out.println("      AeroLine      ");
                System.out.println("====================");
                System.out
                        .println("Cliente tem direto a " + clienteAtual.verificadorPontos() + " Bilhetes Fidelidade");
            }
            System.out.println("      AeroLine      ");
            System.out.println("====================");
            System.out.println("Escolha o bilhete:");
            System.out.println("1 - Bilhete Comum");
            System.out.println("2 - Bilhete Fidelidade");
            System.out.println("3 - Bilhete Promocional");
            System.out.println("0 - Sair");
            opcao = teclado.nextLine();

            try {
                switch (opcao) {
                    case "1":
                        bilhete = new BilheteComum(codBilhete(), LocalDate.now(), vooBilhete());
                        menuVoo();
                        System.out.println("      AeroLine      ");
                        System.out.println("====================");
                        System.out.println(bilhete.descricao());
                        clienteAtual.comprarBilhete(bilhete);
                        System.out.println("Digite qualquer coisa para continuar...");
                        teclado.nextLine();
                        break;
                    case "2":
                        if (clienteAtual.verificadorPontos() > 0) {
                            bilhete = new BilheteFidelidade(codBilhete(), LocalDate.now(), vooBilhete());
                            menuVoo();
                            System.out.println("      AeroLine      ");
                            System.out.println("====================");
                            System.out.println(bilhete.descricao());
                            clienteAtual.comprarBilhete(bilhete);
                        } else {
                            System.out.println("Cliente não tem direito a bilhete Fidelidade.");
                        }

                        System.out.println("Digite qualquer coisa para continuar...");
                        teclado.nextLine();
                        break;
                    case "3":
                        bilhete = new BilhetePromocional(codBilhete(), LocalDate.now(), vooBilhete());
                        menuVoo();
                        System.out.println("      AeroLine      ");
                        System.out.println("====================");
                        System.out.println(bilhete.descricao());
                        clienteAtual.comprarBilhete(bilhete);
                        System.out.println("Digite qualquer coisa para continuar...");
                        teclado.nextLine();
                        break;
                    case "0":
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Valor Inválido");
                        break;
                }
            } catch (DateTimeException e) {
                System.out.println("      AeroLine      ");
                System.out.println("====================");
                System.out.println("Data com valor Invalido");
                System.out.println("Tente novamente...");
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("      AeroLine      ");
                System.out.println("====================");
                System.out.println("Valor Invalido");
                System.out.println("Tente novamente...");
            } catch (NullPointerException e) {
                System.out.println("      AeroLine      ");
                System.out.println("====================");
                System.out.println("Valor não pode ser nulo ou em branco.");
                System.out.println("Tente novamente...");
            }

        } while (!opcao.contains("0"));
    }
    /**
     * Método para criar um Cliente.
     */
    public static Cliente Criarcliente() {
        System.out.println("      AeroLine      ");
        System.out.println("====================");
        System.out.println("Digite o seu nome:");
        String nome = teclado.nextLine();
        System.out.println("Digite o seu CPF:");
        String cpf = teclado.nextLine();
        return new Cliente(nome, cpf);
    }
    /**
     * Menu do Cliente para realizar suas operações.
     */
    public static void menuCliente() {
        String opcao;
        do {
            System.out.println("      AeroLine      ");
            System.out.println("====================");
            System.out.println("Escolha o opção:");
            System.out.println("1 - Comprar Bilhete");
            System.out.println("2 - Contratar Acelerador de pontos");
            System.out.println("3 - Bilhetes dos últimos 12 meses");
            System.out.println("4 - Relatório do Cliente");
            System.out.println("0 - Sair");
            opcao = teclado.nextLine();
            switch (opcao) {
                case "1":
                    menuCompra();
                    clientes.put(clienteAtual.getCpf(), clienteAtual);
                    break;
                case "2":
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println("Multiplicado atual : " + clienteAtual.getMultiplicador().getDescricao());
                    System.out.println("Escolha um novo plano:");
                    System.out.println("1 - Prata");
                    System.out.println("2 - Preto");
                    System.out.println("3 - Padrão");
                    System.out.println("0 - Sair");
                    String plano = teclado.nextLine();
                    if (plano.contains("1")) {
                        clienteAtual.addMultiplicador(AceleradorEnum.PRATA);
                    } else if (plano.contains("2")) {
                        clienteAtual.addMultiplicador(AceleradorEnum.PRETO);
                    } else if (plano.contains("3")) {
                        clienteAtual.addMultiplicador(AceleradorEnum.PADRAO);
                    } else {
                        System.out.println("Valor Invalido...");
                    }
                    break;
                case "3":
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println("Bilhetes dos últimos 12 meses:");
                    List<Bilhete> auxBilhetes = clienteAtual.getCompras().stream()
                            .filter(b -> b.getDate().until(LocalDate.now(), ChronoUnit.MONTHS) <= 12).toList();
                    if (auxBilhetes.isEmpty()) {
                        System.out.println("Cliente não possui bilhetes nos últimos 12 meses.");
                    } else {
                        auxBilhetes.forEach(b -> System.out.println(b.descricao()));
                        if (clienteAtual.verificadorPontos() >= 1) {
                            System.out
                                    .println("Cliente tem direto a " + clienteAtual.verificadorPontos()
                                            + " bilhetes Fidelidade");
                        }
                    }
                    System.out.println("Digite qualquer coisa para continuar...");
                    teclado.nextLine();

                    break;
                case "4":
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println("Relatório:");
                    System.out.println(clienteAtual.descricao());
                    System.out.println("Digite qualquer coisa para continuar...");
                    teclado.nextLine();
                    break;
                case "0":
                    clientes.put(clienteAtual.getCpf(), clienteAtual);
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Valor Inválido");
                    break;
            }
        } while (!opcao.contains("0"));
    }
    /**
     * Menu de Relatórios Gerais do sistema.
     */
    public static void menuRelatorio() {
        String opcao;
        List<Voo> voos;

        do {
            System.out.println("      AeroLine      ");
            System.out.println("====================");
            System.out.println("Escolha o opção:");
            System.out.println("1 - Cliente com mais pontos");
            System.out.println("2 - Voos por cidade a partir de um data, com mais de 100 reservas");
            System.out.println("3 - Valor total arrecadado com bilhetes");
            System.out.println("0 - Sair");
            opcao = teclado.nextLine();
            switch (opcao) {
                case "1":
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    // Filtra pegando todos clientes com pontuação > 0 , e pega máximo comparando
                    // por pontos.
                    // Classe cliente ja valida os pontos valido até 12 meses
                    Optional<Cliente> maiorCliente = clientes.values().stream().filter(c -> c.calcularPontos() > 0)
                            .max((o1, o2) -> o1.calcularPontos() > o2.calcularPontos() ? 1
                                    : o1.calcularPontos() < o2.calcularPontos() ? -1 : 0);
                    System.out.println(maiorCliente.isPresent() ? maiorCliente.get().descricao()
                            : "Não temos cliente com mais pontos acumulados nos últimos 12 meses");

                    System.out.println("Digite qualquer coisa para continuar...");
                    teclado.nextLine();
                    break;
                case "2":
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println("Digite a cidade:");
                    String cidade = teclado.nextLine();
                    LocalDate data;
                    try {
                        data = data();
                        // Stream de clientes que mapeia cada cliente para stream de compras que também
                        // mapeia um stream de voo, por fim transforma em uma lista voos completa.

                        voos = clientes.values().stream()
                                .flatMap(c -> c.getCompras().stream().flatMap(b -> b.getReservas().stream()))
                                .collect(Collectors.toList());

                        voos = voos.stream().filter(v -> v.toString().contains(cidade))
                                .filter(v -> v.toString().contains(data.toString())).toList();
                        if (voos.size() > 100) {
                            voos.stream().forEach(v -> System.out.println(v.toString()));
                        } else {
                            System.out.println("Não existe voos com estes critérios");
                        }

                        System.out.println("Digite qualquer coisa para continuar...");
                        teclado.nextLine();
                    } catch (DateTimeException e) {
                        System.out.println("      AeroLine      ");
                        System.out.println("====================");
                        System.out.println("Data com valor Invalido");
                        System.out.println("Tente novamente...");

                    }

                    break;
                case "3":
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    double totalArrecadado = clientes.values().stream().mapToDouble(c -> c.getTotalValorGasto()).sum();
                    System.out.println("Total arrecadado : " + totalArrecadado);
                    System.out.println("Digite qualquer coisa para continuar...");
                    teclado.nextLine();
                    break;
                case "0":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Valor Inválido");
                    break;
            }
        } while (!opcao.contains("0"));
    }
    /**
     * Menu Principal.
     */
    public static void main(String[] args) {
        String opcao;
        lerArquivo();
        do {
            System.out.println("      AeroLine      ");
            System.out.println("====================");
            System.out.println("Escolha o opção:");
            System.out.println("1 - Criar novo Cliente");
            System.out.println("2 - Selecionar Cliente");
            System.out.println("3 - Remover Cliente");
            System.out.println("4 - Relatórios Geral");
            System.out.println("0 - Sair");
            opcao = teclado.nextLine();
            switch (opcao) {
                case "1":
                    try {
                        clienteAtual = Criarcliente();
                        System.out.println("      AeroLine      ");
                        System.out.println("====================");
                        if (clientes.containsKey(clienteAtual.getCpf())) {
                            System.out.println("Este CPF já existe ,Cliente não foi criado...");
                        } else {
                            System.out.println("Cliente criado...");
                            clientes.put(clienteAtual.getCpf(), clienteAtual);
                        }
                        System.out.println("Digite qualquer coisa para continuar...");
                        teclado.nextLine();
                    } catch (NullPointerException e) {
                        System.out.println("      AeroLine      ");
                        System.out.println("====================");
                        System.out.println("Valor não pode ser nulo ou em branco.");
                        System.out.println("Tente novamente...");
                    }

                    break;
                case "2":
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println("Digite o CPF do cliente:");
                    String cpf = teclado.nextLine();
                    if (clientes.containsKey(cpf)) {
                        clienteAtual = clientes.get(cpf);
                        menuCliente();
                    } else {
                        System.out.println("O cliente não foi encontrado...");
                        System.out.println("Digite qualquer coisa para continuar...");
                        teclado.nextLine();
                    }
                    break;
                case "3":
                    System.out.println("      AeroLine      ");
                    System.out.println("====================");
                    System.out.println("Digite o CPF do cliente que deseja remover:");
                    String cpfRemove = teclado.nextLine();
                    if (clientes.containsKey(cpfRemove)) {
                        clientes.remove(cpfRemove);
                        System.out.println("Cliente foi removido...");
                    } else {
                        System.out.println("O cliente não foi encontrado...");
                    }
                    System.out.println("Digite qualquer coisa para continuar...");
                    teclado.nextLine();
                    break;
                case "4":
                    menuRelatorio();
                    break;
                case "0":
                    teclado.close();
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Valor Inválido");
                    break;
            }
        } while (!opcao.contains("0"));
        gravarArquivo();
    }
}
