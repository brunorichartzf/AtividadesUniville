public class Gasto {
    private int tipo;
    private String data;
    private double valor;
    private int formaPagamento;

    //Constructor
    public Gasto(int tipo,String data, double valor, int formaPagamento){
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
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

    public void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    public int getFormaPagamento() {
        return formaPagamento;
    }
}
