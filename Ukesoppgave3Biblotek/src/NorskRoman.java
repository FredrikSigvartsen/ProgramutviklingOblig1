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
    public boolean lesObjektFraFil( DataInputStream inputFil )throws IOException{
         //< Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
        while(true){
        maalform = inputFil.readUTF();    
        super.lesObjektFraFil(inputFil);
        }
    }

    @Override
    public void skrivObjektTilFil( DataOutputStream outputFil ) throws IOException{
         //< Skriver datafeltenes verdier til fil. >
        
        while(true){
            outputFil.writeUTF(maalform);
            super.skrivObjektTilFil(outputFil);
        }
    }
    
}
