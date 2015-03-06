/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredr_000
 */
import java.io.*;

public class NorskRoman extends Roman {
    
    private String maalform;
    
    public NorskRoman(){
        
    }
    public NorskRoman(String forfatter, String tittel, int sideantall, double pris, String sjanger, String målform){
        super(forfatter, tittel, sideantall, pris, sjanger);
        this.maalform = målform;
    }

    public String getMålform() {
        return maalform;
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Målform: " + maalform;
    }
    
    @Override
    public boolean lesObjektFraFil( DataInputStream inputFil ){ // Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
        try{    
            super.lesObjektFraFil(inputFil);
            maalform = inputFil.readUTF();
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
    }// end of method lesObjektFraFil

    @Override
    public void skrivObjektTilFil( DataOutputStream outputFil ) throws IOException{ // Skriver datafeltenes verdier til fil. >
            outputFil.writeUTF("NorskRoman");
            super.skrivObjektTilFil(outputFil);
            outputFil.writeUTF(maalform);
    } // end of method skrivObjektTilFil
}// end of class NorskRoman
