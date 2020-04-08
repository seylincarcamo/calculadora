package operations;

import java.text.DecimalFormat;
import javax.swing.JTextField;

/**
 *
 * @author Seilyn
 */
public class Resolve {

    private boolean denominadorCero;
    private double total;

    public Resolve() {
        denominadorCero = false;
        total = 0.0;
    }

    private String formaterDecimal(double f) {
        String s = "";
        DecimalFormat formateador = new DecimalFormat("###,###.###############");
        s = formateador.format(f);
        return s;
    }

    private double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
        return resultado;
    }

    public String getTotal() {
        String n = "";
        double temp = 0.0;
        if (!isdenominadorCero()) {
            if (isEntero()) {
                //n = Math.round(total) + "";                
                n = formaterDecimal(Math.round(total));//devuelve un valor entero
            } else {
                //devuelve valor con decimales
                temp = redondearDecimales(total, 15);
                n = formaterDecimal(temp);
            }
        } else {
            n = "No se puede dividir entre cero";
            total = 0.0;
        }
        return n;
    }

    private void set_denominadorCero(boolean b) {
        denominadorCero = b;
    }

    private boolean isdenominadorCero() {
        return denominadorCero;
    }

   public void negate() {
        double negate = total;
        total = negate * (-1);
    }

    public void sqrt(String tipo) {
        double sqrt = total;
        if (tipo.equals("raiz")) {
            total = Math.sqrt(sqrt);
        } else {
            total = Math.pow(sqrt, 2);
        }
    }    
    
    public void porcentaje(String numero){
        double p = Double.parseDouble(numero);
        double t = total;
        total = (total*p)/100 ;
    }

    public void reciproc() {
        double dividir = total;
        if (dividir > 0) {
            total = 1 / dividir;
        } else {
            set_denominadorCero(true);
        }
    }

    public boolean isEntero() {
        String n = total + "";
        int savePosition = 0;
        for (int x = 0; x <= n.length() - 1; x++) {
            if (n.charAt(x) == '.') {
                savePosition = x;
                break;
            }
        }
        for (int y = savePosition; y <= n.length() - 1; y++) {
            if ((n.charAt(y) >= '1') && (n.charAt(y) <= '9')) {
                return false;
            }
        }
        return true;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void reset() {
        total = 0;
        denominadorCero = false;
    }

    public void acumular(String numero) {
        total = Double.parseDouble(numero);
    }

    public void acumular(String signo, String numero) {
        double n = Double.parseDouble(numero);
        double temp = 0.0;
        set_denominadorCero(false);
        if (signo.equalsIgnoreCase("+")) {
            total = total + n;
        } else if (signo.equalsIgnoreCase("-")) {
            total = total - n;
        } else if (signo.equalsIgnoreCase("*") || signo.equalsIgnoreCase("x")) {
            total = total * n;
        } else {
            if (n > 0) {
                total = total / n;
            } else {
                set_denominadorCero(true);
            }
        }
    }//fin acumular
}
