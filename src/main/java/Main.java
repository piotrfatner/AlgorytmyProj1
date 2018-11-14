import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Init matrix:");
        HandlerClass handler = new HandlerClass();
        int widthOfMatrix;
        int heigthOfMatrix;
        int priceOfCut;
        Scanner reader = new Scanner(System.in);
        System.out.println("Podaj szerokosc plytki:");
        widthOfMatrix= reader.nextInt();
        System.out.println("Podaj dlugosc plytki:");
        heigthOfMatrix= reader.nextInt();
        System.out.println("Podaj koszt pojedynczego ciecia:");
        priceOfCut = reader.nextInt();
        reader.close();
        handler.generateMatrix(widthOfMatrix,heigthOfMatrix);
        handler.bottomUpMethod(widthOfMatrix,heigthOfMatrix,priceOfCut);
        System.out.println("Top down method:\n");
        System.out.println("Best result:"+handler.topDownMethod(new int[widthOfMatrix][heigthOfMatrix],widthOfMatrix,heigthOfMatrix,priceOfCut));
    }
}
