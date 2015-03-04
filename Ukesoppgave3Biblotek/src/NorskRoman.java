/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredr_000
 */
public class NorskRoman extends Roman {
    
    private String målform;
    
    public NorskRoman(){
        
    }
    public NorskRoman(String forfatter, String tittel, int sideantall, double pris, String sjanger, String målform){
        super(forfatter, tittel, sideantall, pris, sjanger);
        this.målform = målform;
    }

    public String getMålform() {
        return målform;
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Målform: " + målform;
    }
    
    
}
