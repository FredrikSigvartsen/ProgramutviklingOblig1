package programutvikling.oblig.pkg1;
import java.io.Serializable;

public class Eier implements Serializable{
    
    private static final long serialVersionUID = 123L;
    private int medlemsNr;
    private static int nesteNr = 1;
    private String navn;
    private String adresse;
    private Baat baat;
    Eier neste;
    
    public Eier(String navn, String adresse) {
        
        medlemsNr = nesteNr++;
        this.navn = navn;
        this.adresse = adresse;
        baat = null;
    }
    
    public static int getNr() {
        return nesteNr;
    }
    
    public static void setNr(int nr) {
        nesteNr = nr;
    }
        
    
    public int getMedlemsNr() {
        return this.medlemsNr;
    }
    
    public Baat getBaat() {
        return this.baat;
    }
    
    public void setBaat(Baat baat) {
        this.baat = baat;
    }
    
    public String toString() {
        return "Navn: " + this.navn + 
               "\nAdresse: " + this.adresse + 
               "\nMedlemsNr: " + this.medlemsNr + 
               "\nBaat: " + (baat != null ? "\n" + baat.toString() : "Ingen baat registrert");
    }
}
