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
  
  
  public void skrivTilFil( String filOutput ) {
      
      
     try( DataOutputStream fil = new DataOutputStream(
			                    new FileOutputStream( filOutput ) ) ) {
      
      Bok løper = første;
      
      while ( løper != null ){
        løper.skrivObjektTilFil( fil );
        løper = løper.neste;
      } // end of while
    }// end of try 
    catch ( IOException ioe ){
      visMelding("Kunne ikke skrive til fil.");
    }
  } // end of method skrivTilFil()

  public void lesFraFil( String filInput ){

    try( DataInputStream fil = new DataInputStream(
                                            new FileInputStream( filInput ) ) )
    {
     
    Bok ny = null;
    String bok = "";
    
    while(true){ 
        
        bok = fil.readUTF();
        
        //\\ HVIS SKOLEBOK
        if( bok.equalsIgnoreCase("Skolebok"))                
                ny = new Skolebok();
        //\\ HVIS FAGBOK 
        else if( bok.equalsIgnoreCase("Fagbok"))         
             ny = new Fagbok();
        
        
        //\\ HVIS NORSK ROMAN
        else if( bok.equalsIgnoreCase("NorskRoman"))         
            ny = new NorskRoman();
        
         //\\ HVIS UTENLANDSK ROMAN
        else if( bok.equalsIgnoreCase("UtenlandskRoman"))   
            ny = new UtenlandskRoman();
        
        else return;
        
        ny.lesObjektFraFil(fil);
                settInn(ny);
        }// end of while 
    }// end of try
   
    catch(EOFException e){
      return;
    }
    catch ( IOException ioe ){
      visMelding( "Får ikke lest fil " + filInput );
      return;
    }
    catch( Exception e){
      visMelding("En type for exception");
      return;
    }
    
  }// end of method lesFraFil  
  
  
  public void visMelding(String m){
            JOptionPane.showMessageDialog(null, m); 
        }
}