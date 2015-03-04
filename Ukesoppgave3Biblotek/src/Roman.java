/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredr_000
 */
public abstract class Roman extends Bok {
    
    private String sjanger;
    
    public Roman(){
        
    }
    
    public Roman(String forfatter, String tittel, int sideantall, double pris, String sjanger){
        
        super( forfatter, tittel, sideantall, pris);
        this.sjanger = sjanger;
    }

    public String getSjanger() {
        return sjanger;
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Sjanger: " + sjanger;
    }
    
}
