/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredr_000
 */
public class Fagbok extends Bok {
    
    private String fagomraade;
    
    public Fagbok(){
        
    }
    
    public Fagbok(String forfatter, String tittel, int sideantall, double pris, String fagomraade){
        super(forfatter, tittel, sideantall, pris);
        this.fagomraade = fagomraade;
        
    }

    public String getFagomraade() {
        return fagomraade;
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Kategori: Fagbok\n  Fagområde: " + fagomraade;
    }
    
}
