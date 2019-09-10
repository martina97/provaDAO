package control;

import entity.Edificio;

public class ControllerConfigurazioneRisorse {
    public static void creaEdificio(String nome, String indirizzo) {
        Edificio nuovoEdificio = new Edificio(nome, indirizzo);
        System.out.println("Edificio creato");
    }
}
