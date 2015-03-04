/**
 * Created by Jens on 18.02.2015.
 */
public class Eier {
    private int medlemsnr = 10000;
    private int nestenr = medlemsnr++;
    private String navn;
    private String adresse;
    public boolean båt = false;
    Eier neste;

    public Eier(String n, String a){
        navn = n;
        adresse = a;
        nestenr++;
        neste = null;
        båt = true;
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getMedlemsnrnr() {
        return nestenr;
    }

    public String toString() {
        return "Navn: " + navn + "\n" + "Adresse: " + adresse + "\n" + "MedlemsNr: " + nestenr;
    }
}
