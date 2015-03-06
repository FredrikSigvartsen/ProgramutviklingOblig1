package programutvikling.oblig.pkg1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Brukergrensesnitt extends JFrame {

    private JTextField medlemNr, registreringsNr, navn, adresse, merke, årsmodell, lengde, motor, farge;
    private JLabel labelMedlemNr, labelRegistreringsNr, labelNavn, labelAdresse, labelMerke, labelÅrsmodell, labelLengde, labelMotor, labelFarge;
    private JTextArea area;
    private JButton regEier, regBåt, skrivReg, slettBåt, slettEier, skrivEier, regEierskifte;
    private JRadioButton eier, båt, slettB, slettE, eierskifte, alt;
    private ButtonGroup group;
    private Lytter lytter;
    private Båteierregister register;

    public Brukergrensesnitt() {

        super("Båtregister");
        lesFraFil();
        this.register = register;
        
        lytter = new Lytter();

        medlemNr = new JTextField(3);
        registreringsNr = new JTextField(5);
        navn = new JTextField(20);
        adresse = new JTextField(20);
        merke = new JTextField(10);
        årsmodell = new JTextField(4);
        lengde = new JTextField(6);
        motor = new JTextField(6);
        farge = new JTextField(10);
        
        area = new JTextArea(12,25);
        area.setEditable(false);

        labelMedlemNr = new JLabel("MedlemsNr: ");
        labelRegistreringsNr = new JLabel("RegistreringsNr: ");
        labelNavn = new JLabel("Navn: ");
        labelAdresse = new JLabel("Adresse: ");
        labelMerke = new JLabel("Merke: ");
        labelÅrsmodell = new JLabel("Årsmodell: ");
        labelLengde = new JLabel("Lengde: ");
        labelMotor = new JLabel("Hestekrefter: ");
        labelFarge = new JLabel("Farge: ");
        
        eier = new JRadioButton("Registrer eier");
        eier.addActionListener(lytter);
        båt = new JRadioButton("Registrer båt");
        båt.addActionListener(lytter);
        slettB = new JRadioButton("Slett båt");
        slettB.addActionListener(lytter);
        slettE = new JRadioButton("Slett eier");
        slettE.addActionListener(lytter);
        eierskifte = new JRadioButton("Eierskifte");
        eierskifte.addActionListener(lytter);
        alt = new JRadioButton("Aktiver alle knapper");
        alt.addActionListener(lytter);
        alt.setSelected(true);
        group = new ButtonGroup();
        group.add(eier);
        group.add(båt);
        group.add(slettB);
        group.add(slettE);
        group.add(eierskifte);
        group.add(alt);
        
        regEier = new JButton("Registrer eier");
        regEier.addActionListener(lytter);
        regBåt = new JButton("Registrer båt");
        regBåt.addActionListener(lytter);
        skrivReg = new JButton("Vis register");
        skrivReg.addActionListener(lytter);
        slettBåt = new JButton("Slett båt");
        slettBåt.addActionListener(lytter);
        slettEier = new JButton("Slett eier");
        slettEier.addActionListener(lytter);
        skrivEier = new JButton("Eier info");
        skrivEier.addActionListener(lytter);
        regEierskifte = new JButton("Registrer eierskifte");
        regEierskifte.addActionListener(lytter);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(eier);
        c.add(båt);
        c.add(slettB);
        c.add(slettE);
        c.add(eierskifte);
        c.add(alt);
        c.add(labelNavn);
        c.add(navn);
        c.add(labelAdresse);
        c.add(adresse);
        c.add(labelMedlemNr);
        c.add(medlemNr);
        c.add(labelRegistreringsNr);
        c.add(registreringsNr);
        c.add(labelMerke);
        c.add(merke);
        c.add(labelÅrsmodell);
        c.add(årsmodell);
        c.add(labelLengde);
        c.add(lengde);
        c.add(labelMotor);
        c.add(motor);
        c.add(labelFarge);
        c.add(farge);
        c.add(new JScrollPane(area));
        c.add(regEier);
        c.add(regBåt);
        c.add(regEierskifte);
        c.add(slettBåt);
        c.add(slettEier);
        c.add(skrivReg);
        c.add(skrivEier);

        setSize( 330, 550 );
        setVisible( true );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void skrivTilFil() {
        
        try(ObjectOutputStream utfil = new ObjectOutputStream(
                new FileOutputStream("src/liste.data")))
        {
            utfil.writeObject(register);
            utfil.writeInt(Eier.getNr());
            utfil.writeInt(Båt.getNr());
        }
        catch(NotSerializableException nse) {
            visFeilmelding("nse exception");
        }
        catch(IOException ioe) {
            visFeilmelding("ioe exception");
        }
    }
    
    public void lesFraFil() {
        try(ObjectInputStream innfil = new ObjectInputStream(
                new FileInputStream("src/liste.data")))
        {
            register = (Båteierregister) innfil.readObject();
            Eier.setNr(innfil.readInt());
            Båt.setNr(innfil.readInt());
        }
        catch(ClassNotFoundException cnfe) {
            area.setText(cnfe.getMessage());
            area.append("\nOppretter tom liste.");
            register = new Båteierregister();
        }
        catch(FileNotFoundException fnfe) {
            area.setText(fnfe.getMessage());
            area.setText("Finner ikke datafil, oppretter tom liste");
            register = new Båteierregister();
        }
        catch(IOException ioe) {
            area.setText(ioe.getMessage());
            area.setText("Innlesningsfeil. Oppretter tom liste.");
            register = new Båteierregister();
        }
    }
    
    public void registrerEier() {
        String navn = this.navn.getText();
        String adresse = this.adresse.getText();
        Eier ny = new Eier(navn, adresse);
        register.settInn(ny);
        area.setText("Ny eier registrert med medlemsNr: " + ny.getMedlemsNr());
    }
    
    public void registrerBåt() {
        String merke = this.merke.getText();
        int årsmodell = Integer.parseInt(this.årsmodell.getText());
        int lengde = Integer.parseInt(this.lengde.getText());
        int motor = Integer.parseInt(this.motor.getText());
        String farge = this.farge.getText();
        int medlemsNr = Integer.parseInt(this.medlemNr.getText());
        
        Båt ny = new Båt(merke, årsmodell, lengde, motor, farge);
        Eier båteier = register.finnEier(medlemsNr);
        if(båteier != null) {
            båteier.setBåt(ny);
            area.setText("Båten er registrert med regNr: " + ny.getRegNr() + 
                         "\nEieren har medlemsNr: " + medlemsNr);
        } else {
            area.setText("Eier med medlemsNr: " + medlemsNr + " ble ikke funnet i registeret");
        }
        
    }
    
    public void slettEier() {
        int medlemsNr = Integer.parseInt(this.medlemNr.getText());
        Eier eier = register.finnEier(medlemsNr);
        if(eier == null)
            area.setText("Fant ingen eier med medlemsNr: " + medlemsNr);
        else
            area.setText(register.fjernEier(eier));
    }
    
    public void slettBåt() {
        int regNr = Integer.parseInt(this.registreringsNr.getText());
        Båt båt = register.finnBåt(regNr);
        boolean ok;
        if(båt == null) {
            area.setText("Fant ingen båt med regNr: " + regNr);
        } else {
            ok = register.fjernBåt(båt);
            if(ok)
                area.setText("Båten ble slettet");
            else
                area.setText("Båten ble ikke slettet");                      
        }
    }
    
    public void registrerEierskifte() {
        int registreringsNr = Integer.parseInt(this.registreringsNr.getText());
        int medlemsNr = Integer.parseInt(this.medlemNr.getText());
        area.setText(register.eierSkifte(registreringsNr, medlemsNr));
    }
    
    public void visRegister() {
        area.setText(register.visRegister());
    }
    
    public void visEierInfo() {
        int medlemsNr = Integer.parseInt(this.medlemNr.getText());
        area.setText(register.eierInfo(medlemsNr));
    }
    
    public void visFeilmelding(String tekst) {
        JOptionPane.showMessageDialog(this, tekst, "Problem", JOptionPane.ERROR_MESSAGE);
    }
    
    public void blankUtAlleFelt() {
        navn.setText("");
        adresse.setText("");
        medlemNr.setText("");
        registreringsNr.setText("");
        merke.setText("");
        årsmodell.setText("");
        lengde.setText("");
        motor.setText("");
        farge.setText("");
    }

    private class Lytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == regEier) {
                registrerEier();
                blankUtAlleFelt();
            } else if (e.getSource() == regBåt) {
                registrerBåt();
                blankUtAlleFelt();
            } else if (e.getSource() == skrivReg) {
                visRegister();
                blankUtAlleFelt();
            } else if (e.getSource() == slettBåt) {
                slettBåt();
                blankUtAlleFelt();
            } else if (e.getSource() == slettEier) {
                slettEier();
                blankUtAlleFelt();
            } else if (e.getSource() == skrivEier) {
                visEierInfo();
                blankUtAlleFelt();
            } else if (e.getSource() == regEierskifte) {
                registrerEierskifte();
                blankUtAlleFelt();
            } else if (e.getSource() == eier) {
                navn.setEditable(true);
                adresse.setEditable(true);
                medlemNr.setEditable(false);
                registreringsNr.setEditable(false);
                merke.setEditable(false);
                årsmodell.setEditable(false);
                lengde.setEditable(false);
                farge.setEditable(false);
                motor.setEditable(false);
                
                regEier.setEnabled(true);
                regBåt.setEnabled(false);
                regEierskifte.setEnabled(false);
                skrivReg.setEnabled(false);
                skrivEier.setEnabled(false);
                slettEier.setEnabled(false);
                slettBåt.setEnabled(false);
            } else if (e.getSource() == båt) {
                navn.setEditable(false);
                adresse.setEditable(false);
                medlemNr.setEditable(true);
                registreringsNr.setEditable(false);
                merke.setEditable(true);
                årsmodell.setEditable(true);
                lengde.setEditable(true);
                farge.setEditable(true);
                motor.setEditable(true);
                
                regEier.setEnabled(false);
                regBåt.setEnabled(true);
                regEierskifte.setEnabled(false);
                skrivReg.setEnabled(false);
                skrivEier.setEnabled(false);
                slettEier.setEnabled(false);
                slettBåt.setEnabled(false);
            } else if (e.getSource() == slettB) {
                navn.setEditable(false);
                adresse.setEditable(false);
                medlemNr.setEditable(false);
                registreringsNr.setEditable(true);
                merke.setEditable(false);
                årsmodell.setEditable(false);
                lengde.setEditable(false);
                farge.setEditable(false);
                motor.setEditable(false);
                
                regEier.setEnabled(false);
                regBåt.setEnabled(false);
                regEierskifte.setEnabled(false);
                skrivReg.setEnabled(false);
                skrivEier.setEnabled(false);
                slettEier.setEnabled(false);
                slettBåt.setEnabled(true);
            } else if (e.getSource() == slettE) {
                navn.setEditable(false);
                adresse.setEditable(false);
                medlemNr.setEditable(true);
                registreringsNr.setEditable(false);
                merke.setEditable(false);
                årsmodell.setEditable(false);
                lengde.setEditable(false);
                farge.setEditable(false);
                motor.setEditable(false);
                
                regEier.setEnabled(false);
                regBåt.setEnabled(false);
                regEierskifte.setEnabled(false);
                skrivReg.setEnabled(false);
                skrivEier.setEnabled(false);
                slettEier.setEnabled(true);
                slettBåt.setEnabled(false);
            } else if (e.getSource() == eierskifte) {
                navn.setEditable(false);
                adresse.setEditable(false);
                medlemNr.setEditable(true);
                registreringsNr.setEditable(true);
                merke.setEditable(false);
                årsmodell.setEditable(false);
                lengde.setEditable(false);
                farge.setEditable(false);
                motor.setEditable(false);
                
                regEier.setEnabled(false);
                regBåt.setEnabled(false);
                regEierskifte.setEnabled(true);
                skrivReg.setEnabled(false);
                skrivEier.setEnabled(false);
                slettEier.setEnabled(false);
                slettBåt.setEnabled(false);
            } else if (e.getSource() == alt) {
                navn.setEditable(true);
                adresse.setEditable(true);
                medlemNr.setEditable(true);
                registreringsNr.setEditable(true);
                merke.setEditable(true);
                årsmodell.setEditable(true);
                lengde.setEditable(true);
                farge.setEditable(true);
                motor.setEditable(true);
                
                regEier.setEnabled(true);
                regBåt.setEnabled(true);
                regEierskifte.setEnabled(true);
                skrivReg.setEnabled(true);
                skrivEier.setEnabled(true);
                slettEier.setEnabled(true);
                slettBåt.setEnabled(true);
            }
        }
    }
}
