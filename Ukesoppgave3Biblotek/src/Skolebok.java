
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredr_000
 */
public class Skolebok extends Bok  {
    
    private int klassetrinn;
    private String skolefag;
    
    public Skolebok(){
        
    }
    
    public Skolebok(String forfatter, String tittel, int sideantall, double pris, int klassetrinn, String skolefag){
        super(forfatter, tittel, sideantall, pris);
        this.klassetrinn = klassetrinn;
        this.skolefag = skolefag;
    }

    public int getKlassetrinn() {
        return klassetrinn;
    }

    public String getSkolefag() {
        return skolefag;
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Kategori: Skolebok\n   Klassetrinn: "+ klassetrinn
                + "\n   Skolefag: " + skolefag;
    }
    
    public boolean lesObjektFraFil( DataInputStream inputFil ){
         //< Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
        
        try( DataInputStream fil = new DataInputStream(inputFil ) ){
        inputFil.readUTF();
        skolefag = inputFil.readUTF();
        klassetrinn = inputFil.readInt();
        super.lesObjektFraFil(inputFil);
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
    }

    public void skrivObjektTilFil( DataOutputStream outputFil ) throws IOException{
         //< Skriver datafeltenes verdier til fil. >
        
        
        outputFil.writeUTF("Skolebok");
        outputFil.writeUTF(skolefag);
        outputFil.writeInt( klassetrinn);
        super.skrivObjektTilFil(outputFil);
    }
    
}
