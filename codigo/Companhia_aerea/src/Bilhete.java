import java.util.Date;
import java.util.List;

public abstract class Bilhete {

	protected String codBilhete;
	protected double preco;
	protected Date data;
	protected List<Voo> reservas;
	protected final double ACRESCIMO_VOO = 0.1;
	protected final double ACRESCIMO_DIVERSOS_VOO = 0.5;

	public Bilhete(String codBilhete, Date data, List<Voo> reservas) {
		this.codBilhete = codBilhete;
		this.data = data;
		this.reservas = reservas;
	}

	protected abstract double calcularPreco();

	public String descricao() {
		StringBuilder sb = new StringBuilder();
		sb.append("Bilhete: " + this.codBilhete + " Data: "+this.data + " Preço: " + this.preco);
		sb.append("\nVoos:");
		for (Voo voo : reservas) {
			sb.append("\n"+voo.toString());
		}
		return sb.toString();
	}

}
