import java.util.Scanner;

public class raiz{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Insira o valor de A");
        double a = in.nextDouble();
        System.out.println("Insira o valor de B");
        double b = in.nextDouble();
        System.out.println("Insira o valor de C");
        double c = in.nextDouble();
        double delta = Math.pow(b, 2) - 4*a*c;
        b = b * (-1);
        delta = Math.sqrt(delta);
        
        if(Double.isNaN(delta)){
            System.out.println("Impossível calcular a raíz.");
        }
        else{
        double x1 = (b + delta)/2*a;
        double x2 = (b - delta)/2*a;

        System.out.println("O valor de X1 e X2 são respectivamente:" + x1 +" e "+ x2);
        }
        in.close();
        
    }
}