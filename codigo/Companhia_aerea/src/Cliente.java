import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente {

	private String nome;
	private String cpf;
	private List<Bilhete> compras;
	private int pontos;
	private final int PERIODO = 12;
	private double totalValorGasto;
	private AceleradorEnum multiplicador;
	private final int REF_PONTOS = 10500;

	public Cliente(String nome, String cpf) {

		this.nome = nome;
		this.cpf = cpf;
		this.compras = new ArrayList<Bilhete>();
		this.pontos = 0;
		this.totalValorGasto = 0;
		this.multiplicador = AceleradorEnum.PADRAO;
	}

	// MÉTODOS
	public void comprarBilhete(Bilhete bilhete) {
		this.compras.add(bilhete);
		totalValorGasto += bilhete.calcularPreco();
		calcularPontos();
	}

	public void ordenarCompras() {
		Collections.sort(this.compras);
	}

	public int verificadorPontos() {
		return (int) this.pontos / this.REF_PONTOS;
	}

	public int calcularPontos() {
		for (Bilhete bilhete : compras) {
			long mes = bilhete.getDate().until(LocalDate.now(), ChronoUnit.MONTHS);
			if (mes <= this.PERIODO) {
				this.pontos += (int) bilhete.calcularPontos() * this.multiplicador.getMultiplicador();
			}
		}

		return this.pontos;
	}

	public void addMultiplicador(AceleradorEnum multi) {
		this.multiplicador = multi;

	}

	public String descricao() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nome: " + this.nome + " CPF: " + this.cpf + " Pontos: " + this.pontos + " TotalValorGasto: "
				+ this.totalValorGasto + " Acelerador: " + this.multiplicador.getDescricao());
		sb.append("\nBilhetes: \n");
		this.compras.forEach(b-> sb.append("\n" + b.descricao()));

		return sb.toString();
	}

	// GETTERS/SETTERS

	public String getNome() {
		return nome;
	}

	public List<Bilhete> getCompras() {
		return compras;
	}

	public int getPontos() {
		return pontos;
	}

	public double getTotalValorGasto() {
		return totalValorGasto;
	}

	public int getPERIODO() {
		return PERIODO;
	}

	public AceleradorEnum getMultiplicador() {
		return multiplicador;
	}

	public String getCpf() {
		return cpf;
	}

	public int getREF_PONTOS() {
		return REF_PONTOS;
	}

}
