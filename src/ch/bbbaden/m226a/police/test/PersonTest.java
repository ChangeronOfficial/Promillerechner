package ch.bbbaden.m226a.police.test;

import ch.bbbaden.m226a.police.AlkoholischesGetraenk;
import ch.bbbaden.m226a.police.Person;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void getAlkoholPromille() {
        //Arrange
        double koerpermasse = 88;
        double koerpergroesseInCm = 176;
        int age = 45;

        Date date = new Date();
        long geburtsdatum = date.getTime() - age * 31536000000l;
        double HoursSinceDrinking = 2.5;
        long getrunkenAm = (long) (date.getTime() - HoursSinceDrinking * 3600000d);

        int volumenInMilliLiter = 1200;
        double alkoholgehalt = AlkoholischesGetraenk.BIER_ALKOHOLGEHALT;

        //Act
        Person frau = new Person(koerpermasse, koerpergroesseInCm, new Date(geburtsdatum), Person.WEIBLICH);
        Person man = new Person(koerpermasse, koerpergroesseInCm, new Date(geburtsdatum), Person.MAENNLICH);

        //Assert
        AlkoholischesGetraenk alkoholischesGetraenk1 = new AlkoholischesGetraenk(volumenInMilliLiter, alkoholgehalt, new Date());
        frau.trinke(alkoholischesGetraenk1);
        man.trinke(alkoholischesGetraenk1);

        assertEquals(0.9688540911037222, frau.getAlkoholPromille(), 0.001);
        assertEquals(0.7801912053596537, man.getAlkoholPromille(), 0.001);

        AlkoholischesGetraenk alkoholischesGetraenk2 = new AlkoholischesGetraenk(volumenInMilliLiter, alkoholgehalt, new Date(getrunkenAm));
        frau.trinke(alkoholischesGetraenk2);
        man.trinke(alkoholischesGetraenk2);

        assertEquals(0.8188540911037222, frau.getAlkoholPromille(), 0.001);
        assertEquals(0.6301912053596537, man.getAlkoholPromille(), 0.001);
    }
}