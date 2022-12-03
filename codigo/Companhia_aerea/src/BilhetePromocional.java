import java.time.LocalDate;

public class BilhetePromocional extends Bilhete {

	

	private int pontos;
	private final int REF_VALOR = 500;
	private final double DESCONTO_VALOR_TOTAL = 0.6;
	private final double DESCONTO_PONTOS = 0.5;

	public BilhetePromocional(String codBilhete, LocalDate data, Voo voo) {
		super(codBilhete, data, voo);
		calcularPontos();
	}
	@Override
	public double calcularPreco() {
		if(super.reservas.size() == 1){
			double acrescimo = reservas.get(0).valorBase() * super.ACRESCIMO_VOO;
			super.preco = (reservas.get(0).valorBase() + acrescimo) * this.DESCONTO_VALOR_TOTAL;
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
			super.preco = (maiorPreco + acrescimo) * this.DESCONTO_VALOR_TOTAL; 
		}else {
			super.preco = 0;		
		}
		return super.preco;
	}
	@Override
	public int calcularPontos() {
		double somaPts = this.calcularPreco() / this.REF_VALOR;
		this.pontos =  (int) (((int) somaPts * this.REF_VALOR) * this.DESCONTO_PONTOS);
		return this.pontos;
	}
	
	@Override
	public String descricao() {
		return super.descricao() + "\nPontos Obtidos: " + this.pontos+ " Tipo: Promocional";
	}

}
