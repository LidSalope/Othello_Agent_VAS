import java.util.ArrayList;
import java.util.List;

public class Dijagonale {
    Pozicija mogucaPozicija;
    List<Pozicija> dijagonale;

    public Dijagonale(Pozicija mogucaPozicija){
        this.mogucaPozicija = mogucaPozicija;
        dijagonale = new ArrayList<>();
    }

    public void dodajDijagonaleUListu(List<Pozicija> lista){
        for(Pozicija p: lista){
            dijagonale.add(p);
        }
    }

    public Dijagonale(){
    }

    public Pozicija getMogucaPozicija() {
        return mogucaPozicija;
    }

    public void setMogucaPozicija(Pozicija mogucaPozicija) {
        this.mogucaPozicija = mogucaPozicija;
    }

    public List<Pozicija> getDijagonale() {
        return dijagonale;
    }

    public void setDijagonale(List<Pozicija> dijagonale) {
        this.dijagonale = dijagonale;
    }
}
