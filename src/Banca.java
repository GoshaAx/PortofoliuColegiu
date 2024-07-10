public class Banca extends ClasaParinte{
    private String nume;
    private int an_fondare;
    private double procentaj_baza;


    public Banca(String nume, int an_fondare, double procentaj_baza) {
        this.nume = nume;
        this.an_fondare = an_fondare;
        this.procentaj_baza = procentaj_baza;
    }

    public String getNume() {
        return nume;
    }

    public int getAn_fondare() {
        return an_fondare;
    }

    public double getProcentaj_baza() {
        return procentaj_baza;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setAn_fondare(int an_fondare) {
        this.an_fondare = an_fondare;
    }

    public void setProcentaj_baza(double procentaj_baza) {
        this.procentaj_baza = procentaj_baza;}

    public Banca getBanca() {
        return this;
    }



    @Override
    public String toString() {
        String numeFormatted = "\u001B[34mNume: " + nume + "\u001B[0m\n"; // Albastru pentru nume
        String anFondareFormatted = "\u001B[34mAn fondare: " + an_fondare + "\u001B[0m\n"; // Albastru pentru anul de fondare
        String procentajBazaFormatted = String.format("\u001B[34mProcentaj bază: %.2f%%\u001B[0m\n", procentaj_baza); // Albastru pentru procentajul de bază

        String chenarSuperior = "\u001B[36m┌──────────────────────────────────────┐\u001B[0m\n"; // Chenar superior
        String chenarInferior = "\u001B[36m└──────────────────────────────────────┘\u001B[0m\n"; // Chenar inferior

        return chenarSuperior + numeFormatted + anFondareFormatted + procentajBazaFormatted + chenarInferior;
    }
}
