public class Oferta_Standard extends ClasaParinte {

    Banca banca;
    Client client;
    private double comision;
    private int durata;



    public double getComision() {
        return comision;
    }

    public int getDurata() {
        return durata;
    }



    public void setComision(double comision) {
        this.comision = comision;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public Oferta_Standard(Banca banca, Client client, double comision, int durata) {
        this.banca = banca;
        this.client = client;
        this.comision = comision;
        this.durata = durata;
    }

    public double calculeazaSumaDisponibila(double sumaImprumutata) {
        double dobandaLunara = banca.getProcentaj_baza() / 12 / 100;
        double sumaFinala = sumaImprumutata;

        for (int luna = 0; luna < durata; luna++) {
            sumaFinala += sumaFinala * dobandaLunara;
        }

        double comisionAbsolut = sumaImprumutata * (comision / 100);

        sumaFinala += comisionAbsolut;
        return sumaFinala;
    }


    public String toString() {
        String numeBanca = "\u001B[1mNume bancă: " + banca.getNume() + "\u001B[0m\n";
        String numeClient = "\u001B[1mNume client: " + client.getNume() + " " + client.getPrenume() + "\u001B[0m\n";
        String detalii = "\u001B[1mDurata creditului: \u001B[0m" + durata + " luni\n" +
                "\u001B[1mComision: \u001B[0m" + comision + "\n";

        String linieSeparator = "\u001B[36m├──────────────────────────────────────┤\u001B[0m\n";
        String chenarSuperior = "\u001B[36m┌──────────────────────────────────────┐\u001B[0m\n";
        String chenarInferior = "\u001B[36m└──────────────────────────────────────┘\u001B[0m\n";

        return chenarSuperior + numeBanca + linieSeparator + numeClient + linieSeparator + detalii + chenarInferior;
    }






}
