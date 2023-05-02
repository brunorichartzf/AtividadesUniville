import java.util.ArrayList;

public class GeraRelatorio {
    private String[] tiposGanho = {"Salário     ", "Freelancer", "Dividendos", "N. Especificado"};
    private String[] tiposGasto = {"Habitação", "Alimentação", "Lazer        ", "Transporte", "N. Especificado"};
    private String[] formaPagamento = {"Crédito", "Débito", "Pix", "Boleto", "N. Especificado"};
   
    public String geraRelatorioTipoGasto(ArrayList<Gasto> Gastos, String Data){
        
        String cabecalho = "\t\t\tGASTOS\nTipo\t\tData\t\tValor\t\tForma de Pagamento\n";
        StringBuilder sb = new StringBuilder(cabecalho);
        for (Gasto gasto : Gastos) {
            if(gasto.getData().substring(3, 10).equals(Data)){ 
            String linha = tiposGasto[gasto.getTipo()] + "\t" +
                    gasto.getData() + "\t" +
                    "R$ " + gasto.getValor() + "\t" + formaPagamento[gasto.getFormaPagamento()] + "\n";
            sb.append(linha);}
        }
        return sb.toString();
    }

    public String geraRelatorioTipoGanho(ArrayList<Ganho> Ganhos, String Data){
        
        String cabecalho = "\t\tGANHOS\nTipo\t\tData\t\tValor\n";
        StringBuilder sb = new StringBuilder(cabecalho);
        for (Ganho ganho : Ganhos) {
            if(ganho.getData().substring(3, 10).equals(Data)){
            String linha = tiposGanho[ganho.getTipo()] + "\t" +
                    ganho.getData() + "\t" +
                    "R$ " + ganho.getValor() + "\n";
            sb.append(linha);}
        }
        return sb.toString();
    }

    public String geraRelatorioMensal(ArrayList<Ganho> Ganhos, ArrayList<Gasto> Gastos, String Data){
        double totalGanho = 0;
        for (Ganho ganho : Ganhos) {
            if(ganho.getData().substring(3, 10).equals(Data)){
                totalGanho += ganho.getValor();
            }
        }
        double totalGasto = 0;
        for (Gasto gasto : Gastos){
            if(gasto.getData().substring(3, 10).equals(Data)){
                totalGasto += gasto.getValor();
        }}
        double situacao = totalGanho - totalGasto;

        String cabecalho = "\t\t    SALDO DE "+ Data +"\nGanhos Totais\t\tGastos Totais\t\tSituação do Mês\n";
        StringBuilder sb = new StringBuilder(cabecalho);
        
            String linha = "R$ "+ totalGanho + "\t\t" + "R$ " + totalGasto + "\t\t" + "R$ " + situacao + "\n";
            sb.append(linha);

        return sb.toString();
    }
}
