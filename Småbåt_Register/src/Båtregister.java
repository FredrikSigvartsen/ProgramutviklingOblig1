/**
 * Created by Jens on 18.02.2015.
 */
public class Båtregister {
    private Båt første;

    public Båtregister(){
        første = null;
    }

    public void settInn( Båt ny ){
        if ( første == null )
            første = ny;
        else{
            Båt løper = første;
            while ( løper.neste != null )
                løper = løper.neste;
            løper.neste = ny;
        }
    }

    public Båt finnBåt(int regnr){
        Båt løper = første;
        while(løper != null && løper.getRegNr() != regnr){
            løper = løper.neste;
        }
        return løper;
    }

    public boolean fjernBåt( Båt fjern ){
        if( første == null )
            return false;

        if( første == fjern ){
            første = første.neste;
            return true;
        }

        Båt løper = første;

        while( løper.neste != null  ){
            if(løper.neste == fjern ){
                løper.neste = løper.neste.neste;
                return true;
            }
            else
                løper = løper.neste;
        }
        return false;
    }

    public String toString(){
        String ut = "";
        Båt løper = første;

        while ( løper != null ){
            ut += løper.toString() + "\n";
            løper = løper.neste;
        }

        if ( !ut.equals( "" ) )
            return ut;
        else
            return "Ingen båter i registeret!";
    }
}
