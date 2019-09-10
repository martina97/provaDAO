package dao;

import entity.Utente;

import java.sql.*;

public class UtenteDao2 {
    private static String USER = "root";    //user db
    private static String PASS = "root";    //password db

    private static final String DB_URL = "jdbc:mariadb://127.0.0.1:3406/provadb";   //indirizzi db
    private static final String DRIVER_CLASS_NAME = "org.mariadb.jdbc.Driver";

    public static Utente insertNewUser() {

        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        Utente u = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);     //connessione con database specificato prima

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,  //ResultSet è oggetto che contiene il risultato della query
                    ResultSet.CONCUR_READ_ONLY);


            String query = "INSERT INTO utenti VALUES ('ceci','bho','root2','root2');";

            /*
            String sql = "SELECT nome, cognome, username, " +             //scrivo query in SQL
                    "password FROM utenti where username = '"
                    + username + "' AND password = '" + password + "';";
            */

            ResultSet rs = stmt.executeQuery(query);                        //eseguo query

            if (!rs.first()) // rs not empty
                return null;

            boolean moreThanOne = rs.first() && rs.next();                  //mette cursore alla prima riga
            assert !moreThanOne; // per abilitare le asserzioni, avviare la JVM con il parametro -ea
            // (Run Configurations -> <configurazione utilizzata per l'avvio del server> -> Arguments -> VM Arguments).
            // N.B. Le asserzioni andrebbero usate solo per test e debug, non per codice in produzione

            // riposizionamento del cursore
            rs.first();

            // lettura delle colonne "by name"
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            String usernameLoaded = rs.getString("username");

            //assert (usernameLoaded.equals(username));

            u = new Utente(usernameLoaded, "",
                    nome, cognome);

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return u;
    }

    public static Utente findByNameAndPasswordMockup(String username, String password) {
        if ("myusername".equals(username) && "mypassword".equals(password))
            return new Utente("myusername", "", "Tizio","Caio");
        else return null;
    }

    public static void main(String[] args){
        Utente myUser = insertNewUser();
    }
}
