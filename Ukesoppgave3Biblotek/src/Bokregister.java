/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredr_000
 */
import javax.swing.*;
import java.io.*;

public class Bokregister
{
  private Bok første;

    public Bokregister() {
    }

  //registrerer et bokobjekt
  public void settInn( Bok ny )
  {
    //Setter inn Bok-objektet ny i lista av Bok-objekter.
      Bok løper = første;
      if(ny == null)
          return;
      if(første == null){
          første = ny;
          return;
      }
          
      
      while(løper.neste != null)
          løper = løper.neste;
      
      løper.neste = ny;
  }

  //utskrift av innhold i bokliste
  public void skrivListe( JTextArea bøker )
  {
    //Gjennomløper lista av Bok-objekter og tilføyer i tekstområdet
    //bøker informasjonen som er lagret om hver enkelt bok.
      bøker.setText("");
      if(første == null){
          bøker.setText("Ingen bøker i bibloteket");
      }
      
      Bok løper = første;
      while(løper != null){
          bøker.append("\n" + løper.toString() + "\n\n -----------------------"
                  + "-------------------------");
          løper = løper.neste;
      }
  }
  
  
  public void skrivTilFil( String filOutput ){
      
      
     try( DataOutputStream fil = new DataOutputStream(
			                    new FileOutputStream( filOutput ) ) ) {
      
         Bok løper = første;
      while ( løper != null ){
        løper.skrivObjektTilFil( fil );
        løper = løper.neste;
      }
    }
    catch ( IOException ioe ){
      visMelding("Kunne ikke skrive til fil.");
    }
  }

  public void lesFraFil( String filInput ){

    
    try( DataInputStream fil = new DataInputStream(
                                            new FileInputStream( filInput ) ) ){
      
      String bok = "";
      //Bok løper = første;
      while(true){
      bok = fil.readUTF();
     
        Bok ny = null;
          
        if( bok.equals("Skolebok")){                // HVIS SKOLEBOK
                ny = new Skolebok();
            if(ny.lesObjektFraFil(fil)){
                settInn(ny);
            }
            else
                visMelding("Bok ikke satt inn i registeret, på grunn av lesefeil fra fil.");
        }
        
        else if( bok.equals("Fagbok")){             // HVIS FAGBOK 
            ny = new Fagbok();
            
            if(ny.lesObjektFraFil(fil)){
                System.out.println("NÅ HAR VI LEST BOK:" + bok );
                settInn(ny);
            }
            else
                visMelding("Bok ikke satt inn i registeret, på grunn av lesefeil fra fil.");
        }
        
        else if( bok.equalsIgnoreCase("NorskRoman")){         // HVIS NORSK ROMAN 
            ny = new NorskRoman();
            if(ny.lesObjektFraFil(fil))
            settInn(ny);
            else
                visMelding("Bok ikke satt inn i registeret, på grunn av lesefeil fra fil.");
         }
        else if( bok.equalsIgnoreCase("UtenlandskRoman")){    // HVIS UTENLANDSK ROMAN
            ny = new UtenlandskRoman();
            if(ny.lesObjektFraFil(fil))
                settInn(ny);
            else
                visMelding("Bok ikke satt inn i registeret, på grunn av lesefeil fra fil.");
            
         }
      }    
//    løper = løper.neste;
//      } // end of while
    }// end of try 
    catch(EOFException e){
        System.out.println("Har nå nådd slutten av filen");
        return;
    }
    catch ( IOException ioe ){
      visMelding( "Får ikke lest fil " + filInput );
      return;
    }
    catch( Exception e){
      visMelding("En type for exception");
    }
    
  }// end of method lesFraFil  
  
  
  public void visMelding(String m){
            JOptionPane.showMessageDialog(null, m); 
        }
}