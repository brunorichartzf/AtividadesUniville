import java.util.Random;
import java.util.Scanner;

public class AdvinhaNumero { // 459
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Pense em um número entre 1 e 1000. " +
                "Digite enter para continuar");
        leitor.nextLine();
        int tentativas = 10;
        int inferior = 1;
        int superior = 1000;
        while(tentativas > 0){
            System.out.println("Tentativas restantes = "+tentativas);
            System.out.println("Inferior = "+inferior);
            System.out.println("Superior = "+superior);
            int limite = superior - inferior;
            int chute = random.nextInt(limite + 1) + inferior;
            System.out.println("Voce pensou no numero "+chute+"?");
            System.out.println("Digite 1 para Sim.");
            System.out.println("Digite 2 para Não.");
            int resposta = leitor.nextInt();
            if(resposta == 1){
                System.out.println("Acertei!");
                System.out.println("Até a próxima!");
                break;
            }
            tentativas--;
            System.out.println("O numero que vc pensou é maior ou menor?");
            System.out.println("Digite 1 para Maior.");
            System.out.println("Digite 2 para Menor.");
            resposta = leitor.nextInt();
            if(resposta == 1){
                inferior = chute + 1;
            }else{
                superior = chute - 1;
            }
        }
        if(tentativas == 0){
            System.out.println("Eu perdi :(");
        }
        leitor.close();
    }
    
}