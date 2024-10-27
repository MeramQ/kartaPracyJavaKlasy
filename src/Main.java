import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Kadry kadry = new Kadry();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("MENU:");
            System.out.println("1 - Dodaj nowego pracownika");
            System.out.println("2 - Wyświetl informacje o pracownikach");
            System.out.println("3 - Zapisz dane do pliku binarnego");
            System.out.println("4 - Wczytaj dane z pliku binarnego");
            System.out.println("5 - Importuj pracowników z pliku tekstowego");
            System.out.println("6 - Oblicz średnie zarobki w firmie");
            System.out.println("7 - Oblicz średnie zarobki w danym dziale");
            System.out.println("0 - Zakończ program");
            System.out.print("Wybierz opcję: ");
            int wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    kadry.dodajPracownikaInteraktywnie();
                    break;

                case 2:
                    kadry.pisz();
                    break;

                case 3:
                    System.out.print("Podaj nazwę pliku do zapisu: ");
                    String nazwaPlikuZapis = scanner.next();
                    kadry.zapiszDoPliku(nazwaPlikuZapis);
                    break;

                case 4:
                    System.out.print("Podaj ścieżkę pliku do odczytu: ");
                    String nazwaPlikuOdczyt = scanner.next();
                    kadry.odczytajZPliku(nazwaPlikuOdczyt);
                    break;

                case 5:
                    System.out.print("Podaj ścieżkę pliku tekstowego do importu: ");
                    String nazwaPlikuTekstowego = scanner.next();
                    try {
                        kadry.importujZPlikuTekstowego(nazwaPlikuTekstowego);
                    } catch (FileNotFoundException e) {
                        System.err.println("Plik tekstowy nie został znaleziony: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.printf("Średnie zarobki w firmie: %.2f\n", kadry.sredniZarobek());
                    break;

                case 7:
                    System.out.print("Podaj numer działu: ");
                    int nrDzialu = scanner.nextInt();
                    System.out.printf("Średnie zarobki w dziale %d: %.2f\n", nrDzialu, kadry.sredniZarobek(nrDzialu));
                    break;

                case 0:
                    running = false;
                    System.out.println("Zakończono program.");
                    break;

                default:
                    System.out.println("Niepoprawna opcja. Spróbuj ponownie.");
            }
        }

        scanner.close();
    }
}