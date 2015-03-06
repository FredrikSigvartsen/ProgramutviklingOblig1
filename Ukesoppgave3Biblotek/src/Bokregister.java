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
  private Bok forste;

    public Bokregister() {
    }

  //registrerer et bokobjekt
  public void settInn( Bok ny ){
    //Setter inn Bok-objektet ny i lista av Bok-objekter.
      Bok loper = forste;
      if(ny == null)
          return;
      if(forste == null){
          forste = ny;
          return;
      }


      while(loper.neste != null)
          loper = loper.neste;

      loper.neste = ny;
  } // end of method settInn()

  //utskrift av innhold i bokliste
  public void skrivListe( JTextArea boker ){  //Gjennomloper lista av Bok-objekter og tilfoyer i tekstområdet
                                              //boker informasjonen som er lagret om hver enkelt bok.
      boker.setText("");
      if(forste == null){
          boker.setText("Ingen boker i bibloteket");
      }

      Bok loper = forste;
      while(loper != null){
          boker.append("\n" + loper.toString() + "\n\n -----------------------"
                  + "-------------------------");
          loper = loper.neste;
      }
  }// end of method skrivListe


  public void skrivTilFil( String filOutput ) {   // skriver hver variabel/felt til fil.


     try( DataOutputStream fil = new DataOutputStream(
			                    new FileOutputStream( filOutput ) ) ) {

      Bok loper = forste;

      while ( loper != null ){
        loper.skrivObjektTilFil( fil );
        loper = loper.neste;
      } // end of while
    }// end of try
    catch ( IOException ioe ){
      visMelding("Kunne ikke skrive til fil.");
    }
  } // end of method skrivTilFil()

  public void lesFraFil( String filInput ){ // Leser fra fil, og oppretter riktig type objekt.

    try( DataInputStream fil = new DataInputStream(
                                            new FileInputStream( filInput ) ) ){
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

        if(ny.lesObjektFraFil(fil))
            settInn(ny);
        else
            visMelding("Feil ved innsetting av bok i registeret. ");
        }// end of while
    }// end of try

    catch(EOFException e){
      return;
    }
    catch ( IOException ioe ){
      visMelding( "Faar ikke lest fil " + filInput );
      return;
    }
    catch( Exception e){
      visMelding("FEIL I PROGRAM!");
      return;
    }

  }// end of method lesFraFil

  public void visMelding(String m){
            JOptionPane.showMessageDialog(null, m);
        } // end of method visMelding()
}