
    public class VIP extends Oferta_Platinum {
        private static final double BONUS = 5.0 / 100;

        public VIP(Banca banca, Client client, double comision, int durata) {
            super(banca, client, comision, durata);
        }

        @Override
        public double calculeazaSumaDisponibila(double sumaImprumutata) {
            double sumaFinala = super.calculeazaSumaDisponibila(sumaImprumutata);


            double bonusAbsolut = sumaImprumutata * BONUS;
            sumaFinala += bonusAbsolut;

            return sumaFinala;
        }

        @Override
        public String toString() {
            double sumaFinala = calculeazaSumaDisponibila(client.getSold());

            String formattedSumaFinala = String.format("%.2f", sumaFinala);

            String numeBanca = "\u001B[93mNume bancă: " + banca.getNume() + "\u001B[0m\n";
            String numeClient = "\u001B[93mNume client: " + client.getNume() + " " + client.getPrenume() + "\u001B[0m\n";
            String detalii = "\u001B[93mDurata creditului: \u001B[0m" + getDurata() + " \u001B[93mluni\u001B[0m\n" +
                    "\u001B[93mComision: \u001B[0m" + String.format("%.2f", (getComision() - COMISION_REDUS)) + "%\n" +
                    "\u001B[93mSuma disponibilă: \u001B[0m" + formattedSumaFinala + "\n";

            String linieSeparator = "\u001B[36m├──────────────────────────────────────┤\u001B[0m\n";
            String chenarSuperior = "\u001B[36m┌──────────────────────────────────────┐\u001B[0m\n";
            String chenarInferior = "\u001B[36m└──────────────────────────────────────┘\u001B[0m\n";

            return chenarSuperior + numeBanca + linieSeparator + numeClient + linieSeparator + detalii + chenarInferior;
        }

    }



