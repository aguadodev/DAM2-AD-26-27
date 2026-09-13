import java.util.Scanner;

public class EjemploESProducto {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== XESTOR DE PRODUTOS ===");

        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();

        System.out.print("Prezo: ");
        double prezo = sc.nextDouble();

        System.out.print("Unidades: ");
        int unidades = sc.nextInt();

        sc.close();
    
        if (prezo < 0 || unidades < 0) {
            System.err.println("Erro: o prezo e as unidades non poden ser negativos.");
            return;
        }

        double total = prezo * unidades;

        System.out.println();
        System.out.println("=== RESUMO ===");
        System.out.println("Produto: " + nome);
        System.out.println("Prezo: " + prezo + " €");
        System.out.println("Unidades: " + unidades);
        System.out.println("Total: " + total + " €");
    }
}
