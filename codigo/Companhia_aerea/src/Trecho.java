public class Trecho {

	private String codigo;
	private String origem;
	private String destino;

	public Trecho(String codigo, String origem, String destino) {
		this.codigo = codigo;
		this.origem = origem;
		this.destino = destino;
	}
	public String toString() {
		return this.codigo +" ("+this.origem+"/"+this.destino +")";
	}

}
