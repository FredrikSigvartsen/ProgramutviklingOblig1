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
          bøker.append("\n" + løper.toString() + "\n\n -----------------------------------");
          løper = løper.neste;
      }
  }
}