package gui.views;

import helper.HibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginPage extends BorderPane {
  PasswordField senha;
  TextField usuario;
  Button loginButton;

  @FXML
  public void loginIntoSystem(ActionEvent event) {
    String user = usuario.getText();
    String password = senha.getText();

    if (!HibernateController.login(user, password)) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Cadastro não encontrado");
      alert.setContentText("Cadastro inválido ou dados informados incorretamente");
      alert.show();
    } else {
      switchToHomePage(loginButton);
    }
    ;
  };

  public LoginPage() {

    // Barra de navegação
    HBox navBar = createNavBar();

    // Conteúdo principal
    VBox mainContent = createMainContent();

    // Configuração dos componentes no layout
    this.setTop(navBar);
    this.setCenter(mainContent);
  }

  private HBox createNavBar() {
    HBox navBar = new HBox();
    navBar.setPadding(new Insets(15, 12, 15, 12));
    navBar.setStyle("-fx-background-color: #E50914;"); // Cor de fundo da barra de navegação

    // Botão Home com logo da Netflix
    Image netflixLogo = new Image(getClass().getResourceAsStream("../assets/iconNetflix.png")); // Substitua com o
                                                                                                // caminho correto da
                                                                                                // imagem
    ImageView logoView = new ImageView(netflixLogo);
    logoView.setFitHeight(30); // Ajuste conforme necessário
    logoView.setPreserveRatio(true);
    Button homeButton = new Button("", logoView); // Botão com a logo

    // Botão Login
    Button registerButton = new Button("Registrar-se");

    // Estilização dos botões
    String buttonStyle = "-fx-font-size: 14px; -fx-background-color: #000000; -fx-text-fill: #FFFFFF;";
    String buttonHoverStyle = "-fx-font-size: 14px; -fx-background-color: #555555; -fx-text-fill: #FFFFFF;";

    homeButton.setStyle(buttonStyle);
    homeButton.setOnMouseEntered(e -> homeButton.setStyle(buttonHoverStyle));
    homeButton.setOnMouseExited(e -> homeButton.setStyle(buttonStyle));

    registerButton.setStyle(buttonStyle);
    registerButton.setOnMouseEntered(e -> registerButton.setStyle(buttonHoverStyle));
    registerButton.setOnMouseExited(e -> registerButton.setStyle(buttonStyle));
    registerButton.setOnAction(e -> switchToRegistroPage(registerButton));

    // Espaço flexível entre os botões
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    navBar.getChildren().addAll(homeButton, spacer, registerButton);
    return navBar;
  }

  private VBox createMainContent() {
    VBox mainContent = new VBox();
    mainContent.setAlignment(Pos.CENTER_LEFT);
    mainContent.setPadding(new Insets(50, 0, 0, 50)); // Ajuste os espaços conforme necessário
    mainContent.setSpacing(50); // Espaçamento de 20px entre os elementos

    // Carregar a imagem de fundo
    Image backgroundImage = new Image(getClass().getResourceAsStream("../assets/backgroundLogin.png")); // Substitua com
                                                                                                        // o caminho
                                                                                                        // correto da
                                                                                                        // imagem
    BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
    BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
    mainContent.setBackground(new Background(bgImage));

    // Elementos de login
    Label loginLabel = new Label("Login");
    loginLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 40));
    loginLabel.setStyle("-fx-text-fill: #FFFFFF;");

    usuario = new TextField();
    usuario.setId("usuarioTextField");
    usuario.setPromptText("E-mail");
    usuario.setMinHeight(40); // Altura mínima do campo de texto
    usuario.setMaxWidth(300); // Largura máxima do campo de texto

    senha = new PasswordField();
    senha.setId("senhaTextField");
    senha.setPromptText("Senha");
    senha.setMinHeight(40);
    senha.setMaxWidth(300); // Largura máxima do campo de senha

    loginButton = new Button("Entrar");
    loginButton.setId("loginButton");
    loginButton.setOnAction(this::loginIntoSystem);
    loginButton.setStyle("-fx-font-size: 16px; -fx-background-color: #E50914; -fx-text-fill: #FFFFFF;");
    loginButton.setMaxHeight(40); // Altura máxima do botão
    loginButton.setMaxWidth(300); // Largura máxima do botão

    // Adicionar elementos ao VBox
    mainContent.getChildren().addAll(loginLabel, usuario, senha, loginButton);

    return mainContent;
  }

  private void switchToRegistroPage(Button button) {
    Stage stage = (Stage) button.getScene().getWindow();
    RegistroPage RegistroPage = new RegistroPage();
    Scene RegistroScene = new Scene(RegistroPage, stage.getWidth(), stage.getHeight());
    stage.setScene(RegistroScene);
  }

  private void switchToHomePage(Button button) {
    Stage stage = (Stage) button.getScene().getWindow();
    HomePage SelectedPage = new HomePage();
    Scene SelectedScene = new Scene(SelectedPage, stage.getWidth(), stage.getHeight());
    stage.setScene(SelectedScene);
  }
}
