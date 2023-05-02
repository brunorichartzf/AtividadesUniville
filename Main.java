import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Ganho> Ganhos = new ArrayList<Ganho>();
        ArrayList<Gasto> Gastos = new ArrayList<Gasto>();
        GeraRelatorio relatorio = new GeraRelatorio();
        Scanner input = new Scanner(System.in);
        
        Boolean continua = true;
        int opcao = 0;
        int tipo = 0;
        String data = "";
        double valor = 0;
        int formaPag = 0;
        while(continua == true){
            System.out.println("Gestão Financeira\n-----------------\n"+
        "1 - Adicionar Gasto\n2 - Adicionar Ganho\n3 - Relatório de Gastos\n"+
        "4 - Relatório de Ganhos\n5 - Relatório Mensal\n6 - Sair\nSelecione uma opção\n"
        );
            opcao = input.nextInt();
            input.nextLine();
        switch(opcao){
            case 1:
                System.out.println("Adicionar Gasto\n-----------------\n"+
                "1 - Habitação\n2 - Alimentação\n3 - Lazer\n"+
                "4 - Transporte\n5 - N. Especificado\n6 - Voltar\nSelecione o tipo de gasto\n"
                );
                opcao = input.nextInt();
                input.nextLine();
                switch(opcao){
                    case 6:
                    break;
                    default:
                    tipo = opcao-1;
                    System.out.println("Informe a data no formato DD/MM/YYYY:");
                    data = input.nextLine();
                    System.out.println("Informe o valor gasto(Use '.' para ','):");
                    valor = input.nextDouble();
                    input.nextLine();
                    System.out.println("Selecione a forma de pagamento(1-Crédito, 2-Débito, 3-Pix, 4-Boleto, 5-N. Especificado):");
                    formaPag = input.nextInt()-1;
                    Gastos.add(new Gasto(tipo, data, valor, formaPag));
                    System.out.println("Aperte qualquer tecla para continuar:");
                input.nextLine();
                    break;
                }
            break;
            case 2:
                System.out.println("Adicionar Ganho\n-----------------\n"+
                "1 - Salário\n2 - Freelancer\n3 - Dividendos\n"+
                "4 - N. Especificado\n6 - Voltar\nSelecione o tipo de ganho\n"
                );
                opcao = input.nextInt();
                input.nextLine();
                switch(opcao){
                    case 6:
                    break;
                    default:
                    tipo = opcao-1;
                    System.out.println("Informe a data no formato DD/MM/YYYY:");
                    data = input.nextLine();
                    System.out.println("Informe o valor ganho(Use '.' para ','):");
                    valor = input.nextDouble();
                    Ganhos.add(new Ganho(tipo, data, valor));
                    System.out.println("Aperte qualquer tecla para continuar:");
                input.nextLine();
                }
            break;
            case 3:
                System.out.println("Digite a data no formado MM/YYYY:");
                data = input.nextLine();
                System.out.println(relatorio.geraRelatorioTipoGasto(Gastos, data));
                System.out.println("Aperte qualquer tecla para continuar:");
                input.nextLine();
            break;
            case 4:
                System.out.println("Digite a data no formado MM/YYYY:");
                data = input.nextLine();
                System.out.println(relatorio.geraRelatorioTipoGanho(Ganhos, data));
                System.out.println("Aperte qualquer tecla para continuar:");
                input.nextLine();
            break;
            case 5:
                System.out.println("Digite a data no formado MM/YYYY:");
                data = input.nextLine();
                System.out.println(relatorio.geraRelatorioMensal(Ganhos,Gastos,data));
                System.out.println("Aperte qualquer tecla para continuar:");
                input.nextLine();
            break;
            case 6:
                continua = false;
            break;
        }}input.close();
    }
}
