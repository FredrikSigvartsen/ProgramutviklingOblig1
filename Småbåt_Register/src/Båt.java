/**
 * Created by Jens on 18.02.2015.
 */
public class Båt {
    private int registreringsNr = 100000;
    private int nesteRegNr = registreringsNr++;
    private String merke;
    private int årsmodell;
    private int lengde;
    private int hk;
    private String farge;
    private Eier eier;
    Båt neste;

    public Båt(String m, int år, int l, int h, String f, Eier e){
        merke = m;
        årsmodell = år;
        lengde = l;
        hk = h;
        farge = f;
        nesteRegNr++;
        eier = e;
        neste = null;
    }

    public int getRegNr() {
        return nesteRegNr;
    }

    public String getMerke() {
        return merke;
    }

    public int getÅrsmodell() {
        return årsmodell;
    }

    public int getLengde() {
        return lengde;
    }

    public int getHk() {
        return hk;
    }

    public String getFarge() {
        return farge;
    }

    public Eier getEier() {
        return eier;
    }

    public void setEier(Eier nyeier){
        eier = nyeier;
    }

    public String toString() {
        return "----BåtRegNr: " + nesteRegNr + "\n" + "Merke: " + merke + "\n" + "Årsmodell: " + årsmodell + "\n" + "Lengde: " + lengde + "\n" + " Motorytelse: " + hk + "\n" + "Farge: " + farge + "\n" + "Eier: " + eier + "\n";
    }
}
