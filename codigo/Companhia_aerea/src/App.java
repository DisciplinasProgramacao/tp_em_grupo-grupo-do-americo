import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) throws ParseException {
        Date data = new Date(0, 0, 0);
        List<Voo> voos = new ArrayList<>();
        Voo voo = new Voo(new Trecho("01", "PARIS", "FRANÇA"), "01/01/2020 00:00", 1500);
        voos.add(voo);
        System.out.println(voos.size());
        BilheteComun b1 = new BilheteComun("F-0001", data,voos);
        int inteiro = b1.calcularPontos();
        System.out.println(b1.calcularPontos());
        System.out.println(b1.descricao());
    }
}
