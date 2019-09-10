package entity;

public class Utente {

    private String username;
    private String password;
    private String nome;
    private String cognome;

    public String getCognome() {
        return cognome;
    }

    public Utente(String username, String password, String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " " + cognome;
    }
}
