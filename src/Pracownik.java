import java.io.Serializable;

public class Pracownik implements Serializable {
    private String imie;
    private String nazwisko;
    private double placa;
    private char plec;
    private int dzial;


    public Pracownik(String imie, String nazwisko, double placa, char plec, int dzial) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.placa = placa;
        this.plec = plec;
        this.dzial = dzial;
    }

    @Override
    public String toString() {
        return this.imie + " " + this.nazwisko + " " + this.placa + " " + this.plec +" " + this.dzial;
    }

    public boolean czyPracujeWDziale(int dzial) {
        if (this.dzial == dzial) {
            return true;
        } else {
            return false;
        }
    }
    public double getPlaca() {
        return this.placa;
    }
    public int getDzial() {
        return this.dzial;
    }
    public String getImie() {
        return this.imie;
    }
    public String getNazwisko() {
        return this.nazwisko;
    }
    public char getPlec() {
        return this.plec;
    }
}
