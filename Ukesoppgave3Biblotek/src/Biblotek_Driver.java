



import java.awt.event.*;
import javax.swing.*;

public class Biblotek_Driver
{
  public static void main( String[] args )
  {
    Bokregister hioa = new Bokregister();
    BokVindu v = new BokVindu(hioa);
            v.addWindowListener(
      new WindowAdapter() {
        public void windowClosing( WindowEvent e )
        {

          System.exit( 0 );
        }
      } );
  }
}