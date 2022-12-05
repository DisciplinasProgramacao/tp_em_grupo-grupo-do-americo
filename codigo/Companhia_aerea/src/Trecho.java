import java.io.Serializable;

public class Trecho implements Serializable{

	private String codigo;
	private String origem;
	private String destino;

	public Trecho(String codigo, String origem, String destino) {
		if (codigo.isBlank() || origem.isBlank() || destino.isBlank())throw new NullPointerException();
		this.codigo = codigo;
		this.origem = origem;
		this.destino = destino;
	}

	/**
	 * ver origem e destino do trecho
	 */
	public String toString() {
		return this.codigo +" ("+this.origem+"/"+this.destino +")";
	}


	public String getCodigo() {
		return codigo;
	}

}
