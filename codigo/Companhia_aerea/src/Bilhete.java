import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public abstract class Bilhete implements Comparable<Bilhete> {

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

	/**
	 * metodo utilizado para calcular o preço do bilhete
	 * @return o preço total do bilhete.
	 */
	public abstract double calcularPreco();

	/**
	 * metodo utilizado para calcular
	 * @return os pontos calculados da classe bilhete.
	 */
	public abstract int calcularPontos();

	/**
	 * Metodo utiliza-do para adicionar um novo voo na lista de reservas.
	 * parametro voo que será o voo guardado.
	 * @param voo
	 */
	public void adicionarVoo(Voo voo) {
		this.reservas.add(voo);
		
	}

	/**
	 * Método para remover voo da lista de reservas.
	 * parametro codigo usado para remover voo especifico.
	 * @param codigo 
	 */
	public void removerVoo(String codigo) {
		for (Voo voo : reservas) {
			if (codigo.equals(voo.getTrecho().getCodigo()))
				reservas.remove(voo);
		}
	}

	/**
	 * Metodo para saber as informações do bilhete
	 * @return a descrição de um bilhete. código, data e preço do voo.
	 */
	public String descricao() {
		StringBuilder sb = new StringBuilder();
		sb.append("Bilhete: " + this.codBilhete + " Data: " + this.data + " Preço: " + this.preco);
		sb.append("\nVoos:");
		for (Voo voo : reservas) {
			sb.append("\n" + voo.toString());
		}
		return sb.toString();
	}

	@Override
	public int compareTo(Bilhete bilhete) {
		
		return this.data.compareTo(bilhete.data);
	}
	
	/**
	 * 
	 * @return a data do bilhete.
	 */
	public LocalDate getDate() {
		
		return this.data;
	}
 
	/**
	 * 
	 * @return a lista de reservas.
	 */
	public List<Voo> getReservas() {
		return reservas;
	}

	
}
