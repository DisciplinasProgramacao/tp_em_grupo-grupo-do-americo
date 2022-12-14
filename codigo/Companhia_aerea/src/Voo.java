
import java.io.Serializable;
import java.time.LocalDate;

public class Voo implements Serializable{

	private Trecho trecho;
	private LocalDate data;
	private double valorBase;

	public Voo(Trecho trecho, LocalDate data, double valorBase) {
		this.trecho = trecho;
		this.data = data;
		this.valorBase = valorBase;
	}

	/**
	 * Valor base do voo.
	 * @return o valor base do voo.
	 */
	public double valorBase() {
		return this.valorBase;
	}

	public String toString() {
		return this.trecho.toString() + " - " + this.data +  " Valor Base: " + this.valorBase();
	}
	public Trecho getTrecho() {
		return trecho;
	}

}
