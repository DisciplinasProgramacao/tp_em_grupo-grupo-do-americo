import java.util.ArrayList;
import java.util.List;

public class Cliente{

	private String nome;
	private List<Bilhete> compras;
	private int pontos;
	private final int PERIODO =12;
	private double totalValorGasto;
	private IaceleradorPts acelerador;

	
	public Cliente(String nome) {
		
		this.nome = nome;
		this.compras = new ArrayList<Bilhete>();
		this.pontos = 0;
		this.totalValorGasto=0;
	}

	//MÉTODOS
	public void comprarBilhete(Bilhete bilhete){
		
		
		this.compras.add(bilhete);
		
	}
	public void ordenarCompras(){

	}
	public int verificadorPontos(){
		return 0;
	}
	public int calcularPontos(){
		
		for (Bilhete bilhete : compras) {
			
			this.pontos += bilhete.calcularPontos();
		}
		
		return this.pontos;
	}
	public void addAcelerador(IaceleradorPts acelerador){

	}

	public void desativarAcelerador(){

	}
	//GETTERS/SETTERS

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Bilhete> getCompras() {
		return compras;
	}

	public void setCompras(List<Bilhete> compras) {
		this.compras = compras;
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

	public IaceleradorPts getAcelerador() {
		return acelerador;
	}

	public void setAcelerador(IaceleradorPts acelerador) {
		this.acelerador = acelerador;
	}

	public int getPERIODO() {
		return PERIODO;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", compras=" + compras + ", pontos=" + pontos + ", totalValorGasto="
				+ totalValorGasto + "]";
	}
	
	
}
