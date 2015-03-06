/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredr_000
 */
import java.text.DecimalFormat;
import java.io.*;
/**
 *
 * @author fredr_000
 */
public abstract class Bok {
    
    private String forfatter;
    private String tittel;
    private int sideantall;
    private double pris;
    private DecimalFormat tilDesimal;
    Bok neste;
    
    public Bok(){
        
    }
    
    public Bok(String forfatter, String tittel, int sideantall, double pris){
        
        this.forfatter = forfatter;
        this.tittel = tittel; 
        this.sideantall = sideantall;
        this.pris = pris; 
        tilDesimal = new DecimalFormat("00.00");
        neste = null;
    }

    public String getForfatter() {
        return forfatter;
    }

    public String getTittel() {
        return tittel;
    }

    public int getSideantall() {
        return sideantall;
    }

    public double getPris() {
        return pris;
    }
    
    @Override
    public String toString(){
        return 
                    "   Forfatter: " + forfatter 
                + "\n   Tittel: " + tittel
                + "\n   Antall sider: " + sideantall + 
                  "\n   Pris: " + pris;
    } // end of toString()

     public boolean lesObjektFraFil( DataInputStream inputFil ) {  // Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >

        try{    
            forfatter = inputFil.readUTF();
            tittel = inputFil.readUTF();
            sideantall = inputFil.readInt();
            pris = inputFil.readDouble();
        }// end of try 
      
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
    }  // end of method lesObjektFraFil


    public void skrivObjektTilFil( DataOutputStream outputFil ) throws IOException{  //  Skriver datafeltenes verdier til fil. >
        outputFil.writeUTF( forfatter );
        outputFil.writeUTF( tittel );
        outputFil.writeInt( sideantall );
        outputFil.writeDouble( pris );
    }
}
