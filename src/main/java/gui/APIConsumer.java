package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
public class APIConsumer implements Initializable {

    @FXML
    private Label cons_dia;

    @FXML
    public void insereAplicacao() {
        // String data = dataInvest.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        // if(ComandosSistema.inserirAplicacao(new Aplicacao(nomeInvest.getText().toString(),data,obsInvest.getText().toString(),Float.parseFloat(valorInvest.getText().toString()),Float.parseFloat(taxaJuros.getText().toString()))))
        // {
        //     System.out.println("Inserido com sucesso");
        // }else
        // {
        //     System.out.println("Falha na inserção");
        // }
    }


    @FXML
    public void mostrarAnual() {
        // String data = dataRefA.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));//String anoRef = "2023";
        // String[] ano = data.split("/");
   
        // labelAnual.setText(ComandosSistema.gerarRelatorio("anual", ano[2]));
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
