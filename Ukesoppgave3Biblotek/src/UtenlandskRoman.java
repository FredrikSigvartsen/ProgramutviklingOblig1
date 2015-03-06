
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredr_000
 */
public class UtenlandskRoman extends Roman {
    
    private String språk;
    
    public UtenlandskRoman(){
        
    }
    public UtenlandskRoman(String forfatter, String tittel, int sideantall, double pris, String sjanger, String språk){
        super(forfatter, tittel, sideantall, pris, sjanger);
        this.språk = språk;
    }

    public String getSpråk() {
        return språk;
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Språk: " + språk;
    } // end of method toString()
    
    @Override
    public boolean lesObjektFraFil( DataInputStream inputFil ){ //  Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. 
        try{    
            super.lesObjektFraFil(inputFil);
            språk = inputFil.readUTF();
        }
        catch ( FileNotFoundException fnfe ){
            return false;
        }
        catch ( EOFException eofe ){
            return false;
        }
        catch ( IOException ioe ){
            return false;
        }
    return true;
    }// end of method lesObjektFraFil()

    @Override
    public void skrivObjektTilFil( DataOutputStream outputFil ) throws IOException{ // Skriver datafeltenes verdier til fil. 
            outputFil.writeUTF("UtenlandskRoman");
            super.skrivObjektTilFil(outputFil);
            outputFil.writeUTF(språk);
    }// end of method skrivObjektTilFil()    
}
