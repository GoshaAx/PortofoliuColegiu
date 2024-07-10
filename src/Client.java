import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends ClasaParinte{

    private String nume;
    private String prenume;
    private String adresa;
    private String nrTelefon;
    private int PuncteDisponibile;
    private double sold;


    Client(){};
    public Client(String nume, String prenume, String adresa, String nrTelefon, int puncteDisponibile, double sold) {
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
        PuncteDisponibile = puncteDisponibile;
        this.sold = sold;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public int getPuncteDisponibile() {
        return PuncteDisponibile;
    }

    public double getSold() {
        return sold;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public void setPuncteDisponibile(int puncteDisponibile) {
        PuncteDisponibile = puncteDisponibile;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    void read(){
        System.out.println("Bun venit la compania de creditare, cum va numiti?");
        setNume(scanner.nextLine());

        System.out.println("Prenume:");
        setPrenume(scanner.nextLine());

        System.out.println("Adresă:");
        setAdresa(scanner.nextLine());

        System.out.println("Număr de telefon:");
        setNrTelefon(scanner.nextLine());

        System.out.println("Ați mai depus vreodată vreo ofertă? (da/nu)");
        String raspuns = scanner.nextLine();
        int nr_oferte_precedente = 0;
        if (raspuns.equalsIgnoreCase("da")) {
            System.out.println("Câte?");
            nr_oferte_precedente = scanner.nextInt();
        }
        int puncteDisponibile;
        if (nr_oferte_precedente > 5)
            puncteDisponibile = 50;
        else if (nr_oferte_precedente > 3)
            puncteDisponibile = 30;
        else if (nr_oferte_precedente >= 1)
            puncteDisponibile = 10;
        else
            puncteDisponibile = 0;

        setPuncteDisponibile(puncteDisponibile);

        System.out.println("Sold:");
        setSold(scanner.nextDouble());

    }


    public String toString() {
        String numeComplet = "\u001B[1mNume: " + getNume() + " " + getPrenume() + "\u001B[0m\n";
        String detalii = "\u001B[1mAdresă: \u001B[0m" + getAdresa() + "\n" +
                "\u001B[1mNumăr de telefon: \u001B[0m" + getNrTelefon() + "\n" +
                "\u001B[1mPuncte disponibile: \u001B[0m" + getPuncteDisponibile() + "\n" +
                "\u001B[1mSold: \u001B[0m" + getSold() + "\n";

        String chenarSuperior = "\u001B[36m╭────────────────────────────────────────╮\u001B[0m\n";
        String chenarInferior = "\u001B[36m╰────────────────────────────────────────╯\u001B[0m\n";

        return chenarSuperior + numeComplet + detalii + chenarInferior;
    }


}
