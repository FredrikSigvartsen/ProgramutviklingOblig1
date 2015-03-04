/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fredr_000
 */
public class UtenlandskRoman extends Roman {
    
    private String språk;
    
    public UtenlandskRoman(){
        
    }
    public UtenlandskRoman(String forfatter, String tittel, int sideantall, double pris, String sjanger, String språk){
        super(forfatter, tittel, sideantall, pris, sjanger);
        this.språk = språk;
    }

    public String getSpråk() {
        return språk;
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Språk: " + språk;
    }
    
    
}
