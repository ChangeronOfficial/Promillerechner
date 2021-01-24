package ch.bbbaden.m226a.police;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GUI {
    private Person askPersonData(){
        return new Person(askKoerpermasse(), askKoerpergroesseInCm(), askGeburtsdatum(), askGeschlecht());
    }
    private AlkoholischesGetraenk askAlkoholischesGetraenke(Date trinkDatum){
        return new AlkoholischesGetraenk(askVolumenInMilliLiter(), askAlkoholgehalt(), trinkDatum);
    }

    public void promilleRechner(){
        Person person = askPersonData();
        person.trinke(askAlkoholischesGetraenke(askTrinkDatum()));
        postResultat(person.getAlkoholPromille());
    }


    private Date askTrinkDatum() {
        Date trinkDatum = null;
        final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy kk:mm");
        final Date jetzt = new Date();
        do {
            final String eingabe = JOptionPane.showInputDialog(null, "Geben Sie Trinkdatum und -zeit ein.",
                    "Trinkzeit",
                    JOptionPane.QUESTION_MESSAGE);
            if (eingabe == null) { // Cancel
                System.exit(0);
            }
            try {
                trinkDatum = formatter.parse(eingabe);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, eingabe + " ist keine gültige Trinkzeit.");
            }
        } while (trinkDatum == null || !jetzt.after(trinkDatum));
        return trinkDatum;
    }

    private Double askKoerpermasse(){

        double koerpermasse = 0;
        do {
            final String eingabe = JOptionPane.showInputDialog(null, "Geben Sie Ihr Gewicht in kg ein.",
                    "Gewicht",
                    JOptionPane.QUESTION_MESSAGE);
            if (eingabe == null) { // Cancel
                System.exit(0);
            }

            try {
                koerpermasse = Double.parseDouble(eingabe);
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(null, eingabe + " ist kein gültiges Gewicht.");
            }
        }while (koerpermasse == 0);
        return koerpermasse;
    }

    private double askKoerpergroesseInCm(){
        double koerpergroesseInCm = 0;
        do {
            final String eingabe = JOptionPane.showInputDialog(null, "Geben Sie Ihre Grösse in cm ein",
                    "Grösse",
                    JOptionPane.QUESTION_MESSAGE);
            if (eingabe == null) { // Cancel
                System.exit(0);
            }

            try {
                koerpergroesseInCm = Double.parseDouble(eingabe);
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(null, eingabe + " ist kein gültige Koerpergrösse.");
            }
        }while (koerpergroesseInCm == 0);
        return koerpergroesseInCm;
    }

    private Date askGeburtsdatum() {
        Date geburtsdatum = null;
        final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        final Date jetzt = new Date();
        do {
            final String eingabe = JOptionPane.showInputDialog(null, "Geben Sie Ihr Geburtsdatum ein.",
                    "Geburtsdatum",
                    JOptionPane.QUESTION_MESSAGE);

            if (eingabe == null) { // Cancel
                System.exit(0);
            }
            try {
                geburtsdatum = formatter.parse(eingabe);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, eingabe + " ist kein gültiges Datum.");
            }
        } while (geburtsdatum == null || !jetzt.after(geburtsdatum));
        return geburtsdatum;
    }

    private int askGeschlecht(){

        Object[] options = {"Männlich", "Weiblich"};
        int eingabe =  JOptionPane.showOptionDialog(null,
                "Wählen Sie Ihr Geschlecht",
                "Geschlecht",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (eingabe == -1) { // Cancel
            System.exit(0);
        }
        switch (eingabe){
            case 0:
                return Person.MAENNLICH;
            case 1:
                return Person.WEIBLICH;
        }
        return -1;
    }

    private int askVolumenInMilliLiter(){
        int VolumenInMilliLiter = 0;
        do {
            final String eingabe = JOptionPane.showInputDialog(null, "Geben Sie die getrunkene Menge in Milliliter ein.",
                    "Trinkmenge",
                    JOptionPane.QUESTION_MESSAGE);
            if (eingabe == null) { // Cancel
                System.exit(0);
            }

            try {
                VolumenInMilliLiter = Integer.parseInt(eingabe);
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(null, eingabe + " ist kein gültige Trinkmenge.");
            }
        }while (VolumenInMilliLiter == 0);
        return VolumenInMilliLiter;
    }

    private double askAlkoholgehalt(){
        Object[] options = {"Bier", "Wein", "Schnaps"};
        int eingabe = JOptionPane.showOptionDialog(null,
                "Was für ein Getränk haben Sie getrunken?",
                "Getränk",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (eingabe == -1) { // Cancel
            System.exit(0);
        }
        switch(eingabe) {
            case 0:
                return AlkoholischesGetraenk.BIER_ALKOHOLGEHALT;
            case 1:
                return AlkoholischesGetraenk.WEIN_ALKOHOLGEHALT;
            case 2:
                return AlkoholischesGetraenk.SCHNAPS_ALKOHOLGEHALT;
        }
        return 0;
    }

    private void postResultat(double alkoholPromille){

        Spruch spruch = new Spruch(alkoholPromille);

        JOptionPane.showMessageDialog(null,
                "Promillegehalt: " + alkoholPromille + "\n" + spruch.getSpruch(),
                "Resultat",
                JOptionPane.WARNING_MESSAGE);
    }
}
