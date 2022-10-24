import java.util.Date;
import java.util.List;

public class BilheteFidelidade extends Bilhete {

	public BilheteFidelidade(String codBilhete, Date data, List<Voo> reservas) {
		super(codBilhete, data, reservas);
		//TODO Auto-generated constructor stub
	}

	protected double calcularPreco() {
		return (super.preco = 0);
	}
	@Override
	public String descricao() {
		return super.descricao();
	}

}
