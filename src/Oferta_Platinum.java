public class Oferta_Platinum extends Oferta_Standard{
    protected static final double COMISION_REDUS = 3;
    public Oferta_Platinum(Banca banca, Client client, double comision, int durata) {
        super(banca, client, comision, durata);
    }

    @Override
    public double calculeazaSumaDisponibila(double sumaImprumutata) {
        double sumaFinala = super.calculeazaSumaDisponibila(sumaImprumutata);

        double comisionRedus = (getComision() - COMISION_REDUS) / 100;
        double comisionAbsolut = sumaImprumutata * comisionRedus;

        sumaFinala -= comisionAbsolut;

        return sumaFinala;
    }

    @Override
    public String toString() {
        double sumaFinala = calculeazaSumaDisponibila(client.getSold());
        String formattedSumaFinala = String.format("%.2f", sumaFinala);

        String numeBanca = "\u001B[95mNume bancă: " + banca.getNume() + "\u001B[0m\n";
        String numeClient = "\u001B[95mNume client: " + client.getNume() + " " + client.getPrenume() + "\u001B[0m\n";
        String detalii = "\u001B[93mDurata creditului: \u001B[0m" + getDurata() + " \u001B[93mluni\u001B[0m\n" +
                "\u001B[93mComision: \u001B[0m" + String.format("%.2f", (getComision() - COMISION_REDUS)) + "%\n" +
                "\u001B[93mSuma disponibilă: \u001B[0m" + formattedSumaFinala + "\n";

        String linieSeparator = "\u001B[36m├──────────────────────────────────────┤\u001B[0m\n";
        String chenarSuperior = "\u001B[36m┌──────────────────────────────────────┐\u001B[0m\n";
        String chenarInferior = "\u001B[36m└──────────────────────────────────────┘\u001B[0m\n";

        return chenarSuperior + numeBanca + linieSeparator + numeClient + linieSeparator + detalii + chenarInferior;
    }


}
