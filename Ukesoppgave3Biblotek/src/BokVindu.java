/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredr_000
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



// Vinduklasse for bokregisteret. Registrerer bøker inn til et arkiv. F.eks biblotek. 
public class BokVindu extends JFrame {
    
    private JTextField forfatterFelt, tittelFelt, sideAntFelt, prisFelt, fagomraadeFelt, 
            skolefagFelt, klassetrinnFelt, sjangerFelt, maalFormFelt, spraakFelt;
    
    private JButton registrerFagbok, registrerSkolebok, registrerNorskRoman, 
            registrerUtenlandskRoman, visBokregister;
    private JTextArea utskriftsomraade;
    private Bokregister bokRegisteret;
    private String filen = null;
    
        public BokVindu(Bokregister b){ // Bruker bokregister som vi definerer i driverklassen
            
            super("Bokarkiv");
            bokRegisteret = b;
            
          
            
            // Tekstfeltene, inputfelt for brukeren
            forfatterFelt = new JTextField(35); 
            tittelFelt = new JTextField(35);
            sideAntFelt = new JTextField(15);
            prisFelt = new JTextField(15);
            fagomraadeFelt = new JTextField(35);
            skolefagFelt = new JTextField(35);
            klassetrinnFelt = new JTextField(5);
            sjangerFelt = new JTextField(20);
            maalFormFelt = new JTextField(20);
            spraakFelt = new JTextField(25);
            
            // Knappene
            registrerFagbok = new JButton("Registrer fagbok");
            registrerSkolebok = new JButton("Registrer skolebok");
            registrerNorskRoman = new JButton("Registrer norsk roman");
            registrerUtenlandskRoman = new JButton("Registrer utenlandsk roman");
            visBokregister = new JButton("Vis bokregister");
            utskriftsomraade = new JTextArea(15,40);
            
            
            Container c = getContentPane();
            //Legger til i vinduet
            c.setLayout( new FlowLayout());
            c.add( new JLabel("Forfatter: ")); 
            c.add( forfatterFelt);             
            c.add( new JLabel("Tittel: "));
            c.add( tittelFelt);
            c.add( new JLabel("Sideantall: "));
            c.add( sideAntFelt);
            c.add( new JLabel("Pris: "));
            c.add( prisFelt);
            c.add( new JLabel("Fagområde: "));
            c.add( fagomraadeFelt);
            c.add( new JLabel("Skolefag: "));
            c.add( skolefagFelt);
            c.add( new JLabel("Klassetrinn: "));
            c.add( klassetrinnFelt);
            c.add( new JLabel("Sjanger: "));
            c.add( sjangerFelt);
            c.add( new JLabel("Målform(b = bokmål, n = nynorsk): "));
            c.add( maalFormFelt);
            c.add( new JLabel("    Språk:"));
            c.add( spraakFelt);
            
            c.add(registrerFagbok);
            c.add(registrerSkolebok);
            c.add(registrerNorskRoman);
            c.add(registrerUtenlandskRoman);
            c.add(visBokregister);
            c.add(utskriftsomraade);
            c.add( new JScrollPane(utskriftsomraade));
            
            BokLytter lytter = new BokLytter();
            
            registrerFagbok.addActionListener(lytter);
            registrerSkolebok.addActionListener(lytter);
            registrerNorskRoman.addActionListener(lytter);
            registrerUtenlandskRoman.addActionListener(lytter);
            visBokregister.addActionListener(lytter);
            
            utskriftsomraade.setEditable(false);
            setVisible(true);
            setSize(500,650);
            filen = "src/Bokregister.data";
            lesFraFil();
            
        } // end of constructor
    
        public void skrivTilFil(){
            
            bokRegisteret.skrivTilFil(filen);
        } // end of method skrivTilFil()

        public void lesFraFil(){
            bokRegisteret.lesFraFil( filen );
            visRegister();
        }// end of method lesFraFil()
        
        public void nyFagbok(){
            if(!sjekkFelter())
                return;
            
            if(fagomraadeFelt.getText().trim().equals("")){
                utskriftsomraade.setText("Fyll inn hva slags fagområde fagboken tilhører.");
                return;
            }
            String forfatter = forfatterFelt.getText();
            String tittel = tittelFelt.getText();
            String fagomraade = fagomraadeFelt.getText();
            
            try{
            int sideAntall = Integer.parseInt(sideAntFelt.getText());
            double pris = Double.parseDouble(prisFelt.getText());
            
            bokRegisteret.settInn( new Fagbok(forfatter, tittel, sideAntall, pris, fagomraade));
            visMelding( "Fagboken er registrert");
            slettFelter();
            }
            catch(NumberFormatException nfe){
                visMelding("Feil tallformat");
            }
        } // end of method nyFagbok()
        
