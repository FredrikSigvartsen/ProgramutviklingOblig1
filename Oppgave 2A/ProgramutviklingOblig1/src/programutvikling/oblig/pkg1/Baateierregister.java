package programutvikling.oblig.pkg1;

import java.io.Serializable;

public class Baateierregister implements Serializable {
    
    private static final long serialVersionUID = 312L;
    private Eier forste;

    public Baateierregister(){
        forste = null;
    }

    public void settInn( Eier ny ){
        if ( forste == null )
            forste = ny;
        else
        {
            Eier loper = forste;
            while ( loper.neste != null )
                loper = loper.neste;
            loper.neste = ny;
        }
    }

    public String eierInfo(int medlemsNr){
        Eier loper = forste;
        while(loper != null && loper.getMedlemsNr() != medlemsNr){
            loper = loper.neste;
        }
        return loper.toString();
    }
    
    public String visRegister() {
        String ut = "";
        Eier loper = forste;

        while ( loper != null ){
            ut += loper.toString() + "\n\n";
            loper = loper.neste;
        }

        if ( !ut.equals( "" ) )
            return ut;
        else
            return "Registeret er tomt";
    }

    public Eier finnEier(int medlemsNr){
        Eier loper = forste;
        while(loper != null && loper.getMedlemsNr() != medlemsNr) {
            loper = loper.neste;
        }
        return loper;
    }
    
    public Eier finnEier(Baat baat) {
        Eier loper = forste;
        while(loper != null && loper.getBaat() != baat) {
            loper = loper.neste;
        }
        return loper;
    }
    
    public Baat finnBaat(int regNr) {
        Eier loper = forste;
        while(loper != null) {
            if(loper.getBaat() != null && loper.getBaat().getRegNr() == regNr) {
               return loper.getBaat();
            }
            loper = loper.neste;
        }
        return null;
    }
    
    public boolean fjernBaat( Baat baat ) {
        
        Eier eier = finnEier(baat);
        if(eier == null) {
            return false;
        } else {
            eier.setBaat(null);
            return true;
        }
    }
    
    public String fjernEier(Eier eier) {
        
        if(eier.getBaat() == null) {
            if (forste == null)
                return "Registeret er tomt";

            if (forste == eier) {
                forste = forste.neste;
                return "Eieren ble slettet";
            }

            Eier loper = forste;

            while (loper.neste != null) {
                if (loper.neste == eier) {
                    loper.neste = loper.neste.neste;
                    return "Eieren ble slettet";
                } else
                    loper = loper.neste;
            }
            return "Her skjedde det noe";
        }
        else
            return "Eieren eier en baat, og kan derfor ikke fjernes!";
    }

    public String eierSkifte(int regNr, int medlemsNr){
        
        Baat skiftebaat = finnBaat(regNr);
        Eier eier = finnEier(medlemsNr);
        Eier gammelEier = finnEier(skiftebaat);
        
        if(eier == null){
            return "Den nye eieren maa registreres";
        }
        else if(skiftebaat == null) {
            return "Fant ikke baat med regNr: " + regNr;
        } else {
            eier.setBaat(skiftebaat);
            gammelEier.setBaat(null);
            return "Baaten har skiftet eier";
        }
    }
}
