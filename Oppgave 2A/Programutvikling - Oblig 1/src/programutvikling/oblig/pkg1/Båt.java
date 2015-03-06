package programutvikling.oblig.pkg1;
import java.io.Serializable;

public class Båt implements Serializable{
    
    private static final long serialVersionUID = 321L;
    private int registreringsNr;
    private static int nesteNr = 1;
    private String merke;
    private int årsmodell;
    private int lengde;
    private int motorytelse;
    private String farge;
    
    public Båt(String merke, int årsmodell, int lengde, int motorytelse, String farge) {
        
        registreringsNr = nesteNr++;
        this.merke = merke;
        this.årsmodell = årsmodell;
        this.lengde = lengde;
        this.motorytelse = motorytelse;
        this.farge = farge;
    }
    
    public static int getNr() {
        return nesteNr;
    }
    
    public static void setNr(int nr) {
        nesteNr = nr;
    }
    
    public int getRegNr() {
        return this.registreringsNr;
    }
    
    public String toString() {
        return "Merke: " + this.merke + 
               "\nRegistreringsNr: " + this.registreringsNr +
               "\nÅrsmodell: " + this.årsmodell +
               "\nLengde: " + this.lengde + 
               "\nMotorytelse: " + this.motorytelse + 
               "\nFarge: " + this.farge;
    }
}
