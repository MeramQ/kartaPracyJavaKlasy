import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Kadry {
    private final Pracownik[] pracownicy_;
    private int zatrudnienie_;

    public Kadry() {
        pracownicy_ = new Pracownik[100];
        zatrudnienie_ = 0;
    }

    public void dodajPracownika(Pracownik pracownik) {
        if (zatrudnienie_ < 100) {
            pracownicy_[zatrudnienie_] = pracownik;
            zatrudnienie_++;
        }
    }

    public void dodajPracownikaInteraktywnie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj dane nowego pracownika:");
        System.out.print("\timię: ");
        String imie = scanner.next();
        System.out.print("\tnazwisko: ");
        String nazwisko = scanner.next();
        System.out.print("\tpłeć: ");
        char plec = scanner.next().charAt(0);
        System.out.print("\tdział: ");
        int dzial = scanner.nextInt();
        System.out.print("\tpłaca: ");
        double placa = scanner.nextDouble();

        Pracownik pracownik = new Pracownik(imie, nazwisko, placa, plec, dzial);
        dodajPracownika(pracownik);
    }

    public double sredniZarobek() {
        double suma = 0;

        for (int i = 0; i < zatrudnienie_; i++) {
            suma += pracownicy_[i].getPlaca();
        }

        return suma / zatrudnienie_;
    }

    public double sredniZarobek(int dzial) {
        double suma = 0;
        int liczbaP = 0;

        for (int i = 0; i < zatrudnienie_; i++) {
            if (pracownicy_[i].getDzial() == dzial) {
                suma += pracownicy_[i].getPlaca();
                liczbaP++;
            }
        }

        return suma / liczbaP;
    }

    public int[] dajDzialy() {
        Set<Integer> dzialySet = new HashSet<>();

        for (int i = 0; i < zatrudnienie_; i++) {
            dzialySet.add(pracownicy_[i].getDzial());
        }

        return dzialySet.stream().mapToInt(Integer::intValue).toArray();
    }

    public void importujZPlikuTekstowego(String nazwaPliku) throws FileNotFoundException {
        File plik = new File(nazwaPliku);
        Scanner odczyt = new Scanner(plik);
        int liczbaP = 0;

        while (odczyt.hasNextLine()) {
            String linia = odczyt.nextLine();
            String[] dane = linia.split(" ");

            if (dane.length == 5) {
                String imie = dane[0];
                String nazwisko = dane[1];
                double placa = Double.parseDouble(dane[2]);
                char plec = dane[3].charAt(0);
                int dzial = Integer.parseInt(dane[4]);

                Pracownik pracownik = new Pracownik(imie, nazwisko, placa, plec, dzial);
                dodajPracownika(pracownik);
                liczbaP++;
            } else {
                System.out.println("Nieprawidłowy format wiersza: " + linia);
            }
        }
        System.out.printf("Wczytano poprawnie dane %d pracowników z pliku: %s%n",liczbaP, nazwaPliku);

        odczyt.close();
    }


    public void zapiszDoPliku(String nazwaPliku) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nazwaPliku))) {
            for (int i = 0; i < zatrudnienie_; i++) {
                out.writeObject(pracownicy_[i]);
            }

            System.out.println("Dane pracowników zapisano do pliku: " + nazwaPliku);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania do pliku: " + e.getMessage());
        }
    }

    public void odczytajZPliku(String nazwaPliku) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nazwaPliku))) {
            zatrudnienie_ = 0;

            while (true) {
                try {
                    Pracownik pracownik = (Pracownik) in.readObject();
                    dodajPracownika(pracownik);
                } catch (EOFException e) {
                    break;
                }
            }

            System.out.println("Dane pracowników wczytano z pliku: " + nazwaPliku);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Błąd podczas odczytu pliku: " + e.getMessage());
        }
    }

    public void pisz() {
        System.out.println("Informacje o pracownikach:");
        for (int i = 0; i < zatrudnienie_; i++) {
            Pracownik p = pracownicy_[i];
            System.out.printf("%s\t %s\t %c\t %d\t %.2f zł%n",
                    p.getImie(), p.getNazwisko(), p.getPlec(), p.getDzial(), p.getPlaca());
        }

        double sredniZarobekFirmy = sredniZarobek();
        System.out.printf("Średnie płaca w firmie: %.2f zł%n", sredniZarobekFirmy);

        int[] dzialy = dajDzialy();
        for (int dzial : dzialy) {
            System.out.printf("Średnia płaca w dziale %d wynosi: %.2f zł%n", dzial, sredniZarobek(dzial));
        }
    }
}