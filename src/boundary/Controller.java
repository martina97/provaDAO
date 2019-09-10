package boundary;

import control.ControllerConfigurazioneRisorse;
import dao.UtenteDao;
import entity.Utente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextField nomeEdificio;

    @FXML
    public TextField indirizzoEdificio;

    @FXML
    public ComboBox combo;

    @FXML
    public CheckBox myCheckBox;

    public void creaEdificio(ActionEvent actionEvent) {

        String nome = nomeEdificio.getText();
        String indirizzo = indirizzoEdificio.getText();
        Utente u = UtenteDao.findByNameAndPassword(nome, indirizzo);
        System.out.println(u);
        ControllerConfigurazioneRisorse.creaEdificio(nome,indirizzo);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
