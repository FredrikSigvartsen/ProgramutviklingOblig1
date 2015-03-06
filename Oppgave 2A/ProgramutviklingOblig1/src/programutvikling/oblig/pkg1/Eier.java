package programutvikling.oblig.pkg1;
import java.io.Serializable;

public class Eier implements Serializable{
    
    private static final long serialVersionUID = 123L;
    private int medlemsNr;
    private static int nesteNr = 1;
    private String navn;
    private String adresse;
    private Båt båt;
    Eier neste;
    
    public Eier(String navn, String adresse) {
        
        medlemsNr = nesteNr++;
        this.navn = navn;
        this.adresse = adresse;
        båt = null;
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
    
    public Båt getBåt() {
        return this.båt;
    }
    
    public void setBåt(Båt båt) {
        this.båt = båt;
    }
    
    public String toString() {
        return "Navn: " + this.navn + 
               "\nAdresse: " + this.adresse + 
               "\nMedlemsNr: " + this.medlemsNr + 
               "\nBåt: " + (båt != null ? "\n" + båt.toString() : "Ingen båt registrert");
    }
}
