
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Test {
    static Scanner in = new Scanner(System.in);
    public static final String FileName = "Banca.ser";

    public static ArrayList<ClasaParinte> lista = new ArrayList<ClasaParinte>();

    public static void salveazaInFisier() {
        try (FileOutputStream fos = new FileOutputStream(FileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(lista);
            System.out.println("\n\033[0;32mOfertele au fost stocate în fișier.\033[0m");

        } catch (FileNotFoundException e) {
            System.out.println("\n\033[0;31mEroare: Fișierul nu a fost găsit.\033[0m");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("\n\033[0;31mEroare la scrierea datelor în fișier.\033[0m");
            throw new RuntimeException(e);
        }
    }

    static public void DateDinFisier() {
        try (ObjectInputStream cin = new ObjectInputStream(new FileInputStream(FileName))) {
            lista = (ArrayList<ClasaParinte>) cin.readObject();
        } catch (EOFException eof) {
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("\n\033[0;31mEroare: Citirea datelor din fișier a eșuat.\033[0m");
        }
    }

    public static void showArrayList() {
        if (lista.isEmpty())
            System.out.println("\n\033[0;33mNu există clienți.\033[0m");

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
    }

    public static void main(String[] args) {
        DateDinFisier();
        showArrayList();

        Banca MAIB = new Banca("Moldova Agroindbank", 1970, 10);
        Banca MICB = new Banca("Moldincombank", 1989, 8);

        boolean continuaProgramul = true;
        while (continuaProgramul) {
            System.out.println("\n=======================================");
            System.out.println("\033[0;34mBine ați venit la sistemul nostru bancar!\033[0m");
            System.out.println("=======================================\n");

            Client client = new Client();
            client.read();

            // Verificare sold negativ
            if (client.getSold() < 0) {
                System.out.println("\n\033[0;31mEroare: Soldul introdus este negativ.\033[0m");
                continue;
            }

            System.out.println("\nPe câte luni doriți să efectuați depozitul?");
            int d = in.nextInt();
            in.nextLine();

            System.out.println("\nBăncile disponibile cu oferte conform datelor dumneavoastră:\n");
            System.out.println(MAIB);
            System.out.println(MICB);

            System.out.println("\nCare dintre ele vă interesează?");
            String banca = in.nextLine();
            ClasaParinte ofertaSelectata = null;

            if (banca.equalsIgnoreCase("maib") || banca.equalsIgnoreCase("Moldova Agroindbank")) {
                System.out.println("\nOferte disponibile pentru această bancă:");
                System.out.println("1. Oferta Standard");
                System.out.println(new Oferta_Standard(MAIB, client, 10, d));
                System.out.println("2. Oferta Platinum");
                System.out.println(new Oferta_Platinum(MAIB, client, 7, d));
                System.out.println("3. Oferta VIP");
                System.out.println(new VIP(MAIB, client, 5, d));
                System.out.println("Alegeti care oferta vreti sa o aplicam contului dvs.");
                int optiune = in.nextInt();
                switch (optiune) {
                    case 1:
                        ofertaSelectata = new Oferta_Standard(MAIB, client, 10, d);
                        break;
                    case 2:
                        ofertaSelectata = new Oferta_Platinum(MAIB, client, 7, d);
                        break;
                    case 3:
                        ofertaSelectata = new VIP(MAIB, client, 5, d);
                        break;
                    default:
                        System.out.println("\n\033[0;31mOpțiune invalidă!\033[0m");
                        break;
                }
            } else if (banca.equalsIgnoreCase("micb") || banca.equalsIgnoreCase("Moldincombank")) {
                System.out.println("\nOferte disponibile pentru această bancă:");
                System.out.println("1. Oferta Standard");
                System.out.println(new Oferta_Standard(MICB, client, 10, d));
                System.out.println("2. Oferta Platinum");
                System.out.println(new Oferta_Platinum(MICB, client, 7, d));
                System.out.println("3. Oferta VIP");
                System.out.println(new VIP(MICB, client, 5, d));
                System.out.println("Alegeti care oferta vreti sa o aplicam contului dvs.");

                int optiune = in.nextInt();
                switch (optiune) {
                    case 1:
                        ofertaSelectata = new Oferta_Standard(MICB, client, 10, d);
                        break;
                    case 2:
                        if (client.getPuncteDisponibile() >= 30)
                            ofertaSelectata = new Oferta_Platinum(MICB, client, 7, d);
                        else
                            System.out.println("\n\033[0;31mEroare: Nu aveți suficiente puncte disponibile. Aveți nevoie de minim 30, iar voi dispuneți doar de: " + client.getPuncteDisponibile() + "\033[0m");
                        break;
                    case 3:
                        if (client.getPuncteDisponibile() >= 50)
                            ofertaSelectata = new VIP(MICB, client, 5, d);
                        else
                            System.out.println("\n\033[0;31mEroare: Nu aveți suficiente puncte disponibile. Aveți nevoie de minim 50, iar voi dispuneți doar de: " + client.getPuncteDisponibile() + "\033[0m");
                        break;
                    default:
                        System.out.println("\n\033[0;31mOpțiune invalidă!\033[0m");
                        break;
                }
            }

            if (ofertaSelectata != null) {
                System.out.println("\nOferta selectată:");
                System.out.println(ofertaSelectata);
                lista.add(ofertaSelectata);
                salveazaInFisier();
            }

            System.out.println("\nDoriți să continuați cu alta bancă sau să introduceți un nou client? (banca/client/iesire)");
            String raspuns = in.next();
            if (raspuns.equalsIgnoreCase("banca") || raspuns.equalsIgnoreCase("client")) {
                continue;
            } else if (raspuns.equalsIgnoreCase("iesire")) {
                continuaProgramul = false;
            } else {
                System.out.println("\n\033[0;31mOpțiune invalidă! Programul se va închide.\033[0m");
                continuaProgramul = false;
            }
        }
    }
}