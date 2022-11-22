import java.time.LocalDate;

public class BilheteComum extends Bilhete {

	private int pontos;
	private final int REF_VALOR = 500;

	public BilheteComum(String codBilhete, LocalDate data, Voo voo) {
		super(codBilhete, data, voo);
		calcularPontos();
	}
	
	@Override
	public double calcularPreco() {
		if(super.reservas.size() == 1){
			double acrescimo = reservas.get(0).valorBase() * super.ACRESCIMO_VOO;
			super.preco = reservas.get(0).valorBase() + acrescimo;
		}else if(super.reservas.size() > 1){
			double maiorPreco = 0;
			double soma = 0;
			for (Voo voo : super.reservas) {
				
				if(voo.valorBase() > maiorPreco){
					maiorPreco = voo.valorBase();
				}else{
					soma += voo.valorBase();
				}
			}
			double acrescimo = soma * super.ACRESCIMO_DIVERSOS_VOO;
			super.preco = maiorPreco + acrescimo; 
		}else {
			super.preco = 0;		
		}
		return super.preco;
	}
	@Override
	public int calcularPontos() {
		double somaPts = this.calcularPreco() / this.REF_VALOR;
		this.pontos =  ((int) somaPts * this.REF_VALOR);
		return this.pontos;
	}
	
	@Override
	public String descricao() {
		return super.descricao() + "\nPontos Obtidos: " + this.pontos;
	}

}
