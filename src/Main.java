import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Kadry kadry = new Kadry();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj nazwę pliku tekstowego do importu pracowników:");
        String nazwaPliku = scanner.nextLine();
        try {
            kadry.importujZPlikuTekstowego(nazwaPliku);
        } catch (FileNotFoundException e) {
            System.err.println("Plik tekstowy nie został znaleziony: " + e.getMessage());
        }
        kadry.pisz();
        kadry.dodajPracownikaInteraktywnie();
        kadry.pisz();
        scanner.close();
    }
}