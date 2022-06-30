public class Potez {
    Pozicija pozicija;
    String igrac;

    public Potez(Pozicija pozicija, String igrac) {
        this.pozicija = pozicija;
        this.igrac = igrac;
    }

    public Pozicija getPozicija() {
        return pozicija;
    }

    public void setPozicija(Pozicija pozicija) {
        this.pozicija = pozicija;
    }

    public String getIgrac() {
        return igrac;
    }

    public void setIgrac(String igrac) {
        this.igrac = igrac;
    }
}
