package gui;

import helper.ComandosSistema;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import entities.Aplicacao;
import entities.Despesa;
import entities.Emprestimo;
import entities.Receita;
import relatorio.*;
public class mainController implements Initializable {

    @FXML
    private Label cons_dia;

    @FXML
    private Label cons_mes;

    @FXML
    private Label cons_ano;

    @FXML
    private DatePicker dataDespesa;

    @FXML
    private DatePicker dataEmprestimo;

    @FXML
    private DatePicker dataInvest;

    @FXML
    private DatePicker dataReceita;

    @FXML
    private Button inserirDespesa;

    @FXML
    private Button inserirEmprestimo;

    @FXML
    private Button inserirInvestimento;

    @FXML
    private Button inserirReceita;

    @FXML
    private TextField nomeDespesa;

    @FXML
    private TextField nomeEmprestimo;

    @FXML
    private TextField nomeInvest;

    @FXML
    private TextField nomeReceita;

    @FXML
    private TextArea obsInvest;
    
    @FXML
    private TextField jurosemprestimo;

    @FXML
    private TextField taxaJuros;

    @FXML
    private ChoiceBox<String> tipoInvest;

    private String[] tipos = {"Anual","Mensal","Diário"};

    @FXML
    private TextField valorDespesa;

    @FXML
    private TextField valorEmprestimo;

    @FXML
    private TextField valorInvest;

    @FXML
    private TextField valorReceita;

    @FXML
    private TextArea obsReceita;

    @FXML
    private TextField numParcelas;

    @FXML
    private TextField valorParcela;

    @FXML
    private TextArea obsEmprestimo;
    
   
    @FXML
    private DatePicker dataRefD;

    @FXML
    private DatePicker dataRefS;

    @FXML
    private DatePicker dataRefM;

    @FXML
    private DatePicker dataRefA;

    @FXML
    private Label labelDiario;

    @FXML
    private Label labelSemanal;

    @FXML
    private Label labelMensal;

    @FXML
    private Label labelAnual;

    @FXML
    public void insereAplicacao()
    {
        String data = dataInvest.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if(ComandosSistema.inserirAplicacao(new Aplicacao(nomeInvest.getText().toString(),data,obsInvest.getText().toString(),Float.parseFloat(valorInvest.getText().toString()),Float.parseFloat(taxaJuros.getText().toString()))))
        {
            System.out.println("Inserido com sucesso");
        }else
        {
            System.out.println("Falha na inserção");
        }
    }

    @FXML
    public void insereDespesa()
    {
        String data = dataDespesa.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if(ComandosSistema.inserirDespesa(new Despesa(nomeDespesa.getText().toString(),data,obsInvest.getText().toString(),Float.parseFloat(valorDespesa.getText().toString()))))
        {
            System.out.println("Inserido com sucesso");
        }else
        {
            System.out.println("Falha na inserção");
        }
    }

    @FXML
    public void insereImprestimo()
    {
        String data = dataEmprestimo.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        Emprestimo em =  new Emprestimo(nomeEmprestimo.getText().toString(),data,obsEmprestimo.getText().toString(),Float.parseFloat(valorEmprestimo.getText().toString()),Float.parseFloat(jurosemprestimo.getText().toString()),Integer.parseInt(numParcelas.getText().toString()),Float.parseFloat(valorParcela.getText().toString()));
        							  // System.out.println(nomeEmprestimo.getText().toString()+data+obsEmprestimo.getText().toString()+Float.parseFloat(valorEmprestimo.getText().toString())+Float.parseFloat(jurosemprestimo.getText().toString())+Integer.parseInt(numParcelas.getText().toString())+Float.parseFloat(valorParcela.getText().toString()));

        System.out.println(em.getNome());
        System.out.println(em.getDataIn());
        System.out.println(em.getDescricao());
        System.out.println(em.getJuros());
        System.out.println(em.getParcela());
        System.out.println(em.getValor());
        System.out.println(em.getMesesParcelas());
        if(ComandosSistema.inserirEmprestimo(em))
        {
            System.out.println("Inserido com sucesso");
        }else
        {
            System.out.println("Falha na inserção");
        }
    }

    @FXML
    public void insereReceita()
    {
        String data = dataReceita.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if(ComandosSistema.inserirReceita(new Receita(nomeReceita.getText().toString(),data,obsReceita.getText().toString(),Float.parseFloat(valorReceita.getText().toString()))))
        {
            System.out.println("Inserido com sucesso");
        }else
        {
            System.out.println("Falha na inserção");
        }

    }
    
    
    FabricaRelatorio fr = new FabricaRelatorio();

    @FXML
    public void mostrarDiaria() {
    	
    	
    	 String data = dataRefD.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    	 
 
    	
        
    	 
    	 labelDiario.setText(ComandosSistema.gerarRelatorio("diario", data));

    }

    @FXML
    public void mostrarSemanal() {
        String data = dataRefS.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));//String dataRef = "17/06/2023";
    
        labelSemanal.setText(ComandosSistema.gerarRelatorio("semanal", data));
     
    }

    @FXML
    public void mostrarMensal() {
        String data = dataRefM.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));//String dataRef = "06/2023";
        String[] ano = data.split("/");
        String dataa = ano[1] +"/"+ ano[2];
        
        labelMensal.setText(ComandosSistema.gerarRelatorio("mensal", dataa));
    }

    @FXML
    public void mostrarAnual() {
        String data = dataRefA.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));//String anoRef = "2023";
        String[] ano = data.split("/");
   
        labelAnual.setText(ComandosSistema.gerarRelatorio("anual", ano[2]));
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoInvest.getItems().addAll(tipos);
    }
}
