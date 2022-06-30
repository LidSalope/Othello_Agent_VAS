import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Mreza {
    private int redniBrojMreze = 0;
    List<Pozicija> listaPozicija = new ArrayList<>();
    
    public Mreza(){
        postaviPocetnuPoziciju();
        redniBrojMreze += 1;
    }

    private void postaviPocetnuPoziciju() {
        Pozicija prviBijeli = new Pozicija(4,4, "B");
        Pozicija drugiBijeli = new Pozicija(5,5, "B");
        Pozicija prviCrni = new Pozicija(5,4, "C");
        Pozicija drugiCrni = new Pozicija(4,5, "C");
        listaPozicija.add(prviBijeli);
        listaPozicija.add(drugiBijeli);
        listaPozicija.add(prviCrni);
        listaPozicija.add(drugiCrni);
    }

    public void ispisiMrezu() {
        ispisiZaglavljeTablice();
        List<Pozicija> tempRed;
        for (int red = 0; red < 8; red++) {
            tempRed = new ArrayList<>();
            System.out.print("| " +(red+1)+" |");
            for(Pozicija p : listaPozicija) {
                if (p.getRed() == (red+1)) {
                    tempRed.add(p);
                }
            }
            for (int stupac = 0; stupac < 8; stupac++) {
                boolean pronadenPodatak = false;
                if(tempRed.size() >=1){
                    for(Pozicija ps : tempRed){
                        if(ps.getStupac()==(stupac+1)){
                            System.out.print(" "+ps.getBoja()+" |");
                            pronadenPodatak = true;
                        }
                    }
                }
                if(!pronadenPodatak){
                    System.out.print("   |");
                }
                if(stupac==7){
                    System.out.print("\n");
                    System.out.println("-------------------------------------");
                }
            }
        }
    }

    private void ispisiZaglavljeTablice(){
        System.out.println("    | A | B | C | D | E | F | G | H |");
        System.out.println("-------------------------------------");
    }

    public List<Pozicija> getListaPozicija() {
        return listaPozicija;
    }

    public void setListaPozicija(List<Pozicija> listaPozicija) {
        this.listaPozicija = listaPozicija;
    }
}
