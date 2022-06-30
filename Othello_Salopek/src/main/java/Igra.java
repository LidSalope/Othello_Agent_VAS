import java.util.ArrayList;
import java.util.List;

public class Igra {
    List<Potez> listaPoteza;
    Mreza mreza;
    List<Dijagonale> mogucePozicijeDijagonala = new ArrayList<>();
    public List<Pozicija> tempGL = new ArrayList<>();
    public List<Pozicija> tempGD = new ArrayList<>();
    public List<Pozicija> tempDL = new ArrayList<>();
    public List<Pozicija> tempDD = new ArrayList<>();
    private int brojBijelih;
    private int brojCrnih;

    public Igra() {
        this.listaPoteza = new ArrayList<>();
        this.mreza = new Mreza();
        brojBijelih = 0;
        brojCrnih = 0;
    }

    public int getBrojBijelih() {
        return brojBijelih;
    }

    public void setBrojBijelih(int brojBijelih) {
        this.brojBijelih = brojBijelih;
    }

    public int getBrojCrnih() {

        return brojCrnih;
    }

    public void setBrojCrnih(int brojCrnih) {
        this.brojCrnih = brojCrnih;
    }

    public void dodajPotez(Potez potez) {
        listaPoteza.add(potez);
        mreza.listaPozicija.add(potez.getPozicija());
    }

    public void azurirajListu(List<Pozicija> novaLista) {
        mreza.setListaPozicija(novaLista);
    }

