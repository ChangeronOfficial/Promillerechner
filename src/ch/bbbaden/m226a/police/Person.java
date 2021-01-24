package ch.bbbaden.m226a.police;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Person {
    public static final int MAENNLICH = 0;
    public static final int WEIBLICH = 1;
    private static final double ABBAU_WARTEZEIT_STUNDEN = 1.0;
    private static final double ABBAU_PRO_STUNDE = 0.1;
    private static final double ANTEIL_WASSER_IM_BLUT = 0.8;
    private static final double DICHTE_BLUT_GRAMM_PRO_CCM = 1.055;

    private double koerpermasse;
    private double koerpergroesseInCm;
    private Date geburtsdatum;
    private int geschlecht;
    private double alkoholPromille = 0.0;

    public Person(double koerpermasse, double koerpergroesseInCm, Date geburtsdatum, int geschlecht){
        this.koerpermasse = koerpermasse;
        this.koerpergroesseInCm = koerpergroesseInCm;
        this.geburtsdatum=geburtsdatum;
        this.geschlecht = geschlecht;
    }
    private double getAlterInJahre(){
        final LocalDate jetzt = LocalDate.now();
        final LocalDate localGeburtsdatum = geburtsdatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        final Period period = Period.between(localGeburtsdatum, jetzt);
        final long days = ChronoUnit.DAYS.between(localGeburtsdatum, jetzt);
        return days/365.0;
    }
    public void trinke(AlkoholischesGetraenk alkoholischesGetraenk){
        double alkoholkonzentation = (ANTEIL_WASSER_IM_BLUT* alkoholischesGetraenk.getAlkoholMasselnGramm())/(DICHTE_BLUT_GRAMM_PRO_CCM * getGKW());
        double stundenSeitEinahmen = alkoholischesGetraenk.getStundenSeitEinahme(new Date());
        stundenSeitEinahmen -= ABBAU_WARTEZEIT_STUNDEN;
        if (stundenSeitEinahmen > 0) {
            alkoholkonzentation = alkoholkonzentation - stundenSeitEinahmen * ABBAU_PRO_STUNDE;
        }
        if (alkoholkonzentation > 0 ){
            alkoholPromille = alkoholkonzentation;
        }
    }

    public double getAlkoholPromille() {
        return alkoholPromille;
    }

    private double getGKW(){
        if (geschlecht == MAENNLICH){
            return 2.447 - 0.09516 * getAlterInJahre() + 0.1074 * koerpergroesseInCm + 0.3362 * koerpermasse;
        } else if (geschlecht == WEIBLICH){
            return 0.203 - 0.07 * getAlterInJahre() + 0.1069 * koerpergroesseInCm + 0.2466 * koerpermasse;
        }
        return 0;
    }
}
