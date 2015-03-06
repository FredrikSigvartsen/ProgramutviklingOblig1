package programutvikling.oblig.pkg1;

import java.io.Serializable;

public class Båteierregister implements Serializable {
    
    private static final long serialVersionUID = 312L;
    private Eier første;

    public Båteierregister(){
        første = null;
    }

    public void settInn( Eier ny ){
        if ( første == null )
            første = ny;
        else
        {
            Eier løper = første;
            while ( løper.neste != null )
                løper = løper.neste;
            løper.neste = ny;
        }
    }

    public String eierInfo(int medlemsNr){
        Eier løper = første;
        while(løper != null && løper.getMedlemsNr() != medlemsNr){
            løper = løper.neste;
        }
        return løper.toString();
    }
    
    public String visRegister() {
        String ut = "";
        Eier løper = første;

        while ( løper != null ){
            ut += løper.toString() + "\n\n";
            løper = løper.neste;
        }

        if ( !ut.equals( "" ) )
            return ut;
        else
            return "Registeret er tomt";
    }

    public Eier finnEier(int medlemsNr){
        Eier løper = første;
        while(løper != null && løper.getMedlemsNr() != medlemsNr) {
            løper = løper.neste;
        }
        return løper;
    }
    
    public Eier finnEier(Båt båt) {
        Eier løper = første;
        while(løper != null && løper.getBåt() != båt) {
            løper = løper.neste;
        }
        return løper;
    }
    
    public Båt finnBåt(int regNr) {
        Eier løper = første;
        while(løper != null) {
            if(løper.getBåt() != null && løper.getBåt().getRegNr() == regNr) {
               return løper.getBåt();
            }
            løper = løper.neste;
        }
        return null;
    }
    
    public boolean fjernBåt( Båt båt ) {
        
        Eier eier = finnEier(båt);
        if(eier == null) {
            return false;
        } else {
            eier.setBåt(null);
            return true;
        }
    }
    
    public String fjernEier(Eier eier) {
        
        if(eier.getBåt() == null) {
            if (første == null)
                return "Registeret er tomt";

            if (første == eier) {
                første = første.neste;
                return "Eieren ble slettet";
            }

            Eier løper = første;

            while (løper.neste != null) {
                if (løper.neste == eier) {
                    løper.neste = løper.neste.neste;
                    return "Eieren ble slettet";
                } else
                    løper = løper.neste;
            }
            return "Her skjedde det noe";
        }
        else
            return "Eieren eier en båt, og kan derfor ikke fjernes!";
    }

    public String eierSkifte(int regNr, int medlemsNr){
        
        Båt skiftebåt = finnBåt(regNr);
        Eier eier = finnEier(medlemsNr);
        Eier gammelEier = finnEier(skiftebåt);
        
        if(eier == null){
            return "Den nye eieren må registreres";
        }
        else if(skiftebåt == null) {
            return "Fant ikke båt med regNr: " + regNr;
        } else {
            eier.setBåt(skiftebåt);
            gammelEier.setBåt(null);
            return "Båten har skiftet eier";
        }
    }
}
