package companhia_area;

import java.io.*;
import java.util.*;

public class BilhetePromocional extends Bilhete {

	private int pontos;
	private final int REF_VALOR = 500;
	private final double DESCONTO_VALOR_TOTAL = 0.4;
	private final double DESCONTO_PONTOS = 0.5;

	protected double calcularPreco() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	public double calcularPontos() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	public String descricao() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

}
