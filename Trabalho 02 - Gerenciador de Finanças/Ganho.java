public class Ganho {
    private int tipo;
    private String data;
    private double valor;

    //Constructor
    public Ganho(int tipo,String data, double valor){
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
    }

    //Setters e Getters
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public int getTipo() {
        return tipo;
    }

    public void setData(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }
}
