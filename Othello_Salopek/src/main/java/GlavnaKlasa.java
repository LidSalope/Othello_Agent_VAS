import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlavnaKlasa {
    public static String bojaGlavnogIgraca;
    public static boolean krajIgre;
    public static Igra novaIgra;
    public static OthelloAgent agent;

    public static void main(String[] args) {

        System.out.println("OTHELLO");
        System.out.print("\n");
        boolean pokreniIgru = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (pokreniIgru) {
            System.out.println("Započni novu igru? (d/n)");
            String pocetak = null;
            try {
                pocetak = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (pocetak.equalsIgnoreCase("d")) {
                pokreniIgru = true;
                agent = new OthelloAgent();
                novaIgra = new Igra();
                setKrajIgre(false);

                System.out.println("Za početak si izaberite boju: C - crna, B - bijela");
                String unosBoje = null;
                try {
                    unosBoje = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (provjeriUnosIgraca(unosBoje)) {
                    System.out.println("Započeli ste novu igru!");
                    System.out.println("Odabrana boja žetona: " + bojaGlavnogIgraca);
                    System.out.println("Crni igrač igra prvi.");
                    System.out.println("Početna mreža:");
                    System.out.println("**************************************************");
                    novaIgra.mreza.ispisiMrezu();
                    System.out.println("Pokreni igru? (d/n)");
                    String start = null;
                    try {
                        start = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (start.equalsIgnoreCase("d")) {
                        while (!krajIgre) {
                            PokreniPoteze();
                        }
                    }
                } else {
                    System.out.println("ERROR pogrešni format unosa");
                }


            } else {
                pokreniIgru = false;
            }
        }


    }

    private static void PokreniPoteze() {
        if (bojaGlavnogIgraca.equalsIgnoreCase("B")) {
            agent.setBojaAgenta("C");
            while (true) {
                ažurirajBrojeveBijelihICrnih();
                int ukupanBrojNaPloci = novaIgra.getBrojBijelih() + novaIgra.getBrojCrnih();
                if(ukupanBrojNaPloci==64){
                    proglasiPobjednika();
                    krajIgre = true;
                    break;
                }else{
                    ispisRezultata();
                }
                List<Pozicija> mogucePozicije2 = izracunajMogucePoteze(novaIgra.mreza.getListaPozicija(), agent.getBojaAgenta());
                if (mogucePozicije2.size() > 0) {
                    OdigrajPotezAgent(mogucePozicije2);
                }
                ažurirajBrojeveBijelihICrnih();
                ukupanBrojNaPloci = novaIgra.getBrojBijelih() + novaIgra.getBrojCrnih();
                if(ukupanBrojNaPloci==64){
                    proglasiPobjednika();
                    krajIgre = true;
                    break;
                }else{
                    ispisRezultata();
                }
                List<Pozicija> mogucePozicije = izracunajMogucePoteze(novaIgra.mreza.getListaPozicija(), bojaGlavnogIgraca);
                boolean uspjesanPotez = false;
                boolean tempZavrsi = false;
                if (mogucePozicije.size() > 0) {
                    while (!uspjesanPotez) {
                        System.out.println("Unesi potez:");
                        System.out.println("format: (stupac - red) npr. D - 3");
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            String potez = br.readLine();
                            if (potez.equalsIgnoreCase("q")) {
                                krajIgre = true;
                                tempZavrsi = true;
                                break;
                            } else {
                                uspjesanPotez = obradiPotez(potez, mogucePozicije, bojaGlavnogIgraca);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (tempZavrsi) {
                    break;
                }
            }
        } else {
            agent.setBojaAgenta("B");
            while (true) {
                ažurirajBrojeveBijelihICrnih();
                int ukupanBrojNaPloci = novaIgra.getBrojBijelih() + novaIgra.getBrojCrnih();
                if(ukupanBrojNaPloci==64){
                    proglasiPobjednika();
                    krajIgre = true;
                    break;
                }else{
                    ispisRezultata();
                }
                List<Pozicija> mogucePozicije = izracunajMogucePoteze(novaIgra.mreza.getListaPozicija(), bojaGlavnogIgraca);
                boolean uspjesanPotez = false;
                boolean tempZavrsi = false;
                if (mogucePozicije.size() > 0) {
                    while (!uspjesanPotez) {
                        System.out.println("Unesi potez:");
                        System.out.println("format: (stupac - red) npr. D - 3");
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            String potez = br.readLine();
                            if (potez.equalsIgnoreCase("q")) {
                                krajIgre = true;
                                tempZavrsi = true;
                                break;
                            } else {
                                uspjesanPotez = obradiPotez(potez, mogucePozicije, bojaGlavnogIgraca);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (tempZavrsi) {
                    break;
                }
                ažurirajBrojeveBijelihICrnih();
                ukupanBrojNaPloci = novaIgra.getBrojBijelih() + novaIgra.getBrojCrnih();
                if(ukupanBrojNaPloci==64){
                    proglasiPobjednika();
                    krajIgre = true;
                    break;
                }else{
                    ispisRezultata();
                }
                List<Pozicija> mogucePozicije2 = izracunajMogucePoteze(novaIgra.mreza.getListaPozicija(), agent.getBojaAgenta());
                if (mogucePozicije2.size() > 0) {
                    OdigrajPotezAgent(mogucePozicije2);
                }

            }
        }
    }

    private static void ažurirajBrojeveBijelihICrnih() {
        List<Pozicija> bijeli = novaIgra.mreza.getListaPozicija();
        int brojBijelihTemp = 0;
        int brojCrnihTemp = 0;
        for(Pozicija p:bijeli){
            if(p.getBoja().equalsIgnoreCase("B")){
                brojBijelihTemp++;
            }
        }
        List<Pozicija> crni = novaIgra.mreza.getListaPozicija();
        for(Pozicija p:crni){
            if(p.getBoja().equalsIgnoreCase("C")){
                brojCrnihTemp++;
            }
        }
        novaIgra.setBrojBijelih(brojBijelihTemp);
        novaIgra.setBrojCrnih(brojCrnihTemp);
    }

    private static void ispisRezultata() {
        System.out.println("BIJELI - CRNI");
        System.out.println(novaIgra.getBrojBijelih()+"   -   "+novaIgra.getBrojCrnih());
    }

    private static void proglasiPobjednika() {
        if(novaIgra.getBrojBijelih()>novaIgra.getBrojCrnih()){
            if(bojaGlavnogIgraca.equalsIgnoreCase("B")){
                System.out.println("ČESTITAM POBIJEDILI STE AGENTA!");
            }else{
                System.out.println("AGENT JE POBIJEDIO. POKUŠAJTE PONOVNO!");
            }
        }else{
            if(bojaGlavnogIgraca.equalsIgnoreCase("C")){
                System.out.println("ČESTITAM POBIJEDILI STE AGENTA!");
            }else{
                System.out.println("AGENT JE POBIJEDIO. POKUŠAJTE PONOVNO!");
            }
        }
    }

    private static void OdigrajPotezAgent(List<Pozicija> mogucePozicije2) {
        Pozicija pozAgenta = agent.odaberiNajboljiPotez2(novaIgra, mogucePozicije2);
        String unosAgenta = kreirajUnosAgenta(pozAgenta);
        obradiPotez(unosAgenta, mogucePozicije2, agent.getBojaAgenta());
    }

    private static String kreirajUnosAgenta(Pozicija pos) {
        String odgovor = "";
        String stupac = novaIgra.pronadiStupacBrojSlovo(pos.getStupac());
        odgovor = stupac + " - " + pos.getRed();
        System.out.println("Agent: " + odgovor);
        return odgovor;
    }

    public static boolean obradiPotez(String unos, List<Pozicija> mogucePozicije, String bojaIgraca) {
        if (provjeriFormatPoteza(unos)) {
            String[] obrada = unos.split(" ");
            int stupacUnos = pronadiStupacSlovoBroj(obrada[0].toUpperCase());
            if (mogucePozicije.size() > 0) {
                for (Pozicija moguce : mogucePozicije) {
                    if (moguce.getStupac() == stupacUnos && moguce.getRed() == Integer.parseInt(obrada[2])) {
                        Pozicija novaPozicija = new Pozicija(stupacUnos, Integer.parseInt(obrada[2]), bojaIgraca);
                        Potez noviPotez = new Potez(novaPozicija, bojaIgraca);
                        novaIgra.dodajPotez(noviPotez);
                        promijeniBojeUMrezi(novaPozicija);
                        novaIgra.mreza.ispisiMrezu();
                        return true;
                    }
                }
                System.out.println("--------------------");
                System.out.println("NEMOGUĆA POZICIJA!");
                System.out.println("Pokušajte ponovno.");
                System.out.println("Za izlazak iz igre napišite Q");
            } else {
                System.out.println("NEMA MOGUĆIH POTEZA...");
            }
        }
        return false;
    }

    private static void promijeniBojeUMrezi(Pozicija novaPozicija) {
        List<Pozicija> listaZaAzurirati = new ArrayList<>();

        int stupacClana = 0;
        int redClana = 0;
        boolean novaImaVeciStupac = false;
        boolean novaImaVeciRed = false;

        //promijeni boje u redu
        //prođi kroz sve postojeće pozicije
        //pronađi drugu do koje se mijenja boja
        //ako su u istom redu a različiti stupac
        for (Pozicija postojeca : novaIgra.mreza.listaPozicija) {
            if (novaPozicija.getRed() == postojeca.getRed() && novaPozicija.getBoja().equalsIgnoreCase(postojeca.getBoja())) {
                if (postojeca.getStupac() != novaPozicija.getStupac()) {
                    if (postojeca.getStupac() > novaPozicija.getStupac()) {
                    } else {
                        novaImaVeciStupac = true;
                    }
                    stupacClana = postojeca.getStupac();
                }
            }
        }

        if (novaImaVeciStupac) {
            for (int i = novaPozicija.getStupac(); i >= stupacClana; i--) {
                Pozicija zaPromjenu = dohvatiPozicijuIzListe(novaIgra.mreza.listaPozicija, i, novaPozicija.getRed());
                if (zaPromjenu != null && !zaPromjenu.getBoja().equalsIgnoreCase(novaPozicija.getBoja())) {
                    listaZaAzurirati.add(zaPromjenu);
                }
            }
        } else {
            for (int i = stupacClana; i >= novaPozicija.getStupac(); i--) {
                Pozicija zaPromjenu = dohvatiPozicijuIzListe(novaIgra.mreza.listaPozicija, i, novaPozicija.getRed());
                if (zaPromjenu != null && !zaPromjenu.getBoja().equalsIgnoreCase(novaPozicija.getBoja())) {
                    listaZaAzurirati.add(zaPromjenu);
                }
            }
        }

        //promijeni boje u stupcu
        //prođi kroz sve postojeće pozicije
        //pronađi drugu do koje se mijenja boja
        //ako su u istom stupcu a različiti red dodaj u listu
        for (Pozicija postojeca : novaIgra.mreza.listaPozicija) {
            if (novaPozicija.getStupac() == postojeca.getStupac() && novaPozicija.getBoja().equalsIgnoreCase(postojeca.getBoja())) {
                if (postojeca.getRed() != novaPozicija.getRed()) {
                    if (postojeca.getRed() < novaPozicija.getRed()) {
                        novaImaVeciRed = true;
                    }
                    redClana = postojeca.getRed();
                }
            }
        }

        if (novaImaVeciRed) {
            for (int i = novaPozicija.getRed(); i >= redClana; i--) {
                Pozicija zaPromjenu = dohvatiPozicijuIzListe(novaIgra.mreza.listaPozicija, novaPozicija.getStupac(), i);
                if (zaPromjenu != null && !zaPromjenu.getBoja().equalsIgnoreCase(novaPozicija.getBoja())) {
                    listaZaAzurirati.add(zaPromjenu);
                }
            }
        } else {
            for (int i = redClana; i >= novaPozicija.getRed(); i--) {
                Pozicija zaPromjenu = dohvatiPozicijuIzListe(novaIgra.mreza.listaPozicija, novaPozicija.getStupac(), i);
                if (zaPromjenu != null && !zaPromjenu.getBoja().equalsIgnoreCase(novaPozicija.getBoja())) {
                    listaZaAzurirati.add(zaPromjenu);
                }
            }
        }

        if (novaIgra.mogucePozicijeDijagonala.size() > 0) {
            for (Dijagonale d : novaIgra.mogucePozicijeDijagonala) {
                if (d.getMogucaPozicija().getStupac() == novaPozicija.getStupac()) {
                    if (d.getMogucaPozicija().getRed() == novaPozicija.getRed()) {
                        for (Pozicija dP : d.getDijagonale()) {
                            listaZaAzurirati.add(dP);
                        }
                    }
                }
            }
        }

        List<Pozicija> azuriranaLista = azurirajBojeUListi(novaIgra.mreza.listaPozicija, listaZaAzurirati, novaPozicija.getBoja());
        novaIgra.azurirajListu(azuriranaLista);
    }


    public static List<Pozicija> azurirajBojeUListi(List<Pozicija> postojecaLista,
                                                    List<Pozicija> listaZaAzuriranje,
                                                    String boja) {
        List<Pozicija> azuriranaLista = postojecaLista;

        for (Pozicija pP : azuriranaLista) {
            for (Pozicija pZA : listaZaAzuriranje) {
                if (pZA.getRed() == pP.getRed() && pZA.getStupac() == pP.getStupac()) {
                    pP.setBoja(boja);
                }
            }
        }

        return azuriranaLista;
    }

    public static Pozicija dohvatiPozicijuIzListe(List<Pozicija> lista, int stupac, int red) {
        for (Pozicija p : lista) {
            if (p.getStupac() == stupac && p.getRed() == red) {
                return p;
            }
        }
        return null;
    }

    public static List<Pozicija> izracunajMogucePoteze(List<Pozicija> odigranePozicije, String bojaIgraca) {
        List<Pozicija> mogucePozicije = new ArrayList<>();

        if (bojaIgraca.equalsIgnoreCase("C")) {
            mogucePozicije = novaIgra.izracunajMogucePotezeCrni(odigranePozicije);
        } else {
            mogucePozicije = novaIgra.izracunajMogucePotezeBijeli(odigranePozicije);
        }

        return mogucePozicije;
    }


    private static boolean provjeriFormatPoteza(String unos) {
        Pattern unosP = Pattern.compile("^[A-H] \\- [1-8]$");

        Matcher unosM = unosP.matcher(unos);

        if (unosM.matches()) {
            return true;
        } else {
            System.out.println("Neispravan unos!");
        }
        return false;
    }


    private static int pronadiStupacSlovoBroj(String s) {
        int odgovor = 0;
        switch (s) {
            case "A":
                odgovor = 1;
                break;
            case "B":
                odgovor = 2;
                break;
            case "C":
                odgovor = 3;
                break;
            case "D":
                odgovor = 4;
                break;
            case "E":
                odgovor = 5;
                break;
            case "F":
                odgovor = 6;
                break;
            case "G":
                odgovor = 7;
                break;
            case "H":
                odgovor = 8;
                break;
        }
        return odgovor;
    }


    private static boolean provjeriUnosIgraca(String unos) {

        Pattern crnaBojaP = Pattern.compile("^C$");
        Pattern bijelaBojaP = Pattern.compile("^B$");

        Matcher crnaBojaM = crnaBojaP.matcher(unos);
        Matcher bijelaBojaM = bijelaBojaP.matcher(unos);

        if (crnaBojaM.matches()) {
            setBojaGlavnogIgraca(unos);
            agent.setBojaAgenta("B");
            return true;
        } else if (bijelaBojaM.matches()) {
            setBojaGlavnogIgraca(unos);
            agent.setBojaAgenta("C");
            return true;
        }
        return false;
    }

    public static String getBojaGlavnogIgraca() {
        return bojaGlavnogIgraca;
    }

    public static void setBojaGlavnogIgraca(String bojaGlavnogIgraca) {
        GlavnaKlasa.bojaGlavnogIgraca = bojaGlavnogIgraca;
    }

    public static boolean isKrajIgre() {
        return krajIgre;
    }

    public static void setKrajIgre(boolean krajIgre) {
        GlavnaKlasa.krajIgre = krajIgre;
    }

}
