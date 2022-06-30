public class Pozicija {
    private int id = 1;
    private int stupac;
    private int red;
    private String boja;

    public Pozicija(int stupac, int red, String boja) {
        this.id += 1;
        this.red = red;
        this.stupac = stupac;
        this.boja = boja;
    }


    public int getId() {
        return id;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getStupac() {
        return stupac;
    }

    public void setStupac(int stupac) {
        this.stupac = stupac;
    }

}