        public void nySkolebok(){
            if(!sjekkFelter())
                return;
            
            if(klassetrinnFelt.getText().trim().equals("")){
                visMelding("Fyll inn klasstrinn ");
                return;
            }
            else if(skolefagFelt.getText().trim().equals("")){
                visMelding("Fyll inn skolefag ");
                return;
            }
            
            String forfatter = forfatterFelt.getText();
            String tittel = tittelFelt.getText();
            String skolefag = skolefagFelt.getText();
            
            try{
            int sideAntall = Integer.parseInt(sideAntFelt.getText());
            double pris = Double.parseDouble(prisFelt.getText());
            int klassetrinn = Integer.parseInt(klassetrinnFelt.getText());
            bokRegisteret.settInn( new Skolebok(forfatter, tittel, sideAntall, pris, klassetrinn, skolefag));
            visMelding( "Skoleboken er registrert");
            slettFelter();
            }
            catch(NumberFormatException nfe){
                visMelding("Feil tallformat");
            }
            
        } // end of method nySkolebok()
        
        public void nyNorskRoman(){
            if(!sjekkFelter())
                return;
            
            if(sjangerFelt.getText().trim().equals("")){
                visMelding("Fyll inn sjanger ");
                return;
            }
            else if(maalFormFelt.getText().trim().equals("")){
                visMelding("Fyll inn målfrom ");
                return;
            }
            
            String forfatter = forfatterFelt.getText();
            String tittel = tittelFelt.getText();
            String maalform = maalFormFelt.getText();
            String sjanger = sjangerFelt.getText();
            
            try{
            int sideAntall = Integer.parseInt(sideAntFelt.getText());
            double pris = Double.parseDouble(prisFelt.getText());
            if(maalform.equalsIgnoreCase("b"))
                maalform = "Bokmål";
            else if(maalform.equalsIgnoreCase("n"))
                maalform = "Nynorsk";
            else{
                visMelding( "Ugyldig målform. Velg b for bokmål, eller n for nynorsk. ");
                maalFormFelt.setText("");
                return;
            }
            bokRegisteret.settInn( new NorskRoman(forfatter, tittel, sideAntall, pris, sjanger, maalform));
            visMelding( "Norskromanen er registrert");
            slettFelter();
            }
            
            catch(NumberFormatException nfe){
                visMelding("Feil tallformat");
            }
        
        } // end of method nyNorskRoman()
        
        public void nyUtenlandskRoman(){
              if(!sjekkFelter())
                return;
            
            if(sjangerFelt.getText().trim().equals("")){
                visMelding("Fyll inn sjanger ");
                return;
            }
            else if(spraakFelt.getText().trim().equals("")){
                visMelding("Fyll inn språk ");
                return;
            }
            
            String forfatter = forfatterFelt.getText();
            String tittel = tittelFelt.getText();
            String sjanger = sjangerFelt.getText();
            String spraak = spraakFelt.getText();
            
            try{
            int sideAntall = Integer.parseInt(sideAntFelt.getText());
            double pris = Double.parseDouble(prisFelt.getText());
            bokRegisteret.settInn( new UtenlandskRoman(forfatter, tittel, sideAntall, pris, sjanger, spraak));
            visMelding( "Utenlandskromanen er registrert");
            slettFelter();
            }
            catch(NumberFormatException nfe){
                visMelding("Feil tallformat");
            }
        } // end of method nyUtenlandskRoman()
        
        public void visRegister(){
         bokRegisteret.skrivListe(utskriftsomraade);
        } // end of method visRegister()
        
        
        public boolean sjekkFelter(){
          if(forfatterFelt.getText().trim().equals("")){
              visMelding("OBS! Fyll inn navn på forfatter");
              return false;
          }
          else if(tittelFelt.getText().trim().equals("")){
              visMelding("OBS! Fyll inn tittel på boken");
              return false;
          }
          else if(sideAntFelt.getText().trim().equals("")){
              visMelding("OBS! Fyll inn antall sider på boken");
              return false;
          }
          else if(prisFelt.getText().trim().equals("")){
              visMelding("OBS! Fyll inn bokens pris");
              return false;
          }
          return true;
        } // end of method sjekkFelter()
        public void visMelding(String m){
            JOptionPane.showMessageDialog(null, m); 
        }
        
        private void slettFelter(){
            
            forfatterFelt.setText( "" );
            tittelFelt.setText( "" );
            sideAntFelt.setText( "" );
            prisFelt.setText( "" );
            fagomraadeFelt.setText( "" );
            skolefagFelt.setText( "" );
            klassetrinnFelt.setText( "" );
            sjangerFelt.setText( "" );
            maalFormFelt.setText( "" );
            spraakFelt.setText( "" );
        }
        
    private class BokLytter implements ActionListener{
        
        public void actionPerformed( ActionEvent e){
            if(e.getSource() == registrerFagbok)
                nyFagbok();
            else if( e.getSource() == registrerSkolebok)
                nySkolebok();
            else if(e.getSource() == registrerNorskRoman)
                nyNorskRoman();
            else if(e.getSource() == registrerUtenlandskRoman)
                nyUtenlandskRoman();
            else if(e.getSource() == visBokregister)
                visRegister();
        }
    } // end of ActionListener method
    
}// end of class BokVindu 
