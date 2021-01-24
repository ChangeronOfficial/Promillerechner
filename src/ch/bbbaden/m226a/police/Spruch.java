package ch.bbbaden.m226a.police;

public class Spruch {

    private double alkoholPromille;

    public Spruch(double alkoholPromille){
        this.alkoholPromille = alkoholPromille;
    }
    public String getSpruch(){
        if (alkoholPromille < 1){
            return "Da geht noch was rein";
        }
        else{
            return "Schon mächtig einen in der Krone";
        }
    }
}
