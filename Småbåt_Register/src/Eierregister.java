/**
 * Created by Jens on 03.03.2015.
 */
public class Eierregister {
    private Eier første;

    public Eierregister(){
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

    public String eierInfo(int regnr){
        Eier løper = første;
        Båt båtløper = null;
        while(løper != null && båtløper.getRegNr() != regnr){
            båtløper = båtløper.neste;
        }
        return løper.toString();
    }

    public Eier finnEier(int medlemsnr){
        Eier løper = første;
        while(løper != null && løper.getMedlemsnrnr() != medlemsnr){
            løper = løper.neste;
        }
        return løper;
    }

    public boolean fjernEier(Eier fjern){
        if(!fjern.båt){
            if (første == null)
                return false;

            if (første == fjern) {
                første = første.neste;
                return true;
            }

            Eier løper = første;

            while (løper.neste != null) {
                if (løper.neste == fjern) {
                    løper.neste = løper.neste.neste;
                    return true;
                } else
                    løper = løper.neste;
            }
            return false;
        }
        else
            return false;
    }

    public boolean eierSkifte(int regnr, int medlemsnr, String navn, String adresse){
        Båtregister skifte = null;
        Båt skiftebåt = skifte.finnBåt(regnr);
        Eier eier = finnEier(medlemsnr);
        if(eier == null){
            Eier ny = new Eier(navn, adresse);
            skiftebåt.setEier(ny);
            return true;
        }
        else if(eier != null){
            skiftebåt.setEier(finnEier(medlemsnr));
            return true;
        }
        return false;
    }
}
