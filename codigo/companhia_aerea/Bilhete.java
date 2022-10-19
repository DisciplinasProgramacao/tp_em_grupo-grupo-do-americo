package companhia_area;

import java.io.*;
import java.util.*;

public abstract class Bilhete {

	protected String codBilhete;
	protected double preco;
	protected Date data;
	protected List<Voo> reservas;
	protected final double ACRESCIMO_VOO = 0.1;
	protected final double ACRESCIMO_DIVERSOS_VOO = 0.5;

	protected abstract double calcularPreco();

	public String descricao() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

}
