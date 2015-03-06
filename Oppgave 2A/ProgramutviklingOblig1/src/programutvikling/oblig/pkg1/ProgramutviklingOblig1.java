package programutvikling.oblig.pkg1;
import java.awt.event.*;

public class ProgramutviklingOblig1 {

    public static void main(String[] args) {
        
        final Brukergrensesnitt vindu = new Brukergrensesnitt();

        vindu.addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    vindu.skrivTilFil();
                    System.exit( 0 );
                }
        } );
    }
}
