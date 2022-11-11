import java.util.List;

public class Cliente{

	private String nome;
	private List<Bilhete> compras;
	private int pontos;
	private final int PERIODO =12;
	private double totalValorGasto;
	private IaceleradorPts acelerador;

	

	//MÉTODOS
	public void comprarBilhete(Bilhete bilhete){

	}
	public void ordenarCompras(){

	}
	public int verificadorPontos(){
		return 0;
	}
	public int calcularPontos(){
		return 0;
	}
	public void addAcelerador(IaceleradorPts acelerador){

	}

	public void desativarAcelerador(){

	}
}
