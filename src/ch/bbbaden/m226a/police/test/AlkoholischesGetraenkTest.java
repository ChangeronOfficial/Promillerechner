package ch.bbbaden.m226a.police.test;

import ch.bbbaden.m226a.police.AlkoholischesGetraenk;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AlkoholischesGetraenkTest {

    @Test
    void getStundenSeitEinahme() {


        //Arrange
        double SolutionInHours = 3;
        Date date = new Date();
        long time = date.getTime() - 3 * 3600000;
        //Act
        AlkoholischesGetraenk alkoholischesGetraenk = new AlkoholischesGetraenk(1200, AlkoholischesGetraenk.BIER_ALKOHOLGEHALT , new Date(time));
        double stundenSeitEinahme =  alkoholischesGetraenk.getStundenSeitEinahme(date);
        //Assert
        assertEquals(SolutionInHours, stundenSeitEinahme);
    }

    @Test
    void getAlkoholMasselnGramm() {
        //Arrange
        int volumenInMilliLiter = 1200;
        double alkoholgehalt = AlkoholischesGetraenk.BIER_ALKOHOLGEHALT;
        double DICHTE_ALKOHOL = 0.8;
        //Act
        AlkoholischesGetraenk alkoholischesGetraenk = new AlkoholischesGetraenk(1200, alkoholgehalt, new Date());
        //Assert
        assertEquals(alkoholischesGetraenk.getAlkoholMasselnGramm(), volumenInMilliLiter* alkoholgehalt * DICHTE_ALKOHOL);
    }
}