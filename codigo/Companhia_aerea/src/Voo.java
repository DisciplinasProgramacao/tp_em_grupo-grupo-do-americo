import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Voo {

	private Trecho trecho;
	private Date data;
	private double valorBase;

	public Voo(Trecho trecho, String data, double valorBase) throws ParseException  {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		this.trecho = trecho;
		this.data = sdf.parse(data);
		this.valorBase = valorBase;
	}

	public double valorBase() {
		return this.valorBase;
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		return this.trecho.toString() + " - " + sdf.format(this.data);
	}

}
