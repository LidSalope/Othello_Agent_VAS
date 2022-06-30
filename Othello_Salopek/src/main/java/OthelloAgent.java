import java.util.ArrayList;
import java.util.List;

public class OthelloAgent {
    String bojaAgenta;

    public OthelloAgent() {
    }

    public String getBojaAgenta() {
        return bojaAgenta;
    }

    public void setBojaAgenta(String bojaAgenta) {
        this.bojaAgenta = bojaAgenta;
    }

    public Pozicija odaberiNajboljiPotez2(Igra igra, List<Pozicija> mogucePozicije) {
        Pozicija odabranaPozicija = null;
        int brojSuprotnihUkupno = 0;
        List<Pozicija> pozicijeSaIstimBrojemSuprotnih = new ArrayList<>();

        for (Pozicija mogucaPozicija : mogucePozicije) {
            int tempIzracunatBroj = izbrojiSuprotne(mogucaPozicija, igra.mreza.getListaPozicija(), igra.mogucePozicijeDijagonala);

            if (tempIzracunatBroj > brojSuprotnihUkupno) {
                brojSuprotnihUkupno = tempIzracunatBroj;
                odabranaPozicija = mogucaPozicija;
            }
            if(tempIzracunatBroj == brojSuprotnihUkupno){
                pozicijeSaIstimBrojemSuprotnih.add(mogucaPozicija);
            }
        }

        if(pozicijeSaIstimBrojemSuprotnih.size()>1){
            int min = 0;
            int max = pozicijeSaIstimBrojemSuprotnih.size();

            int random = (int)Math.floor(Math.random()*(max-min+1)+min);
            odabranaPozicija = pozicijeSaIstimBrojemSuprotnih.get(random);
        }

        return odabranaPozicija;
    }

    private int izbrojiSuprotne(Pozicija moguciPotez, List<Pozicija> odigranePozicije, List<Dijagonale> dijagonale) {
        int brojSuprotnihURedu = 0;
        int brojSuprotnihUStupcu = 0;
        int brojSuprotnihUkupno = 0;
        int brojUDijagonali = 0;

        boolean novaImaVeciStupac = false;
        boolean novaImaVeciRed = false;
        int stupacClana = 0;
        int redClana = 0;

        for (Pozicija postojeca : odigranePozicije) {
            if (moguciPotez.getRed() == postojeca.getRed()
                    && moguciPotez.getBoja().equalsIgnoreCase(postojeca.getBoja())) {
                if (postojeca.getStupac() != moguciPotez.getStupac()) {
                    if (postojeca.getStupac() > moguciPotez.getStupac()) {
                    } else {
                        novaImaVeciStupac = true;
                    }
                    stupacClana = postojeca.getStupac();
                }
            }
        }
        if (novaImaVeciStupac) {
            for (int i = moguciPotez.getStupac(); i >= stupacClana; i--) {
                Pozicija zaPromjenu = GlavnaKlasa.dohvatiPozicijuIzListe(odigranePozicije, i, moguciPotez.getRed());
                if (zaPromjenu != null && !zaPromjenu.getBoja().equalsIgnoreCase(moguciPotez.getBoja())) {
                    brojSuprotnihURedu++;
                }
            }
        } else {
            for (int i = stupacClana; i >= moguciPotez.getStupac(); i--) {
                Pozicija zaPromjenu = GlavnaKlasa.dohvatiPozicijuIzListe(odigranePozicije, i, moguciPotez.getRed());
                if (zaPromjenu != null && !zaPromjenu.getBoja().equalsIgnoreCase(moguciPotez.getBoja())) {
                    brojSuprotnihURedu++;
                }
            }
        }

        brojSuprotnihUkupno += brojSuprotnihURedu;

        for (Pozicija postojeca : odigranePozicije) {
            if (moguciPotez.getStupac() == postojeca.getStupac()
                    && moguciPotez.getBoja().equalsIgnoreCase(postojeca.getBoja())) {
                if (postojeca.getRed() != moguciPotez.getRed()) {
                    if (postojeca.getRed() < moguciPotez.getRed()) {
                        novaImaVeciRed = true;
                    }
                    redClana = postojeca.getRed();
                }
            }
        }

        if (novaImaVeciRed) {
            for (int i = moguciPotez.getRed(); i >= redClana; i--) {
                Pozicija zaPromjenu = GlavnaKlasa.dohvatiPozicijuIzListe(odigranePozicije, moguciPotez.getStupac(), i);
                if (zaPromjenu != null && !zaPromjenu.getBoja().equalsIgnoreCase(moguciPotez.getBoja())) {
                    brojSuprotnihUStupcu++;
                }
            }
        } else {
            for (int i = redClana; i >= moguciPotez.getRed(); i--) {
                Pozicija zaPromjenu = GlavnaKlasa.dohvatiPozicijuIzListe(odigranePozicije, moguciPotez.getStupac(), i);
                if (zaPromjenu != null && !zaPromjenu.getBoja().equalsIgnoreCase(moguciPotez.getBoja())) {
                    brojSuprotnihUStupcu++;
                }
            }
        }


        brojSuprotnihUkupno += brojSuprotnihUStupcu;


        if (dijagonale.size() > 0) {
            for (Dijagonale d : dijagonale) {
                if (d.getMogucaPozicija().getStupac() == moguciPotez.getStupac()) {
                    if (d.getMogucaPozicija().getRed() == moguciPotez.getRed()) {
                        brojUDijagonali = d.dijagonale.size();
                    }
                }
            }
        }

        brojSuprotnihUkupno += brojUDijagonali;

        return brojSuprotnihUkupno;
    }
}
