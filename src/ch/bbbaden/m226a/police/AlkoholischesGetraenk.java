package ch.bbbaden.m226a.police;

import java.util.Date;

public class AlkoholischesGetraenk {
    public static final double BIER_ALKOHOLGEHALT = 0.05;
    public static final double WEIN_ALKOHOLGEHALT = 0.10;
    public static final double SCHNAPS_ALKOHOLGEHALT = 0.40;
    private static final double DICHTE_ALKOHOL = 0.8;

    private int volumenInMilliLiter;
    private double alkoholgehalt;
    private Date getrunkenAm;

    public AlkoholischesGetraenk(int volumenInMilliLiter, double alkoholgehalt, Date getrunkenAm){
        this.volumenInMilliLiter = volumenInMilliLiter;
        this.alkoholgehalt = alkoholgehalt;
        this.getrunkenAm = getrunkenAm;
    }
    public double getStundenSeitEinahme(Date jetzt){
        return (jetzt.getTime() - getrunkenAm.getTime())/ 3600000d;
    }
    public double getAlkoholMasselnGramm() {
        return volumenInMilliLiter * alkoholgehalt * DICHTE_ALKOHOL;
    }
}
