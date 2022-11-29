import java.time.LocalDateTime;
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
	private double multiplicador;
	private final int REF_PONTOS = 10500;

	public Cliente(String nome, String cpf) {

		this.nome = nome;
		this.cpf = cpf;
		this.compras = new ArrayList<Bilhete>();
		this.pontos = 0;
		this.totalValorGasto = 0;
		this.multiplicador = 1;
	}

	// MÉTODOS
	public void comprarBilhete(Bilhete bilhete) {

		this.compras.add(bilhete);

	}

	public void ordenarCompras() {

		Collections.sort(this.compras);
	}

	public int verificadorPontos() {
		int auxPontos = 0;

		for (Bilhete bilhete : compras) {

			long mes = bilhete.getDate().until(LocalDateTime.now(), ChronoUnit.MONTHS);
			if (mes > this.PERIODO) {
				auxPontos = bilhete.calcularPontos();

			}
		}

		int qtdBilhetes = auxPontos / this.REF_PONTOS;

		return qtdBilhetes;
	}

	public int calcularPontos() {

		for (Bilhete bilhete : compras) {

			this.pontos += bilhete.calcularPontos() * this.multiplicador;
		}

		return this.pontos;
	}

	public void addMultiplicador(double multi) {
		this.setMultiplicador(multi);

	}

	// GETTERS/SETTERS

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public double getTotalValorGasto() {
		return totalValorGasto;
	}

	public void setTotalValorGasto(double totalValorGasto) {
		this.totalValorGasto = totalValorGasto;
	}

	public int getPERIODO() {
		return PERIODO;
	}

	public String descricao() {
		return "Cliente [nome=" + nome + ", compras=" + compras + ", pontos=" + pontos + ", totalValorGasto="
				+ totalValorGasto + "]";
	}

	public double getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(double multiplicador) {
		this.multiplicador = multiplicador;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
