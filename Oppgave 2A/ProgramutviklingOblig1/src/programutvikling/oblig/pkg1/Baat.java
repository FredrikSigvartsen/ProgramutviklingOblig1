package programutvikling.oblig.pkg1;
import java.io.Serializable;

public class Baat implements Serializable{
    
    private static final long serialVersionUID = 321L;
    private int registreringsNr;
    private static int nesteNr = 1;
    private String merke;
    private int aarsmodell;
    private int lengde;
    private int motorytelse;
    private String farge;
    
    public Baat(String merke, int aarsmodell, int lengde, int motorytelse, String farge) {
        
        registreringsNr = nesteNr++;
        this.merke = merke;
        this.aarsmodell = aarsmodell;
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
               "\nÅrsmodell: " + this.aarsmodell +
               "\nLengde: " + this.lengde + 
               "\nMotorytelse: " + this.motorytelse + 
               "\nFarge: " + this.farge;
    }
}
