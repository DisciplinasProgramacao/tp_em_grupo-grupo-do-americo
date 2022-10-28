import java.time.LocalDate;

public class BilheteFidelidade extends Bilhete {

	public BilheteFidelidade(String codBilhete, LocalDate data, Voo voo) {
		super(codBilhete, data, voo);
		calcularPreco();
	}

	protected double calcularPreco() {
		return (super.preco = 0);
	}
	@Override
	public String descricao() {
		return super.descricao();
	}

}