    public List<Pozicija> izracunajMogucePotezeBijeli(List<Pozicija> odigranePozicije) {
        List<Pozicija> mogucePozicije = new ArrayList<>();
        mogucePozicijeDijagonala.clear();
        tempGL.clear();
        tempGD.clear();
        tempDL.clear();
        tempDD.clear();


        List<Pozicija> odigraniPoteziCrni = odvojiBojeOdigranihPozicija(odigranePozicije, "C");
        List<Pozicija> odigraniPoteziBijeli = odvojiBojeOdigranihPozicija(odigranePozicije, "B");
        System.out.println("Mogući potezi bijeli igrač:");

        //pronađi po redovima za bijelog
        for (Pozicija pBijeli : odigraniPoteziBijeli) {
            List<Pozicija> listaURedu = dohvatiSveSuprotneUIstomRedu(odigraniPoteziCrni, pBijeli.getRed(), "C");
            int tempD = 0;
            int tempL = 0;
            if (pBijeli.getStupac() == 1) {
                //prođi desno od bijelog
                for (int i = pBijeli.getStupac(); i <= 8; i++) {
                    for (Pozicija pD : listaURedu) {
                        if (pD.getStupac() == i) {
                            tempD = i;
                        }
                    }
                }
            } else if (pBijeli.getStupac() == 8) {
                //prođi lijevo od bijelog
                for (int i = pBijeli.getStupac(); i >= 1; i--) {
                    for (Pozicija pD : listaURedu) {
                        if (pD.getStupac() == i) {
                            tempL = i;
                        }
                    }
                }
            } else {
                //prođi lijevo od bijelog
                for (int i = pBijeli.getStupac(); i >= 1; i--) {
                    for (Pozicija pD : listaURedu) {
                        if (pD.getStupac() == i) {
                            tempL = i;
                        }
                    }
                }
                //prođi desno od bijelog
                for (int i = pBijeli.getStupac(); i <= 8; i++) {
                    for (Pozicija pD : listaURedu) {
                        if (pD.getStupac() == i) {
                            tempD = i;
                        }
                    }
                }
            }

            if (tempD != 0 && tempD != 8) {
                if (!provjeriPoziciju(odigranePozicije, tempD + 1, pBijeli.getRed())) {
                    if (!provjeriPoziciju(mogucePozicije, tempD + 1, pBijeli.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(tempD + 1);
                        Pozicija novaPozicija = new Pozicija(tempD + 1, pBijeli.getRed(), "B");
                        if (provjeriIspravnostMogucePozicije(novaPozicija, odigranePozicije, pBijeli, "red")) {
                            mogucePozicije.add(novaPozicija);
                            System.out.println(stupac + " - " + pBijeli.getRed());
                        }
                    }
                }
            }
            if (tempL != 0 && tempL != 1) {
                if (!provjeriPoziciju(odigranePozicije, tempL - 1, pBijeli.getRed())) {
                    if (!provjeriPoziciju(mogucePozicije, tempL - 1, pBijeli.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(tempL - 1);
                        Pozicija novaPozicija = new Pozicija(tempL - 1, pBijeli.getRed(), "B");
                        if (provjeriIspravnostMogucePozicije(novaPozicija, odigranePozicije, pBijeli, "red")) {
                            mogucePozicije.add(novaPozicija);
                            System.out.println(stupac + " - " + pBijeli.getRed());
                        }
                    }
                }
            }
        }

        //pronađi po stupcima za bijelog
        for (Pozicija pBijeli : odigraniPoteziBijeli) {
            List<Pozicija> listaUStupcu = dohvatiSveSuprotneUIstomStupcu(odigraniPoteziCrni, pBijeli.getStupac(), "C");
            int tempG = 0;
            int tempD = 0;
            if (pBijeli.getRed() == 1) {
                //prođi dole od bijelog
                for (int i = pBijeli.getRed(); i <= 8; i++) {
                    for (Pozicija pD : listaUStupcu) {
                        if (pD.getRed() == i) {
                            tempD = i;
                        }
                    }
                }
            } else if (pBijeli.getRed() == 8) {
                //prođi gore od bijelog
                for (int i = pBijeli.getRed(); i >= 1; i--) {
                    for (Pozicija pD : listaUStupcu) {
                        if (pD.getRed() == i) {
                            tempG = i;
                        }
                    }
                }
            } else {
                //prođi gore od bijelog
                for (int i = pBijeli.getRed(); i >= 1; i--) {
                    for (Pozicija pD : listaUStupcu) {
                        if (pD.getRed() == i) {
                            tempG = i;
                        }
                    }
                }
                //prođi dole od bijelog
                for (int i = pBijeli.getRed(); i <= 8; i++) {
                    for (Pozicija pD : listaUStupcu) {
                        if (pD.getRed() == i) {
                            tempD = i;
                        }
                    }
                }
            }

            if (tempG != 0 && tempG != 1) {
                if (!provjeriPoziciju(odigranePozicije, pBijeli.getStupac(), tempG - 1)) {
                    if (!provjeriPoziciju(mogucePozicije, pBijeli.getStupac(), tempG - 1)) {
                        String stupac = pronadiStupacBrojSlovo(pBijeli.getStupac());
                        System.out.println(stupac + " - " + (tempG - 1));
                        Pozicija novaPozicija = new Pozicija(pBijeli.getStupac(), tempG - 1, "B");
                        mogucePozicije.add(novaPozicija);
                    }
                }
            }
            if (tempD != 0 && tempD != 8) {
                if (!provjeriPoziciju(odigranePozicije, pBijeli.getStupac(), tempD + 1)) {
                    if (!provjeriPoziciju(mogucePozicije, pBijeli.getStupac(), tempD + 1)) {
                        String stupac = pronadiStupacBrojSlovo(pBijeli.getStupac());
                        System.out.println(stupac + " - " + (tempD + 1));
                        Pozicija novaPozicija = new Pozicija(pBijeli.getStupac(), tempD + 1, "B");
                        mogucePozicije.add(novaPozicija);
                    }
                }
            }
        }

        //DIJAGONALE ZA BIJELOG
        for (Pozicija pBijeli : odigraniPoteziBijeli) {
            Pozicija goreLijevo = dohvatiMogucuUDijagGL(odigranePozicije, pBijeli.getStupac(), pBijeli.getRed(), "B");
            Pozicija goreDesno = dohvatiSveUDijagGD(odigranePozicije, pBijeli.getStupac(), pBijeli.getRed(), "B");
            Pozicija doleLijevo = dohvatiMogucuUDijagDL(odigranePozicije, pBijeli.getStupac(), pBijeli.getRed(), "B");
            Pozicija doleDesno = dohvatiMogucuUDijagDD(odigranePozicije, pBijeli.getStupac(), pBijeli.getRed(), "B");


            //GORE LIJEVO
            if (goreLijevo != null) {
                Pozicija novaGoreLijevo = new Pozicija((goreLijevo.getStupac() - 1), (goreLijevo.getRed() - 1), "B");
                if (provjeriIspravnostMogucePozicije(novaGoreLijevo, null, null, "dijagonala")) {
                    Dijagonale novaDijagonala = new Dijagonale(novaGoreLijevo);
                    if (mogucePozicijeDijagonala.size() == 0) {
                        mogucePozicijeDijagonala.add(novaDijagonala);
                    } else {
                        boolean pronadjen = false;
                        for (Dijagonale d : mogucePozicijeDijagonala) {
                            if (d.getMogucaPozicija().getStupac() == novaGoreLijevo.getStupac()) {
                                if (d.getMogucaPozicija().getRed() == novaGoreLijevo.getRed()) {
                                    novaDijagonala = d;
                                    pronadjen = true;
                                }
                            }
                        }
                        if (!pronadjen) {
                            mogucePozicijeDijagonala.add(novaDijagonala);
                        }
                    }
                    novaDijagonala.dodajDijagonaleUListu(tempGL);
                    if (!provjeriPoziciju(mogucePozicije, novaGoreLijevo.getStupac(), novaGoreLijevo.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(novaGoreLijevo.getStupac());
                        System.out.println(stupac + " - " + (novaGoreLijevo.getRed()));
                        mogucePozicije.add(novaGoreLijevo);

                    }
                }
            }
            if (goreDesno != null) {
                Pozicija novaGoreDesno = new Pozicija((goreDesno.getStupac() + 1), (goreDesno.getRed() - 1), "B");
                if (provjeriIspravnostMogucePozicije(novaGoreDesno, null, null, "dijagonala")) {
                    Dijagonale novaDijagonala = new Dijagonale(novaGoreDesno);
                    if (mogucePozicijeDijagonala.size() == 0) {
                        mogucePozicijeDijagonala.add(novaDijagonala);
                    } else {
                        boolean pronadjen = false;
                        for (Dijagonale d : mogucePozicijeDijagonala) {
                            if (d.getMogucaPozicija().getStupac() == novaGoreDesno.getStupac()) {
                                if (d.getMogucaPozicija().getRed() == novaGoreDesno.getRed()) {
                                    novaDijagonala = d;
                                    pronadjen = true;
                                }
                            }
                        }
                        if (!pronadjen) {
                            mogucePozicijeDijagonala.add(novaDijagonala);
                        }
                    }
                    novaDijagonala.dodajDijagonaleUListu(tempGD);
                    if (!provjeriPoziciju(mogucePozicije, novaGoreDesno.getStupac(), novaGoreDesno.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(novaGoreDesno.getStupac());
                        System.out.println(stupac + " - " + novaGoreDesno.getRed());
                        mogucePozicije.add(novaGoreDesno);

                    }
                }
            }
            if (doleLijevo != null) {
                Pozicija novaDoleLijevo = new Pozicija((doleLijevo.getStupac() - 1), (doleLijevo.getRed() + 1), "B");
                if (provjeriIspravnostMogucePozicije(novaDoleLijevo, null, null, "dijagonala")) {
                    Dijagonale novaDijagonala = new Dijagonale(novaDoleLijevo);
                    if (mogucePozicijeDijagonala.size() == 0) {
                        mogucePozicijeDijagonala.add(novaDijagonala);
                    } else {
                        boolean pronadjen = false;
                        for (Dijagonale d : mogucePozicijeDijagonala) {
                            if (d.getMogucaPozicija().getStupac() == novaDoleLijevo.getStupac()) {
                                if (d.getMogucaPozicija().getRed() == novaDoleLijevo.getRed()) {
                                    novaDijagonala = d;
                                    pronadjen = true;
                                }
                            }
                        }
                        if (!pronadjen) {
                            mogucePozicijeDijagonala.add(novaDijagonala);
                        }
                    }
                    novaDijagonala.dodajDijagonaleUListu(tempDL);
                    if (!provjeriPoziciju(mogucePozicije, novaDoleLijevo.getStupac(), novaDoleLijevo.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(novaDoleLijevo.getStupac());
                        System.out.println(stupac + " - " + novaDoleLijevo.getRed());
                        mogucePozicije.add(novaDoleLijevo);
                    }
                }
            }
            if (doleDesno != null) {
                Pozicija novaDoleDesno = new Pozicija((doleDesno.getStupac() + 1), (doleDesno.getRed() + 1), "B");
                if (provjeriIspravnostMogucePozicije(novaDoleDesno, null, null, "dijagonala")) {
                    Dijagonale novaDijagonala = new Dijagonale(novaDoleDesno);
                    if (mogucePozicijeDijagonala.size() == 0) {
                        mogucePozicijeDijagonala.add(novaDijagonala);
                    } else {
                        boolean pronadjen = false;
                        for (Dijagonale d : mogucePozicijeDijagonala) {
                            if (d.getMogucaPozicija().getStupac() == novaDoleDesno.getStupac()) {
                                if (d.getMogucaPozicija().getRed() == novaDoleDesno.getRed()) {
                                    novaDijagonala = d;
                                    pronadjen = true;
                                }
                            }
                        }
                        if (!pronadjen) {
                            mogucePozicijeDijagonala.add(novaDijagonala);
                        }
                    }
                    novaDijagonala.dodajDijagonaleUListu(tempDD);
                    if (!provjeriPoziciju(mogucePozicije, novaDoleDesno.getStupac(), novaDoleDesno.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(novaDoleDesno.getStupac());
                        System.out.println(stupac + " - " + novaDoleDesno.getRed());
                        mogucePozicije.add(novaDoleDesno);

                    }

                }
            }

        }

        return mogucePozicije;
    }


    public List<Pozicija> izracunajMogucePotezeCrni(List<Pozicija> odigranePozicije) {
        List<Pozicija> mogucePozicije = new ArrayList<>();
        mogucePozicijeDijagonala.clear();
        tempGL.clear();
        tempGD.clear();
        tempDL.clear();
        tempDD.clear();

        List<Pozicija> odigraniPoteziCrni = odvojiBojeOdigranihPozicija(odigranePozicije, "C");
        List<Pozicija> odigraniPoteziBijeli = odvojiBojeOdigranihPozicija(odigranePozicije, "B");
        System.out.println("Mogući potezi crni igrač:");
        //pronađi po redovima za crnog
        for (Pozicija pCrni : odigraniPoteziCrni) {
            List<Pozicija> listaURedu = dohvatiSveSuprotneUIstomRedu(odigraniPoteziBijeli, pCrni.getRed(), "B");
            int tempD = 0;
            int tempL = 0;
            if (pCrni.getStupac() == 1) {
                //prođi desno od crnog
                for (int i = pCrni.getStupac(); i <= 8; i++) {
                    for (Pozicija pD : listaURedu) {
                        if (pD.getStupac() == i) {
                            tempD = i;
                        }
                    }
                }
            } else if (pCrni.getStupac() == 8) {
                //prođi lijevo od crnog
                for (int i = pCrni.getStupac(); i >= 1; i--) {
                    for (Pozicija pD : listaURedu) {
                        if (pD.getStupac() == i) {
                            tempL = i;
                        }
                    }
                }
            } else {
                //prođi lijevo od crnog
                for (int i = pCrni.getStupac(); i >= 1; i--) {
                    for (Pozicija pD : listaURedu) {
                        if (pD.getStupac() == i) {
                            tempL = i;
                        }
                    }
                }
                //prođi desno od crnog
                for (int i = pCrni.getStupac(); i <= 8; i++) {
                    for (Pozicija pD : listaURedu) {
                        if (pD.getStupac() == i) {
                            tempD = i;
                        }
                    }
                }
            }

            if (tempD != 0 && tempD != 8) {
                if (!provjeriPoziciju(odigranePozicije, tempD + 1, pCrni.getRed())) {
                    if (!provjeriPoziciju(mogucePozicije, tempD + 1, pCrni.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(tempD + 1);
                        Pozicija novaPozicija = new Pozicija(tempD + 1, pCrni.getRed(), "C");
                        if (provjeriIspravnostMogucePozicije(novaPozicija, odigranePozicije, pCrni, "red")) {
                            mogucePozicije.add(novaPozicija);
                            System.out.println(stupac + " - " + pCrni.getRed());
                        }

                    }
                }
            }
            if (tempL != 0 && tempL != 1) {
                if (!provjeriPoziciju(odigranePozicije, tempL - 1, pCrni.getRed())) {
                    if (!provjeriPoziciju(mogucePozicije, tempL - 1, pCrni.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(tempL - 1);
                        Pozicija novaPozicija = new Pozicija(tempL - 1, pCrni.getRed(), "C");
                        if (provjeriIspravnostMogucePozicije(novaPozicija, odigranePozicije, pCrni, "red")) {
                            mogucePozicije.add(novaPozicija);
                            System.out.println(stupac + " - " + pCrni.getRed());
                        }
                    }
                }
            }
        }


        //pronađi po stupcima za crnog
        for (Pozicija pCrni : odigraniPoteziCrni) {
            List<Pozicija> listaUStupcu = dohvatiSveSuprotneUIstomStupcu(odigraniPoteziBijeli, pCrni.getStupac(), "B");
            int tempG = 0;
            int tempD = 0;
            if (pCrni.getRed() == 1) {
                //prođi dole od crnog
                for (int i = pCrni.getRed(); i <= 8; i++) {
                    for (Pozicija pD : listaUStupcu) {
                        if (pD.getRed() == i) {
                            tempD = i;
                        }
                    }
                }
            } else if (pCrni.getRed() == 8) {
                //prođi gore od crnog
                for (int i = pCrni.getRed(); i >= 1; i--) {
                    for (Pozicija pD : listaUStupcu) {
                        if (pD.getRed() == i) {
                            tempG = i;
                        }
                    }
                }
            } else {
                //prođi gore od crnog
                for (int i = pCrni.getRed(); i >= 1; i--) {
                    for (Pozicija pD : listaUStupcu) {
                        if (pD.getRed() == i) {
                            tempG = i;
                        }
                    }
                }
                //prođi dole od crnog
                for (int i = pCrni.getRed(); i <= 8; i++) {
                    for (Pozicija pD : listaUStupcu) {
                        if (pD.getRed() == i) {
                            tempD = i;
                        }
                    }
                }
            }

            if (tempG != 0 && tempG != 1) {
                if (!provjeriPoziciju(odigranePozicije, pCrni.getStupac(), tempG - 1)) {
                    if (!provjeriPoziciju(mogucePozicije, pCrni.getStupac(), tempG - 1)) {
                        String stupac = pronadiStupacBrojSlovo(pCrni.getStupac());
                        System.out.println(stupac + " - " + (tempG - 1));
                        Pozicija novaPozicija = new Pozicija(pCrni.getStupac(), tempG - 1, "C");
                        mogucePozicije.add(novaPozicija);
                    }
                }
            }
            if (tempD != 0 && tempD != 8) {
                if (!provjeriPoziciju(odigranePozicije, pCrni.getStupac(), tempD + 1)) {
                    if (!provjeriPoziciju(mogucePozicije, pCrni.getStupac(), tempD + 1)) {
                        String stupac = pronadiStupacBrojSlovo(pCrni.getStupac());
                        System.out.println(stupac + " - " + (tempD + 1));
                        Pozicija novaPozicija = new Pozicija(pCrni.getStupac(), tempD + 1, "C");
                        mogucePozicije.add(novaPozicija);
                    }
                }
            }
        }

        //IZRAČUNAJ DIJAGONALE MOGUĆE ZA CRNOG
        for (Pozicija pCrni : odigraniPoteziCrni) {
            Pozicija goreLijevo = dohvatiMogucuUDijagGL(odigranePozicije, pCrni.getStupac(), pCrni.getRed(), "C");
            Pozicija goreDesno = dohvatiSveUDijagGD(odigranePozicije, pCrni.getStupac(), pCrni.getRed(), "C");
            Pozicija doleLijevo = dohvatiMogucuUDijagDL(odigranePozicije, pCrni.getStupac(), pCrni.getRed(), "C");
            Pozicija doleDesno = dohvatiMogucuUDijagDD(odigranePozicije, pCrni.getStupac(), pCrni.getRed(), "C");


            //GORE LIJEVO
            if (goreLijevo != null) {
                Pozicija novaGoreLijevo = new Pozicija((goreLijevo.getStupac() - 1), (goreLijevo.getRed() - 1), "C");
                if (provjeriIspravnostMogucePozicije(novaGoreLijevo, null, null, "dijagonala")) {
                    Dijagonale novaDijagonala = new Dijagonale(novaGoreLijevo);
                    if (mogucePozicijeDijagonala.size() == 0) {
                        mogucePozicijeDijagonala.add(novaDijagonala);
                    } else {
                        boolean pronadjen = false;
                        for (Dijagonale d : mogucePozicijeDijagonala) {
                            if (d.getMogucaPozicija().getStupac() == novaGoreLijevo.getStupac()) {
                                if (d.getMogucaPozicija().getRed() == novaGoreLijevo.getRed()) {
                                    novaDijagonala = d;
                                    pronadjen = true;
                                }
                            }
                        }
                        if (!pronadjen) {
                            mogucePozicijeDijagonala.add(novaDijagonala);
                        }
                    }
                    novaDijagonala.dodajDijagonaleUListu(tempGL);
                    if (!provjeriPoziciju(mogucePozicije, novaGoreLijevo.getStupac(), novaGoreLijevo.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(novaGoreLijevo.getStupac());
                        System.out.println(stupac + " - " + (novaGoreLijevo.getRed()));
                        mogucePozicije.add(novaGoreLijevo);

                    }
                }
            }
            if (goreDesno != null) {
                Pozicija novaGoreDesno = new Pozicija((goreDesno.getStupac() + 1), (goreDesno.getRed() - 1), "C");
                if (provjeriIspravnostMogucePozicije(novaGoreDesno, null, null, "dijagonala")) {
                    Dijagonale novaDijagonala = new Dijagonale(novaGoreDesno);
                    if (mogucePozicijeDijagonala.size() == 0) {
                        mogucePozicijeDijagonala.add(novaDijagonala);
                    } else {
                        boolean pronadjen = false;
                        for (Dijagonale d : mogucePozicijeDijagonala) {
                            if (d.getMogucaPozicija().getStupac() == novaGoreDesno.getStupac()) {
                                if (d.getMogucaPozicija().getRed() == novaGoreDesno.getRed()) {
                                    novaDijagonala = d;
                                    pronadjen = true;
                                }
                            }
                        }
                        if (!pronadjen) {
                            mogucePozicijeDijagonala.add(novaDijagonala);
                        }
                    }
                    novaDijagonala.dodajDijagonaleUListu(tempGD);
                    if (!provjeriPoziciju(mogucePozicije, novaGoreDesno.getStupac(), novaGoreDesno.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(novaGoreDesno.getStupac());
                        System.out.println(stupac + " - " + novaGoreDesno.getRed());
                        mogucePozicije.add(novaGoreDesno);

                    }
                }
            }
            if (doleLijevo != null) {
                Pozicija novaDoleLijevo = new Pozicija((doleLijevo.getStupac() - 1), (doleLijevo.getRed() + 1), "C");
                if (provjeriIspravnostMogucePozicije(novaDoleLijevo, null, null, "dijagonala")) {
                    Dijagonale novaDijagonala = new Dijagonale(novaDoleLijevo);
                    if (mogucePozicijeDijagonala.size() == 0) {
                        mogucePozicijeDijagonala.add(novaDijagonala);
                    } else {
                        boolean pronadjen = false;
                        for (Dijagonale d : mogucePozicijeDijagonala) {
                            if (d.getMogucaPozicija().getStupac() == novaDoleLijevo.getStupac()) {
                                if (d.getMogucaPozicija().getRed() == novaDoleLijevo.getRed()) {
                                    novaDijagonala = d;
                                    pronadjen = true;
                                }
                            }
                        }
                        if (!pronadjen) {
                            mogucePozicijeDijagonala.add(novaDijagonala);
                        }
                    }
                    novaDijagonala.dodajDijagonaleUListu(tempDL);
                    if (!provjeriPoziciju(mogucePozicije, novaDoleLijevo.getStupac(), novaDoleLijevo.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(novaDoleLijevo.getStupac());
                        System.out.println(stupac + " - " + novaDoleLijevo.getRed());
                        mogucePozicije.add(novaDoleLijevo);
                    }
                }
            }
            if (doleDesno != null) {
                Pozicija novaDoleDesno = new Pozicija((doleDesno.getStupac() + 1), (doleDesno.getRed() + 1), "C");
                if (provjeriIspravnostMogucePozicije(novaDoleDesno, null, null, "dijagonala")) {
                    Dijagonale novaDijagonala = new Dijagonale(novaDoleDesno);
                    if (mogucePozicijeDijagonala.size() == 0) {
                        mogucePozicijeDijagonala.add(novaDijagonala);
                    } else {
                        boolean pronadjen = false;
                        for (Dijagonale d : mogucePozicijeDijagonala) {
                            if (d.getMogucaPozicija().getStupac() == novaDoleDesno.getStupac()) {
                                if (d.getMogucaPozicija().getRed() == novaDoleDesno.getRed()) {
                                    novaDijagonala = d;
                                    pronadjen = true;
                                }
                            }
                        }
                        if (!pronadjen) {
                            mogucePozicijeDijagonala.add(novaDijagonala);
                        }
                    }
                    novaDijagonala.dodajDijagonaleUListu(tempDD);
                    if (!provjeriPoziciju(mogucePozicije, novaDoleDesno.getStupac(), novaDoleDesno.getRed())) {
                        String stupac = pronadiStupacBrojSlovo(novaDoleDesno.getStupac());
                        System.out.println(stupac + " - " + novaDoleDesno.getRed());
                        mogucePozicije.add(novaDoleDesno);

                    }

                }
            }

        }


        return mogucePozicije;
    }

    private Pozicija dohvatiMogucuUDijagDD(List<Pozicija> svePozicije, int stupac, int red, String b) {
        Pozicija dijagonalnaLista = null;
        List<Pozicija> temp = new ArrayList<>();


        if (provjeriPoziciju(svePozicije, (stupac + 1), (red + 1))) {
            Pozicija doleDesno = GlavnaKlasa.dohvatiPozicijuIzListe(svePozicije, (stupac + 1), (red + 1));
            if (doleDesno.getBoja().equalsIgnoreCase(b)) {
                return null;
            } else {
                dijagonalnaLista = doleDesno;
                temp.add(doleDesno);
                int tempRed = red + 2;
                for (int i = stupac + 2; i <= 8; i++) {
                    if (tempRed > 8) {
                        break;
                    }
                    Pozicija tempDD = GlavnaKlasa.dohvatiPozicijuIzListe(svePozicije, i, tempRed);
                    if (tempDD == null) {
                        this.tempDD = temp;
                        return dijagonalnaLista;
                    } else if (tempDD.getBoja().equalsIgnoreCase(b)) {
                        return null;
                    } else {
                        temp.add(tempDD);
                        dijagonalnaLista = tempDD;
                    }
                    tempRed++;
                }
            }
        } else {
            return null;
        }

        return null;
    }

    private Pozicija dohvatiMogucuUDijagDL(List<Pozicija> svePozicije, int stupac, int red, String b) {
        Pozicija dijagonalnaLista = null;
        List<Pozicija> temp = new ArrayList<>();

        if (provjeriPoziciju(svePozicije, (stupac - 1), (red + 1))) {
            Pozicija doleLijevo = GlavnaKlasa.dohvatiPozicijuIzListe(svePozicije, (stupac - 1), (red + 1));
            if (doleLijevo.getBoja().equalsIgnoreCase(b)) {
                return null;
            } else {
                dijagonalnaLista = doleLijevo;
                temp.add(doleLijevo);
                int tempRed = red + 2;
                for (int i = stupac - 2; i >= 1; i--) {
                    if (tempRed > 8) {
                        break;
                    }
                    Pozicija tempDL = GlavnaKlasa.dohvatiPozicijuIzListe(svePozicije, i, tempRed);
                    if (tempDL == null) {
                        this.tempDL = temp;
                        return dijagonalnaLista;
                    } else if (tempDL.getBoja().equalsIgnoreCase(b)) {
                        return null;
                    } else {
                        dijagonalnaLista = tempDL;
                        temp.add(tempDL);
                    }
                    tempRed++;
                }
            }
        } else {
            return null;
        }

        return null;

    }

    private Pozicija dohvatiSveUDijagGD(List<Pozicija> svePozicije, int stupac, int red, String b) {
        Pozicija dijagonalnaLista = null;
        List<Pozicija> temp = new ArrayList<>();

        if (provjeriPoziciju(svePozicije, (stupac + 1), (red - 1))) {
            Pozicija goreDesno = GlavnaKlasa.dohvatiPozicijuIzListe(svePozicije, (stupac + 1), (red - 1));
            if (goreDesno.getBoja().equalsIgnoreCase(b)) {
                return null;
            } else {
                dijagonalnaLista = goreDesno;
                temp.add(goreDesno);
                int tempRed = red - 2;
                for (int i = stupac + 2; i <= 8; i++) {
                    if (tempRed < 1) {
                        break;
                    }
                    Pozicija tempGD = GlavnaKlasa.dohvatiPozicijuIzListe(svePozicije, i, tempRed);
                    if (tempGD == null) {
                        this.tempGD = temp;
                        return dijagonalnaLista;
                    } else if (tempGD.getBoja().equalsIgnoreCase(b)) {
                        return null;
                    } else {
                        dijagonalnaLista = tempGD;
                        temp.add(tempGD);
                    }
                    tempRed--;
                }
            }
        } else {
            return null;
        }


        return null;
    }


    private Pozicija dohvatiMogucuUDijagGL(List<Pozicija> svePozicije, int stupac, int red, String b) {
        Pozicija dijagonalnaLista = null;
        List<Pozicija> temp = new ArrayList<>();

        if (provjeriPoziciju(svePozicije, (stupac - 1), (red - 1))) {
            Pozicija goreLijevo = GlavnaKlasa.dohvatiPozicijuIzListe(svePozicije, (stupac - 1), (red - 1));
            if (goreLijevo.getBoja().equalsIgnoreCase(b)) {
                return null;
            } else {
                dijagonalnaLista = goreLijevo;
                temp.add(goreLijevo);
                int tempRed = red - 2;
                for (int i = stupac - 2; i >= 1; i--) {
                    if (tempRed < 1) {
                        break;
                    }
                    Pozicija tempGL = GlavnaKlasa.dohvatiPozicijuIzListe(svePozicije, i, tempRed);
                    if (tempGL == null) {
                        this.tempGL = temp;
                        return dijagonalnaLista;
                    } else if (tempGL.getBoja().equalsIgnoreCase(b)) {
                        return null;
                    } else {
                        dijagonalnaLista = tempGL;
                        temp.add(tempGL);
                    }
                    tempRed--;
                }
            }
        } else {
            return null;
        }

        return null;
    }

    public List<Pozicija> dohvatiSveSuprotneUIstomStupcu(List<Pozicija> pozicije, int stupac, String boja) {
        List<Pozicija> uIstomStupcu = new ArrayList<>();

        for (Pozicija p : pozicije) {
            if (p.getStupac() == stupac && p.getBoja().equalsIgnoreCase(boja)) {
                uIstomStupcu.add(p);
            }
        }
        return uIstomStupcu;
    }


    public List<Pozicija> dohvatiSveSuprotneUIstomRedu(List<Pozicija> pozicije, int red, String boja) {
        List<Pozicija> uIstomRedu = new ArrayList<>();

        for (Pozicija p : pozicije) {
            if (p.getRed() == red && p.getBoja().equalsIgnoreCase(boja)) {
                uIstomRedu.add(p);
            }
        }
        return uIstomRedu;
    }

    public boolean provjeriPoziciju(List<Pozicija> pozicije, int stupac, int red) {
        for (Pozicija p : pozicije) {
            if (p.getRed() == red && p.getStupac() == stupac) {
                return true;
            }
        }
        return false;
    }

    public List<Pozicija> odvojiBojeOdigranihPozicija(List<Pozicija> odigrano, String boja) {
        List<Pozicija> odvojeno = new ArrayList<>();

        for (Pozicija o : odigrano) {
            if (o.getBoja().equalsIgnoreCase(boja)) {
                odvojeno.add(o);
            }
        }

        return odvojeno;
    }

    public String pronadiStupacBrojSlovo(int stupac) {
        String odgovor = " ";
        switch (stupac) {
            case 1:
                odgovor = "A";
                break;
            case 2:
                odgovor = "B";
                break;
            case 3:
                odgovor = "C";
                break;
            case 4:
                odgovor = "D";
                break;
            case 5:
                odgovor = "E";
                break;
            case 6:
                odgovor = "F";
                break;
            case 7:
                odgovor = "G";
                break;
            case 8:
                odgovor = "H";
                break;
        }
        return odgovor;
    }


    public boolean provjeriIspravnostMogucePozicije(Pozicija mogucaPozicija, List<Pozicija> odigranePozicije, Pozicija krajnjaPozicija, String tip) {
        if (mogucaPozicija.getRed() > 8 || mogucaPozicija.getStupac() > 8) {
            return false;
        }
        if (mogucaPozicija.getRed() < 1 || mogucaPozicija.getStupac() < 1) {
            return false;
        }
        if (tip.equalsIgnoreCase("red")) {
            if (krajnjaPozicija.getStupac() > mogucaPozicija.getStupac()) {
                for (int i = krajnjaPozicija.getStupac() - 1; i > mogucaPozicija.getStupac(); i--) {
                    Pozicija dohvatiIducu = GlavnaKlasa.dohvatiPozicijuIzListe(odigranePozicije, i, mogucaPozicija.getRed());
                    if (dohvatiIducu == null) {
                        return false;
                    }
                }
            } else {
                for (int i = mogucaPozicija.getStupac() - 1; i > krajnjaPozicija.getStupac(); i--) {
                    Pozicija dohvatiIducu = GlavnaKlasa.dohvatiPozicijuIzListe(odigranePozicije, i, krajnjaPozicija.getRed());
                    if (dohvatiIducu == null) {
                        return false;
                    }
                }
            }
        } else if (tip.equalsIgnoreCase("stupac")) {
            if (krajnjaPozicija.getRed() > mogucaPozicija.getRed()) {
                for (int i = mogucaPozicija.getRed(); i < krajnjaPozicija.getRed(); i--) {
                    Pozicija dohvatiIducu = GlavnaKlasa.dohvatiPozicijuIzListe(odigranePozicije, krajnjaPozicija.getStupac(), i);
                    if (dohvatiIducu == null) {
                        return false;
                    }
                }
            } else {
                for (int i = krajnjaPozicija.getRed(); i < mogucaPozicija.getRed(); i--) {
                    Pozicija dohvatiIducu = GlavnaKlasa.dohvatiPozicijuIzListe(odigranePozicije, krajnjaPozicija.getStupac(), i);
                    if (dohvatiIducu == null) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
