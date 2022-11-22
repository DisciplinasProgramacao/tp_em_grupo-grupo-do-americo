import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Bilhete {

	protected String codBilhete;
	protected double preco;
	protected LocalDate data;
	protected List<Voo> reservas = new ArrayList<>();
	protected final double ACRESCIMO_VOO = 0.1;
	protected final double ACRESCIMO_DIVERSOS_VOO = 0.5;

	public Bilhete(String codBilhete, LocalDate data, Voo voo) {
		this.codBilhete = codBilhete;
		this.data = data;
		this.reservas.add(voo);
	}

	public abstract double calcularPreco();
	public abstract int calcularPontos();

	public void adicionarVoo(Voo voo) {
		this.reservas.add(voo);
	}

	public void removerVoo(String codigo) {
		for (Voo voo : reservas) {
			if (codigo.equals(voo.getTrecho().getCodigo()))
				reservas.remove(voo);
		}
	}

	public String descricao() {
		StringBuilder sb = new StringBuilder();
		sb.append("Bilhete: " + this.codBilhete + " Data: " + this.data + " Preço: " + this.preco);
		sb.append("\nVoos:");
		for (Voo voo : reservas) {
			sb.append("\n" + voo.toString());
		}
		return sb.toString();
	}

}
